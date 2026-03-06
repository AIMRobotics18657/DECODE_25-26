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
    public ElapsedTime poseTimer = new ElapsedTime();
    public boolean isAutoAIM = false;

    // Get pose from Limelight (field-relative!)
    double visionX;        // inches
    double visionY;        // inches
    double visionHeading; // RADIANS

    Pose2d visionPose = new Pose2d(visionX, visionY, visionHeading);

    public enum localType {
        ODO,
        LIME;
    }

    public localType currentLocalType = localType.LIME;


    public RobotV2(Pose2d startingPose, boolean isAuto) {
        this.startingPose = startingPose;
        this.isAuto = isAuto;
        db = new Drivebase(startingPose);

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
        double currentHeading = Math.toDegrees(rrPose.heading.toDouble());

//        if (scorer.limelight.llResult != null && scorer.limelight.llResult.isValid()) {
//            db.drive.localizer.setPose(new Pose2d(scorer.limelight.llResult.getBotpose().getPosition().x * 0.0254, scorer.limelight.llResult.getBotpose().getPosition().y * 0.0254, Math.toDegrees(rrPose.heading.toDouble())));
////TODO meters or in???
//                       }


        if (scorer.limelight.llResult != null && scorer.limelight.llResult.isValid()) {

            double xMeters = scorer.limelight.llResult.getBotpose_MT2().getPosition().x;

            double yMeters = scorer.limelight.llResult.getBotpose_MT2().getPosition().y;

            double yawDeg = scorer.limelight.llResult.getBotpose_MT2().getOrientation().getYaw();

            double xInches = xMeters / 0.0254;
            double yInches = yMeters / 0.0254;
            double headingRad = Math.toRadians(yawDeg);

            visionPose = new Pose2d(xInches, yInches, headingRad);


            //db.drive.localizer.setPose(visionPose);
        }

        if (!isAuto) {
            db.loop(aimPad1, false);


            handler.updateInputs(aimPad1, aimPad2);

            // GRANT CONTROLS

            if (handler.INTAKE_IN) {
                scorer.intake.setMode(Intake.IntakeMode.IN);
            } else if (handler.INTAKE_OUT) {
                scorer.intake.setMode(Intake.IntakeMode.OUT);
            } else {
                scorer.intake.setMode(Intake.IntakeMode.OFF);
            }

//            if (handler.AUTO_ADJUST) {
//                if (scorer.distPhase == ScoringAssemblyV2.distancePhase.ONE) {
//                    scorer.score(scorer.lldist, 165, 3, 0, 15);
//                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.TWO) {
//                    scorer.score(scorer.lldist, 190, 0, 0, -10);
//                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.THREE) {
//                    scorer.score(scorer.lldist, 200, 3, 0, 0);
//                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.FAR) {
//                    scorer.launcher.setVelo(235 * 2 * Math.PI / 628);
//                    scorer.hood.hood.setPosition(32-32/72-32);
//                }
//            } else if (handler.GRANT_LAUNCHER_OFF) {
//                scorer.launcher.setVelo(0);
//            }

            if (handler.AUTO_ADJUST && currentLocalType == localType.LIME) {
                if (scorer.distPhase == ScoringAssemblyV2.distancePhase.ONE) {
                    scorer.score(scorer.lldist, 165, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.TWO) {
                    scorer.score(scorer.lldist, 190, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.THREE) {
                    scorer.score(scorer.lldist, 200, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.FAR) {
                    scorer.launcher.setVelo(235 * 2 * Math.PI / 628);
                    scorer.hood.hood.setPosition(32-32/72-32);
                }
            } else if (handler.AUTO_ADJUST && currentLocalType == localType.ODO) {
                if (scorer.distPhase == ScoringAssemblyV2.distancePhase.ONE) {
                    scorer.score(getDist(rrPose.position.x, rrPose.position.y), 165, 15, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.TWO) {
                    scorer.score(getDist(rrPose.position.x, rrPose.position.y), 190, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.THREE) {
                    scorer.score(getDist(rrPose.position.x, rrPose.position.y), 200, 10, 0, 0);
                } else if (scorer.distPhase == ScoringAssemblyV2.distancePhase.FAR) {
                    scorer.launcher.setVelo(265 * 2 * Math.PI / 628);
                    scorer.hood.hood.setPosition(38-32/72-32);
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
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() + 0.1);
            } else if (handler.GRANT_HOOD_UP) {
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() - 0.1);
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
                scorer.score(70, 190, 0, 0, -10);
            }  else if (handler.FAR_LAUNCH) {
                scorer.launcher.setVelo(235 * 2 * Math.PI / 628);
                scorer.hood.hood.setPosition(32-32/72-32);
            } else if (handler.JOLIE_LAUNCHER_OFF){
                scorer.launcher.setVelo(0);
            }

            if (handler.GRANT_HOOD_DOWN) {
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() + 0.1); //TODO make this so it moves one degree
            } else if (handler.GRANT_HOOD_UP) {
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() - 0.1);
            }

//            } else if (handler.FULL_DOWN) {
//                //scorer.hood.hood.setPosition(0.73);
//            } else if (handler.FULL_UP) {
//                //scorer.hood.hood.setPosition(0);
//            }

            if (handler.SWITCH_LOCAL) {
                if (currentLocalType == localType.LIME) {
                    currentLocalType = localType.ODO;
                } else if (currentLocalType == localType.ODO) {
                    currentLocalType = localType.LIME;
                }
            }

//TODO can't stop the ramp
//            if (handler.TOGGLE_DRIVE_SPEED) {
//                if (db.activeDriveSpeed == Drivebase.driveSpeed.REGULAR) {
//                    db.activeDriveSpeed = Drivebase.driveSpeed.SLOW;
//                } else {
//                    db.activeDriveSpeed = Drivebase.driveSpeed.REGULAR;
//                }
//            }
        }

    }

    public void setShoot() {
        shootIsDone = false;
        timer.reset();
    }
    public void shootThree () {
        if (timer.milliseconds() <= 100) {
            if (scorer.limelight.llResult != null && scorer.limelight.llResult.isValid()) {
            scorer.score(scorer.lldist, 190, 10, 0, -10);
            } else {
                scorer.score(65 * 0.0254, 190, 10, 0, -10);
            }

        } else if (timer.milliseconds() > 200 && timer.milliseconds() <= 3000) {
            scorer.gate.setMode(Gate.GateMode.IN);
            scorer.intake.setMode(Intake.IntakeMode.IN);
        } else if (timer.milliseconds() > 1000) {
            scorer.launcher.setVelo(0);
            scorer.gate.setMode(Gate.GateMode.OFF);
            shootIsDone = true;

        }

    }

    private double getDist(double xTarget, double yTarget) {

        double xDist = Math.abs(xTarget - rrPose.position.x);
        double yDist = Math.abs(yTarget - rrPose.position.y);

        double dist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
        return dist;
    }

    public double getCorretedHeading() {
        if (Math.toDegrees(rrPose.heading.toDouble()) < 0) {
            return Math.toDegrees(rrPose.heading.toDouble()) + 360;
        } else {
            return Math.toDegrees(rrPose.heading.toDouble());
        }
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        scorer.telemetry(telemetry);
        //db.telemetry(telemetry);
        telemetry.addData("localization type", currentLocalType);
        telemetry.addData("rr pose", rrPose);
        telemetry.addData("current heading", getCorretedHeading());
    }
}