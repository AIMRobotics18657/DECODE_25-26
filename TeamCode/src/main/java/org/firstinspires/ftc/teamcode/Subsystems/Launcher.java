package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Launcher extends Mechanism {
    //TODO: add spinup??

    public DcMotorEx launcher;

    private double activeTargetPower = 0;

    public enum launchPower {
        OFF(0),
        FAR(1),
        CLOSE(0.2),
        REVERSE(-1.);

        public final double power;
        launchPower (double power) {this.power = power;};
    }

    public launchPower activeLaunchPower = launchPower.OFF;


    @Override
    public void init(HardwareMap hwMap) {
        launcher = hwMap.get(DcMotorEx.class, ConfigInfo.launcher.getDeviceName());
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop (AIMPad aimPad) {
        setLaunchPower();
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Launch Position:", activeLaunchPower);
        telemetry.addData("Launch Power:", launcher.getPower());
    }
    public void setLaunchPower() {
        launcher.setPower(activeTargetPower);
    }

    public void setTargetPower(launchPower targetPower) {
        activeTargetPower = targetPower.power;
        activeLaunchPower = targetPower;
    }

}
