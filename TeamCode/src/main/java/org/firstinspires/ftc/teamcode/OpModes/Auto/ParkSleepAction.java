package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@Autonomous (name="parkSleepAction", preselectTeleOp = "TeleOp", group = "AAA_COMP")
public class ParkSleepAction extends LinearOpMode {
    Robot robot = new Robot(new Pose2d(0, 0, 90), true);
    Drivebase db = new Drivebase(new Pose2d(0, 0, 90));
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        db.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            db.loop(new AIMPad(gamepad1), true);
            Actions.runBlocking(
                    new SleepAction(29.)
            );
            timer.reset();
            if (timer.milliseconds() < 1000) {

//                db.setAutoDrivePowers(0, .5 , 0);
                db.loop(new AIMPad(gamepad1), true);
            }
            telemetry.addData("timer", timer.milliseconds());
            telemetry.update();
        }
    }
}
