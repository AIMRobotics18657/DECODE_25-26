package org.firstinspires.ftc.teamcode.OpModes.Auto.Far;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AutoConstants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.RobotV2;

@Autonomous (name="BlueFar")
public class BlueFar extends LinearOpMode {
    boolean isDone = false;
    RobotV2 robot = new RobotV2(AutoConstants.BLUE_FAR_START, true, false);
    public void runOpMode() {
        robot.init(hardwareMap);

        Action initialShoot = robot.db.drive.actionBuilder(AutoConstants.BLUE_FAR_START)
                .splineToLinearHeading(AutoConstants.BLUE_FAR_SHOOT_ONE, Math.toRadians(180))
                .waitSeconds(1)
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(AutoConstants.BLUE_FAR_SHOOT_ONE)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(32, -25, Math.toRadians(-90)), Math.toRadians(-90))//setup(might be hard to stop on a dime here)
                .setTangent(Math.toRadians(-90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(32, -63, Math.toRadians(-90)), Math.toRadians(90))//collect
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(AutoConstants.BLUE_FAR_SHOOT_ONE, Math.toRadians(0))//to shooting
                .waitSeconds(0.1)
                .build();

        Action gateBalls = robot.db.drive.actionBuilder(AutoConstants.BLUE_FAR_SHOOT_ONE)
                //.splineToLinearHeading(new Pose2d(50.8, -40, Math.toRadians(-90)), Math.toRadians(-90))
//                .strafeTo(new Vector2d(59, -65))
//                .strafeTo(new Vector2d(59, -40))
//                .strafeTo(new Vector2d(34, -40))
//                .strafeTo(new Vector2d(34, -65))
//                .strafeTo(new Vector2d(34, -40))
                .setTangent(Math.toRadians(230))
                .splineToLinearHeading(new Pose2d(35, -40, Math.toRadians(340)), Math.toRadians(320))
                //.splineToLinearHeading(new Pose2d(38, -62, Math.toRadians(0)), Math.toRadians(270))
                .strafeTo(new Vector2d(55,-72))
                .strafeTo(new Vector2d(40,-55))
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(62, -66))
                .setTangent(Math.toRadians(90))

                .splineToLinearHeading(AutoConstants.BLUE_FAR_SHOOT_TWO, Math.toRadians(90))
                .waitSeconds(0.1)
                .build();

        Action park = robot.db.drive.actionBuilder(AutoConstants.BLUE_FAR_SHOOT_TWO)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(40, -15, Math.toRadians(180)), Math.toRadians(180))
                .build();

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                robot.scorer.intake.setMode(Intake.IntakeMode.IN);
                                //robot.scorer.launcher.setVelo(295 * 2 * Math.PI / 628);
                                robot.scorer.hood.setPosition((35-32)/(72-32));
                                return !isDone;
                            },
                            new SequentialAction(
                                    initialShoot,
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_FAST);
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_FAST);
                                        robot.shootThreeFar();
                                        return !robot.shootIsDone;
                                    },
                                    getFirstBalls,
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_NORMAL);
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_NORMAL);
                                        robot.shootThreeFar();
                                        return !robot.shootIsDone;
                                    },
                                    gateBalls,
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_NORMAL);
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.scorer.launcher.setVelo(AutoConstants.FAR_LAUNCHER_NORMAL);
                                        robot.shootThreeFar();
                                        return !robot.shootIsDone;
                                    },
                                    park
                            )
                    )
            );
        }
    }
}
