package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp (name="single motor velo shoot")
public class singlemotorveloshoot extends OpMode {

    DcMotorEx launcher;

    public boolean isVelo;
    public boolean isPower;
    AIMPad aimPad;

    @Override
    public void init() {
        launcher = hardwareMap.get(DcMotorEx.class, "la");
        launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        aimPad = new AIMPad(gamepad1);
    }


    @Override
    public void loop() {
        if (gamepad1.a) {
            launcher.setVelocity(20, AngleUnit.RADIANS);
            isVelo = true;
            isPower = false;
        } else if (gamepad1.b) {
            launcher.setPower(.5);
            isVelo = false;
            isPower = true;
        } else {
            launcher.setPower(0);
            isVelo = false;
            isPower = false;
        }

        telemetry.addData("is velo?", isVelo);
        telemetry.addData("is power?", isPower);
    }
}
