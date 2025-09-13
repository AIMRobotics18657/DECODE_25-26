package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Limelight extends Mechanism {

    private Limelight3A lime;
    LLResult result;
    Pose3D botpose;

    public double xPose;
    public double yPose;

    //not sure abt this yet
    public YawPitchRollAngles orientation;
    public double heading;

    @Override
    public void init(HardwareMap hwMap) {
        lime = hwMap.get(Limelight3A.class, "lime");
        lime.setPollRateHz(100); ///Want this faster?
        lime.start();
        lime.pipelineSwitch(0); //Starting pipeline

    }

    @Override
    public void loop(AIMPad gamepad) {
        result = lime.getLatestResult();

        /// Switch this to MegaTag 2 to fuse w GoBildaPinpoint IMU
        /// Use helper methods
        if (result != null && result.isValid()) {
            botpose = result.getBotpose();
            if (botpose != null) {
                xPose = botpose.getPosition().x;
                yPose = botpose.getPosition().y;

                //not too sure about orientation yet prob gonna have to use megatag 2
                orientation = botpose.getOrientation();
                heading = orientation.getYaw();


            }
        }


    }

    public void telemetry(Telemetry telemetry) {


        if (result != null && result.isValid()) {
            //These variables are only for telemetry and debugging
            double tx = result.getTx(); // How far left or right the target is (degrees)
            double ty = result.getTy(); // How far up or down the target is (degrees)
            double ta = result.getTa(); // How big the target looks (0%-100% of the image)

            telemetry.addData("Target X", tx);
            telemetry.addData("Target Y", ty);
            telemetry.addData("Target Area", ta);

            //Bot position relative to field
            /// change this later to MegaTag 2
            telemetry.addData("MT1 Location", "(" + xPose + ", " + yPose + ")");
        } else {
            telemetry.addData("Limelight", "No Targets");
        }

    }
}
