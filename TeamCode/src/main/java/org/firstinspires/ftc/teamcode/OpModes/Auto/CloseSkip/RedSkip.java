package org.firstinspires.ftc.teamcode.OpModes.Auto.CloseSkip;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.RobotV2;

@Autonomous(name="red skip")
public class RedSkip extends LinearOpMode {

    RobotV2 robot = new RobotV2(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135)), true, true);

    boolean isDone = false;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        Action initialShoot = robot.db.drive.actionBuilder(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135)))
                .strafeTo(new Vector2d(-15, 15))
                .waitSeconds(0.1)
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(new Pose2d(-15, 15, Math.toRadians(135)))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-5.5, 25, Math.toRadians(90)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-5.5, 53, Math.toRadians(90)), Math.toRadians(90)) // push into wall
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-15, 15, Math.toRadians(135)), Math.toRadians(-90)) // go to shooting
                .waitSeconds(0.1) // shoot
                .build();


        Action getSecondBalls = robot.db.drive.actionBuilder(new Pose2d(-15, -15, Math.toRadians(-135)))
                .setTangent(Math.toRadians(45))
                .strafeTo(new Vector2d(12,25))//issues here
                .turnTo(Math.toRadians(90))// and here, makes a big circle instead of strafing, why? cuz we set the tangent?
                .splineToLinearHeading(new Pose2d(12, 64, Math.toRadians(90)), Math.toRadians(90)) // push into wall has to be 90
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(12, 33))
                .splineToLinearHeading(new Pose2d(-15, 15, Math.toRadians(135)), Math.toRadians(-90)) // go to shooting
                .waitSeconds(0.1)
                .build();

        Action park = robot.db.drive.actionBuilder(new Pose2d(-15, 15, Math.toRadians(135)))
                .setTangent(Math.toRadians(320))
                .strafeTo(new Vector2d(35, 45))
                .build();



        waitForStart();
        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                robot.scorer.intake.setMode(Intake.IntakeMode.IN);
                                robot.scorer.launcher.setVelo(190 * 2 * Math.PI / 628);
                                return !isDone;
                            }, new SequentialAction(
                            initialShoot,
                            (telemetryPacket) -> {
                                robot.setShoot();
                                return false;
                            },
                            (telemetryPacket) -> { // Shoot 3
                                robot.shootThree();
                                return !robot.shootIsDone;
                            },
                            getFirstBalls,
                            (telemetryPacket) -> {
                                robot.setShoot();
                                return false;
                            },
                            (telemetryPacket) -> { // Shoot 3
                                robot.shootThree();
                                return !robot.shootIsDone;
                            },
                            getSecondBalls,
                            (telemetryPacket) -> {
                                robot.setShoot();
                                return false;
                            },
                            (telemetryPacket) -> { // Shoot 3
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
