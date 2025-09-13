package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot extends Mechanism {

    public Drivebase db;

    public Pose2d startingPose;
    public boolean isAuto;

    public Robot (Pose2d startingPose, boolean isAuto) {
        this.startingPose = startingPose;
        this.isAuto = isAuto;
        db = new Drivebase(startingPose);
    }

    @Override
    public void init(HardwareMap hwMap) {
        db.init(hwMap);
    }

    @Override
    public void loop(AIMPad gamepad) {

        ///STATE MACHINE LOGIC
    }
}
