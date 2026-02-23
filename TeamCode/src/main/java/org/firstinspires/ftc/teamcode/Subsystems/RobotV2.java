package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.Settings.InputHandler;

import java.security.KeyStore;

public class RobotV2 extends Mechanism {

    public Drivebase db;
    public ScoringAssemblyV2 scorer = new ScoringAssemblyV2();

    private InputHandler handler = new InputHandler();

    public Pose2d startingPose;
    public boolean isAuto;

    public Pose2d rrPose;


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

        if (!isAuto) {
            db.loop(aimPad1, false);
            handler.updateInputs(aimPad1, aimPad2);

            if (handler.INTAKE_IN || handler.GRANT_INTAKE_IN) {
                scorer.intake.setMode(Intake.IntakeMode.IN);
            } else if (handler.INTAKE_OUT || handler.GRANT_INTAKE_OUT) {
                scorer.intake.setMode(Intake.IntakeMode.OUT);
            } else {
                scorer.intake.setMode(Intake.IntakeMode.OFF);
            }

            if (handler.CLOSE_LAUNCH){
                //scorer.launcher.setVelo(300 * 0.01);
                scorer.score(1,80,0);
            } else if (handler.FAR_LAUNCH){
                scorer.launcher.setVelo(0);
            }

            if (handler.GATE_IN) {
                scorer.gate.setMode(Gate.GateMode.IN);
            } else if (handler.GATE_OUT) {

                scorer.gate.setMode(Gate.GateMode.OUT);
            } else {
                scorer.gate.setMode(Gate.GateMode.OFF);
            }

            if (handler.HOOD_DOWN) {
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() - .05);
            } else if (handler.HOOD_UP) {
                scorer.hood.hood.setPosition(scorer.hood.hood.getPosition() + .05);
            }

//            if (handler.FAR_LAUNCH) {
//                scorer.launcher.setTargetPower(Launcher.launchPower.FAR);
//            } else if (handler.REVERSE_LAUNCH) {
//                scorer.launcher.setTargetPower(Launcher.launchPower.REVERSE);
//            } else if (handler.CLOSE_LAUNCH) {
//                scorer.launcher.setTargetPower(Launcher.launchPower.CLOSE);
//            } else {
//                scorer.launcher.setTargetPower(Launcher.launchPower.OFF);
//            }
//0-79
//            if (handler.HOOD_UP) {
//                //double currentPos = scorer.hood.hood.getPosition();
//                //scorer.hood.hood.setPosition(currentPos + 0.01);
//            } else if (handler.HOOD_DOWN) {
//                //double currentPos = scorer.hood.hood.getPosition();
//                //scorer.hood.hood.setPosition(currentPos - 0.01);
//            } else if (handler.FULL_DOWN) {
//                //scorer.hood.hood.setPosition(0.73);
//            } else if (handler.FULL_UP) {
//                //scorer.hood.hood.setPosition(0);
//            }
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

    @Override
    public void telemetry(Telemetry telemetry) {
        scorer.telemetry(telemetry);
        //db.telemetry(telemetry);
        telemetry.addData("rr pose", rrPose);
    }
}