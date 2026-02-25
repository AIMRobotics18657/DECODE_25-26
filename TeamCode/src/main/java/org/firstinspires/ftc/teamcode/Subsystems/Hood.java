package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Hood extends Mechanism {

    public Servo hood;

    @Override
    public void init(HardwareMap hwMap) {
        hood = hwMap.get(Servo.class, ConfigInfo.hood.getDeviceName());
        //hood.scaleRange(0, 0.53);
        hood.scaleRange(0, .53);
        hood.setPosition(0);
    }

    @Override
    public void loop (AIMPad aimPad) {

    }

    @Override
    public void telemetry(Telemetry telemetry){
        telemetry.addData("Hood Position", hood.getPosition());
        telemetry.addData("hood deg", hood.getPosition() * 0.02085714285);
    }
}
