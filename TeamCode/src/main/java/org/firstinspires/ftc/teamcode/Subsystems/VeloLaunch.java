package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class VeloLaunch extends Mechanism {
    //TODO: add spinup??

    public DcMotorEx launcherOne;
    public DcMotorEx launcherTwo;
    @Override
    public void init(HardwareMap hwMap) {
        launcherOne = hwMap.get(DcMotorEx.class, ConfigInfo.launcherOne.getDeviceName());
        launcherTwo = hwMap.get(DcMotorEx.class, ConfigInfo.launcherTwo.getDeviceName());

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcherTwo.setDirection(DcMotorEx.Direction.REVERSE);

        launcherTwo.setVelocityPIDFCoefficients(1000,0,0, 15);
        launcherOne.setVelocityPIDFCoefficients(1000,0,0, 15);


    }

    private void setMode(DcMotorEx.RunMode mode) {
        launcherOne.setMode(mode);
        launcherTwo.setMode(mode);
    }
    private void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
        launcherOne.setZeroPowerBehavior(behavior);
        launcherTwo.setZeroPowerBehavior(behavior);
    }

    @Override
    public void loop (AIMPad aimPad) {

    }

    public void setVelo(double omega) {
        launcherOne.setVelocity(omega, AngleUnit.RADIANS);
        launcherTwo.setVelocity(omega, AngleUnit.RADIANS); //TODO add system to make it so i can see when it is at that velo
    }

    @Override
    public void telemetry(Telemetry telemetry) {
//        telemetry.addData("Velocity", launcherOne.getVelocity(AngleUnit.RADIANS));
//        telemetry.addData("Encoder ticks", launcherOne.getCurrentPosition());
//
//        PIDFCoefficients coeffs = launcherTwo.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        telemetry.addData("P", coeffs.p);
//        telemetry.addData("I", coeffs.i);
//        telemetry.addData("D", coeffs.d);
//        telemetry.addData("F", coeffs.f);
//        telemetry.update();
    }
}