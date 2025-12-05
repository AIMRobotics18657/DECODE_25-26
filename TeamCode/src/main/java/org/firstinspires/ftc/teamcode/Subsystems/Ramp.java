package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Ramp extends Mechanism {

    private DcMotorEx ramp;
    private Servo gate;
    public boolean isGateOpen;

    public void init(HardwareMap hwMap) {
        ramp = hwMap.get(DcMotorEx.class, ConfigInfo.ramp.getDeviceName());
        gate = hwMap.get(Servo.class, ConfigInfo.gate.getDeviceName());
        isGateOpen = false;
    }

    public void spinIn() {
        ramp.setPower(1);
    }

    public void spinOut() {
        ramp.setPower(-1);
    }

    public void stopSpin() {
        ramp.setPower(0);
    }

    @Override
    public void telemetry (Telemetry telemetry) {
        telemetry.addData("Ramp Power: ", ramp.getPower());
    }

    public void openGate() {
        gate.setPosition(.25);
        isGateOpen = true;
    }

    public void closeGate() {
        gate.setPosition(0);
        isGateOpen = false;
    }
}
