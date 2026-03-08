package org.firstinspires.ftc.teamcode.OpModes.Auto.Close;

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

@Autonomous (name="blue auto")
public class AutonBlue extends LinearOpMode {
    RobotV2 robot = new RobotV2(AutoConstants.BLUE_START, true, false);

    boolean isDone = false;


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        Action initialShoot = robot.db.drive.actionBuilder(AutoConstants.BLUE_START)
                .strafeTo(AutoConstants.BLUE_SHOOT_VECTOR)
                .waitSeconds(0.1)
                .setTangent(Math.toRadians(-90))
                .build();

        Action getFirstBalls = robot.db.drive.actionBuilder(AutoConstants.BLUE_SHOOT)
                .splineToLinearHeading(AutoConstants.BLUE_LINE_ONE_SETUP, Math.toRadians(-90))
                .splineToLinearHeading(AutoConstants.BLUE_LINE_ONE_WALL, Math.toRadians(-90)) // push into wall
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(AutoConstants.BLUE_SHOOT, Math.toRadians(90)) // go to shooting
                .waitSeconds(0.1) // shoot
                .build();

        Action getSecondBalls = robot.db.drive.actionBuilder(AutoConstants.BLUE_SHOOT)
                .setTangent(Math.toRadians(-45))
                .splineToLinearHeading(AutoConstants.BLUE_LINE_TWO_SETUP, Math.toRadians(-90)) // setup position
                .splineToLinearHeading(AutoConstants.BLUE_LINE_TWO_WALL, Math.toRadians(-90)) // push into wall has to be 90
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(AutoConstants.BLUE_SHOOT, Math.toRadians(90)) // go to shooting
                .waitSeconds(0.1)
                .build();

        Action getThirdBalls = robot.db.drive.actionBuilder(AutoConstants.BLUE_SHOOT)
                .setTangent(Math.toRadians(-10))
                .splineToLinearHeading(AutoConstants.BLUE_LINE_THREE_SETUP, Math.toRadians(-15))//setup(might be hard to stop on a dime here)
                .setTangent(Math.toRadians(-90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(AutoConstants.BLUE_LINE_THREE_WALL, Math.toRadians(-90))//collect
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(AutoConstants.BLUE_SHOOT, Math.toRadians(-230))//to shooting
                .waitSeconds(0.1)
                .build();

        Action park = robot.db.drive.actionBuilder(AutoConstants.BLUE_SHOOT)
                .setTangent(Math.toRadians(-320))
                .splineToLinearHeading(new Pose2d(38, -33, Math.toRadians(-90)), Math.toRadians(-330))
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
                                    getFirstBalls,
                                    //TODO make all get balls into parallel actions
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