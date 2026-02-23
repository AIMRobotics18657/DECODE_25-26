//package org.firstinspires.ftc.teamcode.Subsystems;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.aimrobotics.aimlib.util.Mechanism;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;
//
//public class Launcher extends Mechanism {
//    //TODO: add spinup??
//
//    public DcMotorEx launcherOne;
//    public DcMotorEx launcherTwo;
//    private DcMotorEx activeEncoderMotor;
//
//    private double activeTargetPower = 0;
//
//    public enum launchPower {
//        OFF(0),
//        FAR(1),
//        CLOSE(0.2),
//        REVERSE(-1.);
//
//        public final double power;
//        launchPower (double power) {this.power = power;};
//    }
//
//    public launchPower activeLaunchPower = launchPower.OFF;
//
//
//    @Override
//    public void init(HardwareMap hwMap) {
//        launcherOne = hwMap.get(DcMotorEx.class, ConfigInfo.launcherOne.getDeviceName());
//        launcherTwo = hwMap.get(DcMotorEx.class, ConfigInfo.launcherTwo.getDeviceName());
//
//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        launcherTwo.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        activeEncoderMotor = launcherOne;
//    }
//
//    public void setMode(DcMotorEx.RunMode mode) {
//        launcherOne.setMode(mode);
//        launcherTwo.setMode(mode);
//    }
//    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
//        launcherOne.setZeroPowerBehavior(behavior);
//        launcherTwo.setZeroPowerBehavior(behavior);
//    }
//
//    @Override
//    public void loop (AIMPad aimPad) {
//        setLaunchPower();
//    }
//
//    @Override
//    public void telemetry(Telemetry telemetry) {
//        telemetry.addData("Launch Position:", activeLaunchPower);
//        telemetry.addData("Launch Power:", activeEncoderMotor.getPower());
//    }
//    public void setLaunchPower() {
//        launcherOne.setPower(activeTargetPower);
//        launcherTwo.setPower(activeTargetPower);
//    }
//
//    public void setTargetPower(launchPower targetPower) {
//        activeTargetPower = targetPower.power;
//        activeLaunchPower = targetPower;
//    }
//
//}
