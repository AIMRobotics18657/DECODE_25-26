package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Launcher;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

public class BlueSide12 extends LinearOpMode {
    public AutoConstants constants;

    public Robot robot = new Robot(constants.BLUE_START, true);
    public boolean isDone = false;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        Action TurnShoot = robot.db.drive.actionBuilder(constants.BLUE_START)
                .turnTo(constants.BLUE_SHOOTING_ANGLE)
                .build();


        while (opModeIsActive()) {
            Actions.runBlocking(
                    new ParallelAction(
                            (telemetryPacket) -> { // Drop Purple
                                robot.loop(new AIMPad(gamepad1), new AIMPad(gamepad2));
                                return !isDone;
                            },
                            new SequentialAction(
                                    TurnShoot,
                                    (telemetryPacket) -> { // Clip preload
                                        robot.launcher.setTargetPower(Launcher.launchPower.CLOSE);
                                        return false; //TODO find a way to figure out if all the balls have been launched
                                    }
                            )
                    )
            );

        }
    }
}
