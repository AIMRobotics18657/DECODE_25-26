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

@Autonomous (name="RedFar")
public class RedFar extends LinearOpMode {
    boolean isDone = false;
    RobotV2 robot = new RobotV2(AutoConstants.RED_FAR_START, true, false);
    public void runOpMode() {
        robot.init(hardwareMap);

        Action initialShoot = robot.db.drive.actionBuilder(AutoConstants.RED_FAR_START)
                .splineToLinearHeading(AutoConstants.RED_FAR_SHOOT, Math.toRadians(180))
                .waitSeconds(1)
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(AutoConstants.RED_FAR_SHOOT)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(39, 15, Math.toRadians(90)), Math.toRadians(180))//setup
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(30, 59, Math.toRadians(90)), Math.toRadians(-90))//collect
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(AutoConstants.RED_FAR_SHOOT, Math.toRadians(0))
                .waitSeconds(0.1)
                .build();

        Action gateBalls = robot.db.drive.actionBuilder(AutoConstants.RED_FAR_SHOOT)
                .setTangent(Math.toRadians(-230))
                .splineToLinearHeading(new Pose2d(30, 45, Math.toRadians(-340)), Math.toRadians(-320))
                .strafeTo(new Vector2d(55,55))
                .strafeTo(new Vector2d(40,45))
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(62, 55))
                .setTangent(Math.toRadians(-90))

                .splineToLinearHeading(AutoConstants.RED_FAR_SHOOT, Math.toRadians(180))
                .waitSeconds(0.1)
                .build();

        Action park = robot.db.drive.actionBuilder(AutoConstants.RED_FAR_SHOOT)
                .setTangent(Math.toRadians(180))
                .strafeTo(new Vector2d(40, 15))
                //.splineToLinearHeading(new Pose2d(40, 15, Math.toRadians(180)), Math.toRadians(180))
                .build();

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                robot.scorer.intake.setMode(Intake.IntakeMode.IN);
                                robot.scorer.launcher.setVelo(265 * 2 * Math.PI / 628);
                                robot.scorer.hood.setPosition((35-32)/(72-32));
                                return !isDone;
                            },
                            new SequentialAction(
                                    initialShoot,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.shootThreeFar();
                                        return !robot.shootIsDone;
                                    },
                                    getFirstBalls,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.shootThreeFar();
                                        return !robot.shootIsDone;
                                    },
                                    gateBalls,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
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
