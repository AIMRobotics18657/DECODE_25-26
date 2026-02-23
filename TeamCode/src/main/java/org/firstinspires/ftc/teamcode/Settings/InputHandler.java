package org.firstinspires.ftc.teamcode.Settings;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.aimrobotics.aimlib.gamepad.AIMPad;

public class InputHandler {

    //TODO add rumble

    public boolean INTAKE_IN = false;
    public boolean INTAKE_OUT = false;
    public boolean GRANT_INTAKE_IN = false;
    public boolean GRANT_INTAKE_OUT = false;

    public boolean FAR_LAUNCH = false;
    public boolean CLOSE_LAUNCH = false;
    //public boolean CLOSE_LAUNCH = false;
    public boolean REVERSE_LAUNCH = false;

    public boolean HOOD_UP = false;
    public boolean HOOD_DOWN = false;
    public boolean FULL_UP = false;
    public boolean FULL_DOWN = false;


    //public boolean TOGGLE_RISE = false;

    //public boolean TOGGLE_ENDGAME = false;

    public boolean FULL_ON = false;
    public boolean WIND_UP = false;
    public boolean WIND_UP_FINISHED = false;
    public boolean TOGGLE_DRIVE_SPEED = false;
    public boolean GATE_IN = false;
    public boolean GATE_OUT = false;

    public void updateInputs(AIMPad aimPad1, AIMPad aimPad2) {

        INTAKE_IN = aimPad2.isLeftTriggerHeld();
        INTAKE_OUT = aimPad2.isLeftBumperHeld();

        GRANT_INTAKE_IN = aimPad1.isLeftTriggerHeld();
        GRANT_INTAKE_OUT = aimPad1.isLeftBumperHeld();


        CLOSE_LAUNCH = aimPad1.isXPressed();//aimPad1.getRightTrigger() > .1;//aimPad1.isRightTriggerHeld();
        FAR_LAUNCH = aimPad1.isLeftBumperPressed();//aimPad1.isRightTriggerHeld();
        REVERSE_LAUNCH = //aimPad1.isRightBumperHeld();

        HOOD_UP = aimPad1.isDPadUpPressed();
        HOOD_DOWN = aimPad1.isDPadDownPressed();
        FULL_UP = aimPad1.isDPadRightPressed();
        FULL_DOWN = aimPad1.isDPadLeftPressed();

        GATE_IN = aimPad1.isAHeld();
        GATE_OUT = aimPad1.isBHeld();

        //TOGGLE_ENDGAME = aimPad1.getRightTrigger() > 0.1;

        //TOGGLE_RISE = aimPad1.isAPressed();

        //FULL_ON = aimPad1.isAHeld();
//        WIND_UP = aimPad1.isAPressed();
//        WIND_UP_FINISHED = aimPad1.isBPressed();
//
//        TOGGLE_DRIVE_SPEED = aimPad1.isYPressed();
//
//        TOGGLE_GATE = aimPad2.isXPressed();



    }
}
