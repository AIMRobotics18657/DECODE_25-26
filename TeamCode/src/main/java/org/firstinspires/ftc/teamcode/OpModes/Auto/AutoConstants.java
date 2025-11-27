package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;

public class AutoConstants {
    public final double ROBOT_WIDTH = 15.2;
    public final double ROBOT_LENGTH = 18;
    public static final Pose2d RED_START = new Pose2d(-6,7, Math.toRadians(135));
    public static final Pose2d RED_SHOOTING = new Pose2d(-6,7, Math.toRadians(150));
    public static final double RED_SHOOTING_ANGLE = Math.toRadians(150);

    public static final Pose2d RED_LINE_ONE_SETUP = new Pose2d(-11.5, 25, Math.toRadians(90));
    public static final double RED_LINE_ONE_SETUP_TANGENT = Math.toRadians(90);
    public static final Pose2d RED_LINE_ONE_WALL = new Pose2d(-11.5, 53, Math.toRadians(90));
    public static final Pose2d RED_LINE_TWO_SETUP = new Pose2d(12, 25, Math.toRadians(90));
    public static final Pose2d RED_LINE_TWO_WALL = new Pose2d(12, 53, Math.toRadians(90));
    public static final Pose2d RED_LINE_THREE_SETUP = new Pose2d(36, 25, Math.toRadians(90));
    public static final Pose2d RED_LINE_THREE_WALL = new Pose2d(36, 53, Math.toRadians(90));
    public static final Pose2d RED_PARK = new Pose2d(38, -33, Math.toRadians(90));

}
