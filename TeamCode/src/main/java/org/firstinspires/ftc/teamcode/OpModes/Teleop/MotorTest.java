package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="motor test")
@Disabled
public class MotorTest extends OpMode {

    public DcMotor motor;

    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");

    }

    public void loop() {
        if(gamepad1.a) {
            motor.setPower(1);
        } else if (gamepad1.b) {
            motor.setPower(.5);
        }
        else {
            motor.setPower(0);
        }
    }
}
