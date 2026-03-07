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

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.RobotV2;

@Autonomous (name="RedFar")
public class RedFar extends LinearOpMode {
    double SHOOTING_ANGLE = (Math.PI) - Math.atan((double) 44 / 118.5);
    Pose2d SHOOTING_POS = new Pose2d(55, 15, SHOOTING_ANGLE);
    RobotV2 robot = new RobotV2(SHOOTING_POS, true, true);
    boolean isDone = false;
    public void runOpMode() {
        robot.init(hardwareMap);
        Action initialShoot = robot.db.drive.actionBuilder(SHOOTING_POS)
                .waitSeconds(0.1)
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(SHOOTING_POS)
                .setTangent(Math.toRadians(-10))
                .splineToLinearHeading(new Pose2d(36, 25, Math.toRadians(90)), Math.toRadians(90))//setup(might be hard to stop on a dime here)
                .setTangent(Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(36, 53, Math.toRadians(90)), Math.toRadians(-90))//collect
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(0))//to shooting
                .waitSeconds(0.1)
                .build();

        Action gateBalls = robot.db.drive.actionBuilder(SHOOTING_POS)
                .splineToLinearHeading(new Pose2d(50.8, 40, Math.toRadians(90)), Math.toRadians(90))
                .strafeTo(new Vector2d(50.8, 57.5))
                .strafeTo(new Vector2d(50.8, 40))
                .strafeTo(new Vector2d(27.5, 40))
                .strafeTo(new Vector2d(27.5, 57.5))
                .strafeTo(new Vector2d(27.5, 40))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(-90))
                .waitSeconds(0.1)
                .build();

        Action park = robot.db.drive.actionBuilder(SHOOTING_POS)
                .setTangent(Math.toRadians(-320))
                .splineToLinearHeading(new Pose2d(38, 33, Math.toRadians(90)), Math.toRadians(90))
                .build();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                robot.scorer.intake.setMode(Intake.IntakeMode.IN);
                                robot.scorer.launcher.setVelo(190 * 2 * Math.PI / 628);
                                return !isDone;
                            },
                            new SequentialAction(
                                    initialShoot,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.shootThree();
                                        return !robot.shootIsDone;
                                    },
                                    getFirstBalls,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.shootThree();
                                        return !robot.shootIsDone;
                                    },
                                    gateBalls,
                                    (telemetryPacket) -> {
                                        robot.setShoot();
                                        return false;
                                    },
                                    (telemetryPacket) -> {
                                        robot.shootThree();
                                        return !robot.shootIsDone;
                                    },
                                    park
                            )
                    )
            );
        }
    }
}
