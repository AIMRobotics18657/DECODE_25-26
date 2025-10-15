package org.firstinspires.ftc.teamcode;

import com.aimrobotics.aimlib.gamepad.AIMPad;

public class InputHandler {

    //TODO add rumble

    public boolean SPIN_IN = false;
    public boolean SPIN_OUT = false;

    public boolean FAR_LAUNCH = false;
    public boolean CLOSE_LAUNCH = false;

    public boolean TOGGLE_RISE = false;

    public boolean TOGGLE_ENDGAME = false;

    public void updateInputs(AIMPad aimPad1, AIMPad aimPad2) {

        SPIN_IN = aimPad1.isAPressed();
        SPIN_OUT = aimPad1.isBPressed();

        FAR_LAUNCH = aimPad1.isYPressed();
        CLOSE_LAUNCH = aimPad1.isXPressed();

        TOGGLE_ENDGAME = aimPad1.getRightTrigger() > 0.1;

        TOGGLE_RISE = aimPad1.isAPressed();
    }
}
