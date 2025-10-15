package org.firstinspires.ftc.teamcode.OpModes;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/// Figure out groups
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="")
public class TeleOp extends OpMode {

    AIMPad aimPad1;
    AIMPad aimPad2;
    Robot robot;

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
    }

}
