package org.firstinspires.ftc.teamcode.Settings;

public class InputModification {
    public static double poweredInput(double base, int modifier) {
        if (modifier % 2 == 0) {
            return Math.pow(base, GamepadSettings.EXPONENT_MODIFIER) * Math.signum(base);
        } else {
            return Math.pow(base, GamepadSettings.EXPONENT_MODIFIER);
        }
    }
}
