package org.firstinspires.ftc.teamcode.OpModes;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/// Figure out groups
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="")
public class TeleOp extends OpMode {

    AIMPad aimPad1;
    AIMPad aimPad2;

    @Override
    public void init() {
        aimPad1 = new AIMPad(gamepad1);
        aimPad2 = new AIMPad(gamepad2);

    }

    @Override
    public void loop() {
        aimPad1.update(gamepad1);
        aimPad2.update(gamepad2);

    }

}
