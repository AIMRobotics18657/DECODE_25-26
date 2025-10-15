package org.firstinspires.ftc.teamcode.Trash;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.subsystems.sds.ServoState;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Revolver extends Mechanism {

    private DcMotorEx revolver;
    private StateDrivenServo trapdoor;

    ServoState CLOSED = new ServoState(0);
    ServoState OPEN = new ServoState(.5);

    public enum RevolverRotation {
        
    }

    public void init(HardwareMap hwMap) {
        revolver = hwMap.get(DcMotorEx.class, "rev");
        trapdoor = new StateDrivenServo(new ServoState[]{CLOSED, OPEN}, OPEN, "trap");
    }

    public void loop(AIMPad aimPad) {

    }
}
