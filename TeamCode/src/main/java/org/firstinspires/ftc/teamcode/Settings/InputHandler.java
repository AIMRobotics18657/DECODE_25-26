package org.firstinspires.ftc.teamcode.Settings;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.aimrobotics.aimlib.gamepad.AIMPad;

public class InputHandler {

    // GRANT CONTROLS
    public boolean INTAKE_IN = false;
    public boolean INTAKE_OUT = false;

    public boolean GATE_IN = false;
    public boolean GATE_OUT = false;

    public boolean AUTO_ADJUST = false;
    public boolean GRANT_LAUNCHER_OFF = false;

    public boolean GRANT_HOOD_UP = false;
    public boolean GRANT_HOOD_DOWN = false;

    // JOLIE CONTROLS

    public boolean FAR_LAUNCH = false;
    public boolean CLOSE_LAUNCH = false;
    public boolean REVERSE_LAUNCH = false;
    public boolean JOLIE_LAUNCHER_OFF = false;


    public boolean JOLIE_HOOD_UP = false;
    public boolean JOLIE_HOOD_DOWN = false;

    public boolean TOGGLE_DRIVE_SPEED = false;


    public void updateInputs(AIMPad aimPad1, AIMPad aimPad2) {

        // GRANT CONTROLS

        INTAKE_IN = aimPad1.isLeftTriggerHeld();
        INTAKE_OUT = aimPad1.isLeftBumperHeld();

        GATE_IN = aimPad1.isRightTriggerHeld();
        GATE_OUT = aimPad1.isRightBumperHeld();

        AUTO_ADJUST = aimPad1.isXPressed();
        GRANT_LAUNCHER_OFF = aimPad1.isYPressed();

        GRANT_HOOD_UP = aimPad1.isDPadUpPressed();
        GRANT_HOOD_DOWN = aimPad1.isDPadDownPressed();

        // JOLIE CONTROLS

        CLOSE_LAUNCH = aimPad2.isXPressed();
        FAR_LAUNCH = aimPad2.isBPressed();

        REVERSE_LAUNCH = aimPad2.isRightBumperHeld();
        JOLIE_LAUNCHER_OFF = aimPad2.isLeftBumperHeld();

        JOLIE_HOOD_UP = aimPad2.isDPadUpPressed();
        JOLIE_HOOD_DOWN= aimPad2.isDPadDownPressed();

    }
}
