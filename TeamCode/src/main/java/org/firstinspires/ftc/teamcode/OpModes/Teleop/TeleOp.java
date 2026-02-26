package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Settings.InputHandler;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.RobotV2;

/// Figure out groups
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group = "AAA_COMP")
public class TeleOp extends OpMode {

    AIMPad aimPad1;
    AIMPad aimPad2;
    RobotV2 robot = new RobotV2(new Pose2d(0,0,0), false);
    //TODO: learn how save the pose

    @Override
    public void init() {
        aimPad1 = new AIMPad(gamepad1);
        aimPad2 = new AIMPad(gamepad2);
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        aimPad1.update(gamepad1);
        aimPad2.update(gamepad2);
        robot.loop(aimPad1,aimPad2);

        /*
        telemetry.addData("LB Held", aimPad1.isLeftBumperHeld());
        telemetry.addData("LB Pressed", aimPad1.isLeftBumperPressed());
        telemetry.addData("is full on pressed", robot.isFullOn);
        telemetry.addData("active shoot count", robot.scorer.activeShootCount);
        telemetry.addData("current pose", robot.db.drive.localizer.getPose());
        telemetry.addData("launcher power", robot.scorer.launcher.launcher.getPower());
         */
        robot.telemetry(telemetry);
        //telemetry.addData("heading", robot.db.drive.localizer.getPose().heading);
        telemetry.update();
    }

}
