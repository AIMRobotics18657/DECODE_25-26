package org.firstinspires.ftc.teamcode.Settings;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.aimrobotics.aimlib.gamepad.AIMPad;

public class InputHandler {

    //TODO add rumble

    public boolean INTAKE_IN = false;
    public boolean INTAKE_OUT = false;

    public boolean FAR_LAUNCH = false;
    //public boolean CLOSE_LAUNCH = false;
    public boolean REVERSE_LAUNCH = false;

    //public boolean TOGGLE_RISE = false;

    //public boolean TOGGLE_ENDGAME = false;

    public boolean FULL_ON = false;
    public boolean WIND_UP = false;
    public boolean WIND_UP_FINISHED = false;
    public boolean TOGGLE_DRIVE_SPEED = false;
    public boolean TOGGLE_GATE = false;

    public void updateInputs(AIMPad aimPad1, AIMPad aimPad2) {

        INTAKE_IN = aimPad1.isLeftTriggerHeld();
        INTAKE_OUT = aimPad1.isLeftBumperHeld();

        //CLOSE_LAUNCH = aimPad1.isYPressed();
        FAR_LAUNCH = aimPad1.isRightTriggerHeld();
        REVERSE_LAUNCH = aimPad1.isRightBumperHeld();

        //TOGGLE_ENDGAME = aimPad1.getRightTrigger() > 0.1;

        //TOGGLE_RISE = aimPad1.isAPressed();

        //FULL_ON = aimPad1.isAHeld();
        WIND_UP = aimPad1.isAPressed();
        WIND_UP_FINISHED = aimPad1.isBPressed();

        TOGGLE_DRIVE_SPEED = aimPad1.isYPressed();

        TOGGLE_GATE = aimPad1.isXPressed();



    }
}
