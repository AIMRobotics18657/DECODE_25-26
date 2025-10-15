package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ConfigInfo;

public class Ramp extends Mechanism {

    private DcMotorEx ramp;

    public void init(HardwareMap hwMap) {
        ramp = hwMap.get(DcMotorEx.class, ConfigInfo.ramp.getDeviceName());
    }

    public void spinIn() {
        ramp.setPower(1);
    }

    public void spinOut() {
        ramp.setPower(0);
    }

    public void stopSpin() {
        ramp.setPower(0);
    }

    @Override
    public void telemetry (Telemetry telemetry) {
        telemetry.addData("Ramp Power: ", ramp.getPower());
    }
}
