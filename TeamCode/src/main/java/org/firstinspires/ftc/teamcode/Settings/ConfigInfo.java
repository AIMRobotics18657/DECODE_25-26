package org.firstinspires.ftc.teamcode.Settings;

import com.aimrobotics.aimlib.util.HardwareInterface;

public class ConfigInfo {

    public static HardwareInterface intake = new HardwareInterface("IN", false, 2);
    public static HardwareInterface launcherOne = new HardwareInterface("L1", false, 3);
    public static HardwareInterface launcherTwo = new HardwareInterface("L2", true, 2);
    public static HardwareInterface gate = new HardwareInterface("gate", false, 0);
    public static HardwareInterface hood = new HardwareInterface("hood", false, 0);

    //public static HardwareInterface rightRiser = new HardwareInterface("RR", false, 0);
    //public static HardwareInterface leftRiser = new HardwareInterface("LR", false, 0);


    //only used for testing
    public static HardwareInterface frontLeft = new HardwareInterface("FL", false, 0);
    public static HardwareInterface frontRight = new HardwareInterface("FR", false, 0);
    public static HardwareInterface backLeft = new HardwareInterface("BL", false, 0);
    public static HardwareInterface backRight = new HardwareInterface("BR", false, 0);

}
