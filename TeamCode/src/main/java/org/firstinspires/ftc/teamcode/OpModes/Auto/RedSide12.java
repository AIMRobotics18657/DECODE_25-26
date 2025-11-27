package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Launcher;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.ScoringAssembly;

@Autonomous (name="Red Side 12", group = "AAA_COMP", preselectTeleOp="TeleOp")
public class RedSide12 extends LinearOpMode {
    public AutoConstants constants;

    public Robot robot = new Robot(constants.RED_START, true);
    public boolean isDone = false;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        Action TurnShoot = robot.db.drive.actionBuilder(constants.RED_START)
                .turnTo(constants.RED_SHOOTING_ANGLE)
                .build();

        Action lineOne = robot.db.drive.actionBuilder((constants.RED_SHOOTING))
                .setTangent(90)
                .splineToLinearHeading(constants.RED_LINE_ONE_SETUP, Math.toRadians(90))
                .splineToLinearHeading(constants.RED_LINE_ONE_WALL, Math.toRadians(90))
                .build();

        Action secondShoot = robot.db.drive.actionBuilder(constants.RED_LINE_ONE_WALL)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(constants.RED_SHOOTING, Math.toRadians(270))
                .build();

        Action lineTwo = robot.db.drive.actionBuilder(constants.RED_SHOOTING)
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(constants.RED_LINE_TWO_SETUP, Math.toRadians(90)) // setup position
                .splineToLinearHeading(constants.RED_LINE_TWO_WALL, Math.toRadians(90))
                .build();

        Action thirdShoot = robot.db.drive.actionBuilder(constants.RED_LINE_TWO_WALL)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(constants.RED_SHOOTING, Math.toRadians(270))
                .build();

        Action lineThree = robot.db.drive.actionBuilder(constants.RED_SHOOTING)
                .setTangent(Math.toRadians(10))
                .splineToLinearHeading(constants.RED_LINE_THREE_SETUP, Math.toRadians(15))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(constants.RED_LINE_THREE_WALL, Math.toRadians(90))
                .build();

        Action fourthShoot = robot.db.drive.actionBuilder(constants.RED_LINE_THREE_WALL)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(constants.RED_SHOOTING, Math.toRadians(230))
                .build();

        Action park = robot.db.drive.actionBuilder(constants.RED_SHOOTING)
                .setTangent(Math.toRadians(320))
                .splineToLinearHeading(constants.RED_PARK, Math.toRadians(330))
                .build();


        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> {
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                return !isDone;
                            },
                            new SequentialAction(
                                    TurnShoot,
                                    (telemetryPacket) -> { // 1st shoot
                                        robot.scorer.startShootThree();
                                        return robot.scorer.activeShootCount == ScoringAssembly.ShootCount.THREE;
                                    },
                                    lineOne,
                                    secondShoot,
                                    (telemetryPacket) -> { // 2nd shoot
                                        robot.scorer.startShootThree();
                                        return robot.scorer.activeShootCount == ScoringAssembly.ShootCount.THREE;
                                    },
                                    lineTwo,
                                    thirdShoot,
                                    (telemetryPacket) -> { // 3rd shoot
                                        robot.scorer.startShootThree();
                                        return robot.scorer.activeShootCount == ScoringAssembly.ShootCount.THREE;
                                    },
                                    lineThree,
                                    fourthShoot,
                                    (telemetryPacket) -> { // Clip preload
                                        robot.scorer.startShootThree();
                                        return robot.scorer.activeShootCount == ScoringAssembly.ShootCount.THREE;
                                    },
                                    park
                            ),
                            (telemetryPacket) -> { // End Auto
                                isDone = true;
                                return false;
                            }
                    )
            );
            break;
        }
    }
}
