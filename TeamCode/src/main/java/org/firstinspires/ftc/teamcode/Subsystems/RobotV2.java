package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Settings.InputHandler;


public class RobotV2 extends Mechanism {

    public Drivebase db;
    public ScoringAssemblyV2 scorer = new ScoringAssemblyV2();

    private InputHandler handler = new InputHandler();

    public Pose2d startingPose;
    public boolean isAuto;

    public Pose2d rrPose;
    public ElapsedTime timer = new ElapsedTime();
    public boolean shootIsDone = false;
    public double xTarget;
    public double yTarget;
    public boolean isRed;
    public double odoDistOffset = -10; // tuned for blue
    public double odoDistMeters;
    public enum localType {
        ODO,
        LIME;
    }

    public localType currentLocalType = localType.LIME;


    public RobotV2(Pose2d startingPose, boolean isAuto, boolean isRed) {
        this.startingPose = startingPose;
        this.isAuto = isAuto;
        db = new Drivebase(startingPose);
        this.isRed = isRed;

        if (isRed) {
            xTarget = -60;
            yTarget = 60;
        } else {
            xTarget = -60;
            yTarget = -60;
        }

    }

    @Override
    public void init(HardwareMap hwMap) {
        db.init(hwMap);
        scorer.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimPad1, AIMPad aimPad2) {
        scorer.loop(aimPad1);
        db.drive.updatePoseEstimate();

        rrPose = db.drive.localizer.getPose();

        if (!isAuto) {
            db.loop(aimPad1, false);
            handler.updateInputs(aimPad1, aimPad2);
            odoDistMeters = getDist() * 0.0254;


            // Calculate Distance Phases

            if (currentLocalType == RobotV2.localType.LIME && scorer.limelight.llResult != null && scorer.limelight.llResult.isValid()) {
                scorer.lldist = scorer.limelight.distance * 0.0254;

                if (scorer.limelight.distance <= 55) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.ONE;
                } else if (scorer.limelight.distance > 55 && scorer.limelight.distance <= 72) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.TWO;
                } else if (scorer.limelight.distance > 72 && scorer.limelight.distance <= 85) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.THREE;
                } else if (scorer.limelight.distance > 85) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.FAR;
                }
            } else if (currentLocalType == localType.ODO){
                if (getDist() <= 55) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.ONE;
                } else if (getDist() > 55 && getDist() <= 72) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.TWO;
                } else if (getDist() > 72 && getDist() <= 85) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.THREE;
                } else if (getDist() > 85) {
                    scorer.distPhase = ScoringAssemblyV2.distancePhase.FAR;
                }
            } else {
                scorer.distPhase = ScoringAssemblyV2.distancePhase.NA;
            }

            // GRANT CONTROLS

            if (handler.INTAKE_IN) {
                scorer.intake.setMode(Intake.IntakeMode.IN);
            } else if (handler.INTAKE_OUT) {
                scorer.intake.setMode(Intake.IntakeMode.OUT);
            } else {
                scorer.intake.setMode(Intake.IntakeMode.OFF);
            }

            if (handler.AUTO_ADJUST && currentLocalType == localType.LIME) {
                if (scorer.distPhase == ScoringAssemblyV2.distancePhase.ONE) {
                    scorer.score(scorer.lldist, 165, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.TWO) {
                    scorer.score(scorer.lldist, 190, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.THREE) {
                    scorer.score(scorer.lldist, 200, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.FAR) {
                    scorer.launcher.setVelo(245 * 2 * Math.PI / 628);
                    scorer.hood.setPosition((32-32)/(72-32));
                }
            } else if (handler.AUTO_ADJUST && currentLocalType == localType.ODO) {
                if (scorer.distPhase == ScoringAssemblyV2.distancePhase.ONE) {
                    scorer.score(odoDistMeters, 165, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.TWO) {
                    scorer.score(odoDistMeters, 190, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.THREE) {
                    scorer.score(odoDistMeters, 200, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.FAR) {
                    scorer.launcher.setVelo(245 * 2 * Math.PI / 628);
                    scorer.hood.setPosition((38-32)/(72-32));
                }
            } else if (handler.GRANT_LAUNCHER_OFF) {
                scorer.launcher.setVelo(0);
            }

            if (handler.GATE_IN) {
                scorer.gate.setMode(Gate.GateMode.IN);
            } else if (handler.GATE_OUT) {
                scorer.gate.setMode(Gate.GateMode.OUT);
            } else {
                scorer.gate.setMode(Gate.GateMode.OFF);
            }

            if (handler.GRANT_HOOD_DOWN) {
                scorer.hood.setPosition(scorer.hood.hood.getPosition() + 0.1);
            } else if (handler.GRANT_HOOD_UP) {
                scorer.hood.setPosition(scorer.hood.hood.getPosition() - 0.1);
            }

            if (handler.TOGGLE_DRIVE_SPEED) {
                if (db.activeDriveSpeed == Drivebase.driveSpeed.REGULAR) {
                    db.activeDriveSpeed = Drivebase.driveSpeed.SLOW;
                } else if (db.activeDriveSpeed == Drivebase.driveSpeed.SLOW) {
                    db.activeDriveSpeed = Drivebase.driveSpeed.REGULAR;
                }
            }

            // JOLIE CONTROLS

            if (handler.CLOSE_LAUNCH) {
                scorer.launcher.setVelo(200 * 2 * Math.PI / 628);
                scorer.hood.setPosition((45-32)/(72-32)); // TODO tune this
            }  else if (handler.FAR_LAUNCH) {
                scorer.launcher.setVelo(245 * 2 * Math.PI / 628);
                scorer.hood.setPosition((38-32)/(72-32));
            } else if (handler.JOLIE_LAUNCHER_OFF){
                scorer.launcher.setVelo(0);
            }

            if (handler.JOLIE_HOOD_DOWN) {
                scorer.hood.setPosition(scorer.hood.hood.getPosition() + 0.1); //TODO make this so it moves one degree
            } else if (handler.JOLIE_HOOD_UP) {
                scorer.hood.setPosition(scorer.hood.hood.getPosition() - 0.1);
            }

            if (handler.REVERSE_LAUNCH) {
                scorer.launcher.setVelo(-0.2);
            }

            if (handler.SWITCH_LOCAL) {
                if (currentLocalType == localType.LIME) {
                    currentLocalType = localType.ODO;
                } else if (currentLocalType == localType.ODO) {
                    currentLocalType = localType.LIME;
                }
            }

            if (handler.RESET_LOCAL) {
                if (isRed) {
                    db.drive.localizer.setPose(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135)));
                } else {
                    db.drive.localizer.setPose(new Pose2d(-55 + Math.sqrt(50),-55 + Math.sqrt(50), Math.toRadians(-135)));
                }

            }
        }

    }

    public void setShoot() {
        shootIsDone = false;
        timer.reset();
    }
    public void shootThree () {
        if (timer.milliseconds() <= 100) {
                scorer.score(63 * 0.0254, 190, 10, 0, -10);

        } else if (timer.milliseconds() > 200 && timer.milliseconds() <= 3000) {
            scorer.gate.setMode(Gate.GateMode.IN);
            scorer.intake.setMode(Intake.IntakeMode.IN);
        } else if (timer.milliseconds() > 1000) {
            scorer.launcher.setVelo(0);
            scorer.gate.setMode(Gate.GateMode.OFF);
            shootIsDone = true;

        }

    }

    private double getDist() {

        double xDist = Math.abs(xTarget - rrPose.position.x);
        double yDist = Math.abs(yTarget - rrPose.position.y);

        double dist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
        return dist + odoDistOffset;
    }


    @Override
    public void telemetry(Telemetry telemetry) {
        scorer.telemetry(telemetry);
        db.telemetry(telemetry);
        telemetry.addData("odo dist", getDist());
        telemetry.addData("localization type", currentLocalType);
        telemetry.addData("rr pose", rrPose);
    }
}