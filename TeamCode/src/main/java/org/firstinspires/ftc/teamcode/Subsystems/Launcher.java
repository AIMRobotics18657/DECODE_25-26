package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher extends Mechanism {

    DcMotorEx launcherRight;
    DcMotorEx launcherLeft;]

    private double targetPower;


    @Override
    public void init(HardwareMap hwMap) {
        launcherRight = hwMap.get(DcMotorEx.class, "lr");
        launcherLeft = hwMap.get(DcMotorEx.class, "ll");

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop (AIMPad aimPad) {
        setPower();
    }

    private void setMode(DcMotor.RunMode mode) {
        launcherLeft.setMode(mode);
        launcherRight.setMode(mode);
    }

    private void setDirection(DcMotorSimple.Direction direction) {
        launcherLeft.setDirection(direction);
        launcherRight.setDirection(direction);
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        launcherLeft.setZeroPowerBehavior(behavior);
        launcherRight.setZeroPowerBehavior(behavior);
    }

    private void setPower() {
        launcherRight.setPower(targetPower);
        launcherLeft.setPower(targetPower);
    }

    private void setTargetPower (double power) {

    }


}
