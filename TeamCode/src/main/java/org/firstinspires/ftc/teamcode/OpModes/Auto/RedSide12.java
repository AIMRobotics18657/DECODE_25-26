//package org.firstinspires.ftc.teamcode.OpModes.Auto;
//
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Launcher;
//import org.firstinspires.ftc.teamcode.Subsystems.Robot;
//import org.firstinspires.ftc.teamcode.Subsystems.ScoringAssembly;
//
//@Autonomous (name="Red Side 12", group = "AAA_COMP", preselectTeleOp="TeleOp")
//public class RedSide12 extends LinearOpMode {
//    //public AutoConstants constants = new AutoConstants();
//
//    public Robot robot = new Robot(AutoConstants.RED_START, true);
//    public boolean isDone = false;
//    public boolean secondShootStarted = false;
//    public boolean firstShootStarted = false;
//    ElapsedTime timer = new ElapsedTime();
//    public boolean started = false;
//
//
//    @Override
//    public void runOpMode() {
//        robot.init(hardwareMap);
//
//        Action TurnShoot = robot.db.drive.actionBuilder(AutoConstants.RED_START)
//                .turnTo(AutoConstants.RED_SHOOTING_ANGLE)
//                .build();
//
//        Action lineOne = robot.db.drive.actionBuilder(AutoConstants.RED_SHOOTING)
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(AutoConstants.RED_LINE_ONE_SETUP, Math.toRadians(90))
//                .splineToLinearHeading(AutoConstants.RED_LINE_ONE_WALL, Math.toRadians(90))
//                .build();
//
//        Action secondShoot = robot.db.drive.actionBuilder(AutoConstants.RED_LINE_ONE_WALL)
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(AutoConstants.RED_SHOOTING, Math.toRadians(270))
//                .build();
//
//        Action lineTwo = robot.db.drive.actionBuilder(AutoConstants.RED_SHOOTING)
//                .setTangent(Math.toRadians(45))
//                .splineToLinearHeading(AutoConstants.RED_LINE_TWO_SETUP, Math.toRadians(90)) // setup position
//                .splineToLinearHeading(AutoConstants.RED_LINE_TWO_WALL, Math.toRadians(90))
//                .build();
//
//        Action thirdShoot = robot.db.drive.actionBuilder(AutoConstants.RED_LINE_TWO_WALL)
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(AutoConstants.RED_SHOOTING, Math.toRadians(270))
//                .build();
//
//        Action lineThree = robot.db.drive.actionBuilder(AutoConstants.RED_SHOOTING)
//                .setTangent(Math.toRadians(10))
//                .splineToLinearHeading(AutoConstants.RED_LINE_THREE_SETUP, Math.toRadians(15))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(AutoConstants.RED_LINE_THREE_WALL, Math.toRadians(90))
//                .build();
//
//        Action fourthShoot = robot.db.drive.actionBuilder(AutoConstants.RED_LINE_THREE_WALL)
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(AutoConstants.RED_SHOOTING, Math.toRadians(230))
//                .build();
//
//        Action park = robot.db.drive.actionBuilder(AutoConstants.RED_SHOOTING)
//                .setTangent(Math.toRadians(320))
//                .splineToLinearHeading(AutoConstants.RED_PARK, Math.toRadians(330))
//                .build();
//
//        Action park2 = robot.db.drive.actionBuilder(AutoConstants.RED_SHOOTING)
//                        .splineToLinearHeading(AutoConstants.RED_LINE_TWO_SETUP, Math.toRadians(90))
//                                .build();
//
//        Action back20 = robot.db.drive.actionBuilder(new Pose2d(0, 0, 90))
//                .splineToLinearHeading(new Pose2d(0, -20, 0), Math.toRadians(90))
//                        .build();
//        Action Turn45 = robot.db.drive.actionBuilder(new Pose2d(0, 0, 90))
//                        .turn(45)
//                                .build();
//
//        telemetry.addLine("INIT OK, waiting for start...");
//        telemetry.update();
//        waitForStart(); //essential
//
//
//        while (opModeIsActive()) {
//            Actions.runBlocking(
//                    new ParallelAction(
//                            (telemetryPacket) -> {
//                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
//                                robot.scorer.ramp.openGate(); //switched
//                                telemetry.addLine("robot loop executed");
//                                telemetry.addData("is shooting finished?", robot.scorer.shootingFinished);
//                                telemetry.update();
//                                return !isDone;
//                            },
//                            new SequentialAction(
//                                    back20,
//                                    Turn45,
//                                    (telemetryPacket) -> {
//                                        robot.scorer.loop(new AIMPad(gamepad1));
//
//                                        if (!started) {
//                                            robot.scorer.launcher.setTargetPower(Launcher.launchPower.FAR);
//                                            robot.scorer.ramp.spinIn();
//                                            robot.scorer.ramp.openGate(); //switched
//                                            timer.reset();
//                                            started = true;
//                                        }
//                                        if (timer.milliseconds() < 1000) {
//                                            robot.scorer.launcher.setTargetPower(Launcher.launchPower.FAR);
//                                            robot.scorer.ramp.spinIn();
//                                            return true;
//                                        }
//
//                                        if (timer.milliseconds() > 1000 && timer.milliseconds() < 2000) {
//                                            robot.scorer.ramp.closeGate(); //switched
//                                            robot.scorer.launcher.setTargetPower(Launcher.launchPower.FAR);
//                                            robot.scorer.ramp.spinIn();
//                                            return true;
//                                            }
//
//                                        if (timer.milliseconds() > 2000) {
//                                            robot.scorer.launcher.setTargetPower(Launcher.launchPower.OFF);
//                                            robot.scorer.ramp.stopSpin();
//                                            return false;
//                                        }
//
//                                        telemetry.addData("timer", timer.milliseconds());
//                                        telemetry.update();
//                                        return false;
//                                    }
//
//
//                            ),
//                            (telemetryPacket) -> { // End Auto
//                                isDone = true;
//                                return false;
//                            }
//                    )
//            );
//            break;
//        }
//
//
//
//    }
//}
