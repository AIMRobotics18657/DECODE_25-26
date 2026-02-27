package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.RobotV2;

@Autonomous (name="red auto")
public class AutonRed extends LinearOpMode {
    RobotV2 robot = new RobotV2(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135)), true);

    boolean isDone = false;


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        Action initialShoot = robot.db.drive.actionBuilder(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135)))
                .strafeTo(new Vector2d(0, 0))
                .waitSeconds(3)
                .setTangent(Math.toRadians(90))
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(135)))
                .splineToLinearHeading(new Pose2d(-11.5, 25, Math.toRadians(90)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-11.5, 53, Math.toRadians(90)), Math.toRadians(90))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)), Math.toRadians(270))
                .waitSeconds(3)
                .build();

        Action getSecondBalls = robot.db.drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(135)))
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(new Pose2d(12, 25, Math.toRadians(90)), Math.toRadians(90)) // setup position
                .splineToLinearHeading(new Pose2d(12, 53, Math.toRadians(90)), Math.toRadians(90)) // push into wall has to be 90
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)), Math.toRadians(270)) // go to shooting
                .waitSeconds(3)
                .build();

        Action getThirdBalls = robot.db.drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(135)))
                .setTangent(Math.toRadians(10))
                .splineToLinearHeading(new Pose2d(36, 25, Math.toRadians(90)), Math.toRadians(15))//setup(might be hard to stop on a dime here)
                .setTangent(Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(36, 53, Math.toRadians(90)), Math.toRadians(90))//collect
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)), Math.toRadians(230))//to shooting
                .waitSeconds(3)
                .build();

        Action park = robot.db.drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(135)))
                .setTangent(Math.toRadians(320))
                .splineToLinearHeading(new Pose2d(38, -33, Math.toRadians(90)), Math.toRadians(330))
                .build();


        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                return !isDone;
                            },
                            new SequentialAction(
                                    initialShoot,
                                    (telemetryPacket) -> {
                                      robot.setShoot();
                                      return false;
                                    },
                                    (telemetryPacket) -> { // Shoot 3
                                        robot.shootThree();
                                        return !robot.shootIsDone;
                                    },
                                    new ParallelAction(
                                            getFirstBalls,
                                            (telemetryPacket) -> {
                                                robot.scorer.intake.setMode(Intake.IntakeMode.IN);
                                                return false;
                                            }
                                    ),//TODO make all get balls into parallel actions
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
                                    getThirdBalls,
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
