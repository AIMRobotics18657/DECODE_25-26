package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@Autonomous (name="parkManualStop", preselectTeleOp = "TeleOp", group = "AAA_COMP")
public class ParkManualStop extends LinearOpMode {
    Robot robot = new Robot(new Pose2d(0, 0, 90), true);
    Drivebase db = new Drivebase(new Pose2d(0, 0, 90));
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        db.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            db.loop(new AIMPad(gamepad1), true);
            db.setAutoDrivePowers(0, .5 , 0);
            db.loop(new AIMPad(gamepad1), true);
            }
            telemetry.update();
        }
    }

