package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Limelight extends Mechanism {

    private Limelight3A lime;
    LLResult llResult;
    double distance;
    Pose3D botpose;

    public double xPose;
    public double yPose;

    //not sure abt this yet
    public YawPitchRollAngles orientation;
    public double heading;


    private Pose2d globalPose = null;
    private double MAX_STALENESS = 1000;
    public boolean isStale;
    public final double ballOffset = 2;

    @Override
    public void init(HardwareMap hwMap) {
        lime = hwMap.get(Limelight3A.class, "lime");
        lime.setPollRateHz(100); ///Want this faster?
        lime.pipelineSwitch(0); //Starting pipeline
        lime.start();

    }

    @Override
    public void loop(AIMPad gamepad) {
        llResult = lime.getLatestResult();
        if (llResult != null && llResult.isValid()) {

            distance =  getDistance(llResult.getTa()) + ballOffset;
        } else {
            distance = 0;
        }



    }

    public double getDistance(double ta) {
        double dist = 70.50987 * Math.pow(ta, -0.5421864);
         return dist;
    }

    public YawPitchRollAngles getHeading() {

        return llResult.getBotpose_MT2().getOrientation();
    }

//    public double getIdealHeading() {
//        double targetBlueX = -60;
//        double targetBlueY = -60;
//        double deltaX = targetBlueX - llResult.getBotpose_MT2().getPosition().x;
//        double deltaY = targetBlueY - llResult.getBotpose_MT2().getPosition().y;
//
//        return Math.toDegrees(Math.atan2(deltaY, deltaX));
//    }

    public void telemetry(Telemetry telemetry) {



        telemetry.addData("LL Null?", llResult == null);
        telemetry.addData("LL Valid?", llResult.isValid());

        if (llResult != null) {
//            telemetry.addData("LL Valid?", llResult.isValid());
//
//            Pose3D botpose = llResult.getBotpose_MT2();
//            telemetry.addData("MT2 Null?", botpose == null);
//
            telemetry.addData("tgtx", llResult.getTx());
//            telemetry.addData("tgta", llResult.getTa());
//
//            if (botpose != null) {
//                telemetry.addData("Botpose", botpose.toString());
//            }
        }


        telemetry.addData("calculated dist", distance);
        //telemetry.update();
    }
}

