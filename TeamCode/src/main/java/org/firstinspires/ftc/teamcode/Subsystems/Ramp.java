package org.firstinspires.ftc.teamcode.Subsystems;

import android.graphics.Region;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.subsystems.sds.ServoState;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Ramp extends Mechanism {

    private DcMotorEx ramp;
    public StateDrivenServo gate;
    public boolean isGateOpen;

    ServoState OPEN = new ServoState(0.0 );
    ServoState CLOSED = new ServoState(.4);

    public void init(HardwareMap hwMap) {
        ramp = hwMap.get(DcMotorEx.class, ConfigInfo.ramp.getDeviceName());
        gate = new StateDrivenServo(new ServoState[]{OPEN, CLOSED}, CLOSED, ConfigInfo.gate.getDeviceName(), Servo.Direction.REVERSE);
        isGateOpen = false;

        gate.init(hwMap);

    }

    @Override
    public void loop(AIMPad aimpad) {
        gate.loop(aimpad);
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
        gate.telemetry(telemetry);
    }

    public void openGate() {
        gate.setActiveTargetState(OPEN);
        isGateOpen = true;
    }

    public void closeGate() {
        gate.setActiveTargetState(CLOSED);
        isGateOpen = false;
    }

    public void toggleGate() {
        if (isGateOpen) {
            closeGate();
        } else {
            openGate();
        }
    }

    public void custom(double position) {
        gate.setActiveStateCustom(position);
    }
}
