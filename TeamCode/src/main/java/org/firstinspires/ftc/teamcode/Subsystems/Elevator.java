package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator extends Mechanism {

    private CRServo elevatorLower;

    private CRServo elevatorUpper;

    public void init(HardwareMap hwMap) {
        elevatorLower = hwMap.get(CRServo.class, "el");
        elevatorUpper = hwMap.get(CRServo.class, "eu");

    }

    public void spinIn () {
        elevatorLower.setPower(1);
        elevatorUpper.setPower(1);
    }

    public void spinOut () {
        elevatorLower.setPower(-1);
        elevatorUpper.setPower(-1);
    }

}
