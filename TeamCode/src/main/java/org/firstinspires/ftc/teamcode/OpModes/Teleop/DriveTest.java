package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

@TeleOp(name="drivetest")
@Disabled
public class DriveTest extends OpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    AIMPad aimPad1;
    AIMPad aimPad2;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotorEx.class, ConfigInfo.frontLeft.getDeviceName());
        frontRight = hardwareMap.get(DcMotorEx.class, ConfigInfo.frontRight.getDeviceName());
        backLeft = hardwareMap.get(DcMotorEx.class, ConfigInfo.backLeft.getDeviceName());
        backRight = hardwareMap.get(DcMotorEx.class, ConfigInfo.backRight.getDeviceName());

        aimPad1 = new AIMPad(gamepad1);
        aimPad2 = new AIMPad(gamepad2);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        aimPad1.update(gamepad1);
        aimPad2.update(gamepad2);

        if (aimPad1.isAPressed()) {
            setPower(1);
        } else if (aimPad1.isBPressed()) {
            setPower(.5);
        } else if (aimPad1.isYPressed()) {
            setPower(.25);
        } else if (aimPad1.isXPressed()) {
            setPower(0);
        }

    }

    public void setPower(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
}
