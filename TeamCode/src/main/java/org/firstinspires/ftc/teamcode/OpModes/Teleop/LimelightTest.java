package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Subsystems.Limelight;

@TeleOp (name = "limelight test")
@Disabled
public class LimelightTest extends OpMode {


    Limelight limelight = new Limelight();
    @Override
    public void init() {
        limelight.init(hardwareMap);
    }

    @Override
    public void loop() {
        limelight.loop(new AIMPad(gamepad1));

        limelight.telemetry(telemetry);
        telemetry.update();
    }
}