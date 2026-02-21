package org.firstinspires.ftc.teamcode.Settings;

import com.aimrobotics.aimlib.util.HardwareInterface;

public class ConfigInfo {

    public static HardwareInterface intake = new HardwareInterface("IN", false, 0);
    public static HardwareInterface launcher = new HardwareInterface("LA", false, 0);
    public static HardwareInterface gate = new HardwareInterface("gate", false, 0);

    //public static HardwareInterface rightRiser = new HardwareInterface("RR", false, 0);
    //public static HardwareInterface leftRiser = new HardwareInterface("LR", false, 0);

    public static HardwareInterface frontLeft = new HardwareInterface("FL", false, 0);
    public static HardwareInterface frontRight = new HardwareInterface("FR", false, 0);
    public static HardwareInterface backLeft = new HardwareInterface("BL", false, 0);
    public static HardwareInterface backRight = new HardwareInterface("BR", false, 0);

}
