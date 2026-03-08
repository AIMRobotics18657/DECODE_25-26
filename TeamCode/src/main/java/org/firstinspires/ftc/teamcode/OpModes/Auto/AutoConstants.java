package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

public class AutoConstants {
//    public final double ROBOT_WIDTH = 15.2;
//    public final double ROBOT_LENGTH = 18;
//    public static final Pose2d RED_START = new Pose2d(-6,7, Math.toRadians(135));
//    public static final Pose2d RED_SHOOTING = new Pose2d(-6,7, Math.toRadians(150));
//    public static final double RED_SHOOTING_ANGLE = Math.toRadians(150);
//
//    public static final Pose2d RED_LINE_ONE_SETUP = new Pose2d(-11.5, 25, Math.toRadians(90));
//    public static final double RED_LINE_ONE_SETUP_TANGENT = Math.toRadians(90);
//    public static final Pose2d RED_LINE_ONE_WALL = new Pose2d(-11.5, 53, Math.toRadians(90));
//    public static final Pose2d RED_LINE_TWO_SETUP = new Pose2d(12, 25, Math.toRadians(90));
//    public static final Pose2d RED_LINE_TWO_WALL = new Pose2d(12, 53, Math.toRadians(90));
//    public static final Pose2d RED_LINE_THREE_SETUP = new Pose2d(36, 25, Math.toRadians(90));
//    public static final Pose2d RED_LINE_THREE_WALL = new Pose2d(36, 53, Math.toRadians(90));
//    public static final Pose2d RED_PARK = new Pose2d(38, -33, Math.toRadians(90));



    // BLUE CONSTANTS
    public static final Pose2d BLUE_START = new Pose2d(-55 + Math.sqrt(50),-55 + Math.sqrt(50), Math.toRadians(-135));
    public static final Pose2d BLUE_SHOOT = new Pose2d(-15, -15, Math.toRadians(-135));
    public static final Vector2d BLUE_SHOOT_VECTOR = new Vector2d(-15,-15);

    public static final Pose2d BLUE_LINE_ONE_SETUP = new Pose2d(-8.5, -25, Math.toRadians(-90));

    public static final Pose2d BLUE_LINE_ONE_WALL = new Pose2d(-8.5, -61, Math.toRadians(-90));

    public static final Pose2d BLUE_GATE_SETUP = new Pose2d(9, -50, Math.toRadians(90));
    public static final Vector2d BLUE_GATE_OPEN = new Vector2d(9, -67);

    public static final Pose2d BLUE_LINE_TWO_SETUP = new Pose2d(16, -25, Math.toRadians(-90));

    public static final Pose2d BLUE_LINE_TWO_WALL = new Pose2d(16, -70, Math.toRadians(-90));

    public static final Pose2d BLUE_LINE_THREE_SETUP = new Pose2d(39, -25, Math.toRadians(-90));

    public static final Pose2d BLUE_LINE_THREE_WALL = new Pose2d(39, -70, Math.toRadians(-90));


    // RED CONSTANTS
    public static final Pose2d RED_START = new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135));
    public static final Pose2d RED_SHOOT = new Pose2d(-15, 15, Math.toRadians(135));
    public static final Vector2d RED_SHOOT_VECTOR = new Vector2d(-15,15);

    public static final Pose2d RED_LINE_ONE_SETUP = new Pose2d(-6.5, 25, Math.toRadians(90));

    public static final Pose2d RED_LINE_ONE_WALL = new Pose2d(-6.5, 53, Math.toRadians(90));

    public static final Pose2d RED_GATE_SETUP = new Pose2d(-7, 50, Math.toRadians(270));
    public static final Vector2d RED_GATE_OPEN = new Vector2d(-7, 70);

    public static final Pose2d RED_LINE_TWO_SETUP = new Pose2d(18, 20, Math.toRadians(90));

    public static final Pose2d RED_LINE_TWO_WALL = new Pose2d(18, 66, Math.toRadians(90));

    public static final Pose2d RED_LINE_THREE_SETUP = new Pose2d(41, 15, Math.toRadians(90));

    public static final Pose2d RED_LINE_THREE_WALL = new Pose2d(41, 66, Math.toRadians(90));


    // FAR CONSTANTS
    public static final Pose2d BLUE_FAR_START = new Pose2d(63, -8, Math.toRadians(180));

    public static final double  BLUE_FAR_SHOOTING_ANGLE = (Math.PI) - Math.atan((double) -44 / 124);
    public static final Pose2d BLUE_FAR_SHOOT = new Pose2d(55, -15, BLUE_FAR_SHOOTING_ANGLE);

}
