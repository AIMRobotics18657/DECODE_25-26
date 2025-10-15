package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.control.FeedforwardController;
import com.aimrobotics.aimlib.control.LowPassFilter;
import com.aimrobotics.aimlib.control.PIDController;
import com.aimrobotics.aimlib.control.SimpleControlSystem;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ConfigInfo;
import org.firstinspires.ftc.teamcode.GamepadSettings;

public class Riser extends Mechanism {

    //TODO update constants
    private final double kP = 0.0;
    private final double kI = 0.0;
    private final double kD = 0.0;
    private final double derivativeLowPassGain = 0.0;
    private final double integralSumMax = 0.0;
    private final double kV = 0.0;
    private final double kA = 0.0;
    private final double kStatic = 0.0;
    private final double kCos = 0.0;
    private final double kG = 0.0;
    private final double lowPassGain = 0.0;

    private final SimpleControlSystem controlSystem;

    private DcMotorEx leftRiser;
    private DcMotorEx rightRiser;

    private DcMotorEx activeEncoderMotor;
    private double activeTargetPosition;

    public enum riserPosition {
        DOWN(0),
        UP(500);

        public final int position;
        riserPosition(int position) {
            this.position = position;
        }

    }
    public riserPosition activeRiserPosition = riserPosition.DOWN;

    public Riser() {
        PIDController pidController = new PIDController(kP, kI, kD, derivativeLowPassGain, integralSumMax);
        FeedforwardController feedforwardController = new FeedforwardController(kV, kA, kStatic, kCos, kG);
        LowPassFilter lowPassFilter = new LowPassFilter(lowPassGain);
        controlSystem = new SimpleControlSystem(pidController, feedforwardController, lowPassFilter);
        controlSystem.setTarget(riserPosition.DOWN.position);
    }

    @Override
    public void init(HardwareMap hwMap) {
        rightRiser = hwMap.get(DcMotorEx.class, ConfigInfo.rightRiser.getDeviceName());
        leftRiser = hwMap.get(DcMotorEx.class, ConfigInfo.leftRiser.getDeviceName());

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        activeEncoderMotor = rightRiser;
    }

    @Override
    public void loop(AIMPad aimPad) {
        update();
    }

    public void setMode(DcMotor.RunMode mode) {
        rightRiser.setMode(mode);
        leftRiser.setMode(mode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        rightRiser.setZeroPowerBehavior(behavior);
        leftRiser.setZeroPowerBehavior(behavior);
    }

    public void setPower(double power) {
        rightRiser.setPower(power);
        leftRiser.setPower(power);
    }

    public double getTargetOutputPower() {
        return controlSystem.update(activeEncoderMotor.getCurrentPosition());
    }

    public void update() {
        double power = getTargetOutputPower();
        setPower(power);
    }

    public void setTargetPosition(riserPosition target) {
        activeTargetPosition = target.position;
        activeRiserPosition = target;
        controlSystem.setTarget(activeTargetPosition);
    }

    public double getCurrentPosition() {
        return activeEncoderMotor.getCurrentPosition();
    }

    public boolean isAtTargetPosition () {
        return Math.abs(getCurrentPosition() - activeTargetPosition) < GamepadSettings.PROXIMITY_THRESHOLD;
    }
}
