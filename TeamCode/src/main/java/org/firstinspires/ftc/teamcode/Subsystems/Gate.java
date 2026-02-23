package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.OpModes.Teleop.TeleOp;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Gate extends Mechanism {

    public DcMotorEx gate;

    public enum GateMode {
        IN(1),
        OUT(-1),
        OFF(0);

        public final double power;

        GateMode (double power) {this.power = power;}
    }

    public GateMode activeGateMode = GateMode.OFF;

    @Override
    public void init(HardwareMap hwMap) {
        gate = hwMap.get(DcMotorEx.class, ConfigInfo.gate.getDeviceName());
        gate.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        gate.setDirection(DcMotorEx.Direction.REVERSE);
    }

    @Override
    public void loop (AIMPad aimPad) {
        setPower(activeGateMode);
    }

    public void setMode(GateMode mode) {
        activeGateMode = mode;
    }
    private void setPower(GateMode mode) {
        gate.setPower(activeGateMode.power);
    }


    public void telemetry (Telemetry telemetry) {
        telemetry.addData("Gate Mode", activeGateMode);
    }

}
