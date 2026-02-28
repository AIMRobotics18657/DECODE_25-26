//package org.firstinspires.ftc.teamcode.Subsystems;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.aimrobotics.aimlib.util.Mechanism;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Settings.InputHandler;
//
//import java.security.KeyStore;
//
//public class Robot extends Mechanism {
//
//    public Drivebase db;
//    //public ScoringAssembly scorer = new ScoringAssembly();
//    //public Riser riser = new Riser();
//
//    private InputHandler handler = new InputHandler();
//
//    public Pose2d startingPose;
//    public boolean isAuto;
//    public boolean isFullOn;
//    public boolean isWindUp;
//
//    public enum robotState {
//        SCORING,
//        ENDGAME;
//    }
//
//    public robotState activeState = robotState.SCORING;
//
//    public Robot (Pose2d startingPose, boolean isAuto) {
//        this.startingPose = startingPose;
//        this.isAuto = isAuto;
//        db = new Drivebase(startingPose);
//    }
//
//    @Override
//    public void init(HardwareMap hwMap) {
//        db.init(hwMap);
//        //scorer.init(hwMap);
//        isFullOn = false;
//        //riser.init(hwMap);
//    }
//
//    @Override
//    public void loop(AIMPad aimPad1, AIMPad aimPad2) {
//        //scorer.loop(aimPad1);
//        //riser.loop(aimPad1);
//        db.drive.updatePoseEstimate();
//
//        Pose2d rrPose = db.drive.localizer.getPose();
//
//        if (!isAuto){
//            db.loop(aimPad1, false);
//            handler.updateInputs(aimPad1, aimPad2);
//
//            if (handler.INTAKE_IN || handler.GRANT_INTAKE_IN) {
//                //scorer.intake.setMode(Intake.IntakeMode.IN);
//            } else if (handler.INTAKE_OUT || handler.GRANT_INTAKE_OUT) {
//                //scorer.intake.setMode(Intake.IntakeMode.OUT);
//            } else {
//                //scorer.intake.setMode(Intake.IntakeMode.OFF);
//            }
//
//            if (handler.FAR_LAUNCH) {
//                //scorer.launcher.setTargetPower(Launcher.launchPower.FAR);
//            } else if (handler.REVERSE_LAUNCH) {
//                //scorer.launcher.setTargetPower(Launcher.launchPower.REVERSE);
//            } else if (handler.CLOSE_LAUNCH) {
//                //scorer.launcher.setTargetPower(Launcher.launchPower.CLOSE);
//            } else {
//                //scorer.launcher.setTargetPower(Launcher.launchPower.OFF);
//            }
////0-79
//            if(handler.HOOD_UP) {
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
//
////            if (handler.WIND_UP) {
////                isWindUp = true;
////                scorer.shootingFinished = false;
////                scorer.startWindUp();
////            } else if (handler.WIND_UP_FINISHED) {
////                isWindUp = false;
////                scorer.shootingFinished = true;
////            }
////TODO can't stop the ramp
////            if (handler.TOGGLE_DRIVE_SPEED) {
////                if (db.activeDriveSpeed == Drivebase.driveSpeed.REGULAR) {
////                    db.activeDriveSpeed = Drivebase.driveSpeed.SLOW;
////                } else {
////                    db.activeDriveSpeed = Drivebase.driveSpeed.REGULAR;
////                }
////            }
//
////            if (handler.TOGGLE_GATE) {
////                scorer.i.toggleGate();
////            }
//
//            //if (handler.FULL_ON) {
//              //  isFullOn = true;
//                //scorer.startShootOne();
//            //} else {
//              //  isFullOn = false;
//            //}
//
//
//            /*
//            switch(activeState) {
//                case SCORING:
//                    scoringState();
//                    break;
//                case ENDGAME:
//                    endgameState();
//                    break;
//            }
//             */
//
//        }
//
//    }
//
//    @Override
//    public void telemetry(Telemetry telemetry) {
//        //scorer.telemetry(telemetry);
////        db.telemetry(telemetry);
//    }
//
//    /*
//    public void scoringState() {
//        if (handler.INTAKE_IN) {
//            ramp.spinIn();
//        } else if (handler.INTAKE_OUT) {
//            ramp.spinOut();
//        } else {
//            ramp.stopSpin();
//        }
//
//        if (handler.CLOSE_LAUNCH) {
//            launcher.setTargetPower(Launcher.launchPower.FAR);
//        } else {
//            launcher.setTargetPower(Launcher.launchPower.OFF);
//        }
//
//        //if (handler.TOGGLE_ENDGAME) {
//        //    activeState = robotState.ENDGAME;
//        //}
//    }
//
//    public void endgameState() {
//
//        if (handler.TOGGLE_RISE) {
//            if (riser.activeRiserPosition == Riser.riserPosition.DOWN) {
//                riser.setTargetPosition(Riser.riserPosition.UP);
//            } else {
//                riser.setTargetPosition(Riser.riserPosition.DOWN);
//            }
//        }
//
//
//        if (handler.TOGGLE_ENDGAME) {
//            activeState = robotState.SCORING;
//        }
//    }
//
// */
//}
