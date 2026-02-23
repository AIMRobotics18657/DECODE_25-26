package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.VeloLaunch;

@TeleOp (name="veloShoot")
public class VeloShoot extends OpMode {

    VeloLaunch vl = new VeloLaunch();
    Intake intake = new Intake();
    AIMPad aimPad;

    @Override
    public void init() {
        vl.init(hardwareMap);
        aimPad = new AIMPad(gamepad1);
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        //vl.loop(aimPad);
        aimPad.update(gamepad1);
        intake.loop(aimPad);

        if (aimPad.isLeftTriggerHeld()) {
            vl.launcherOne.setVelocity(600, AngleUnit.RADIANS);
            vl.launcherTwo.setVelocity(600, AngleUnit.RADIANS);
        } else if (aimPad.isRightTriggerHeld()) {
            vl.launcherOne.setVelocity(200, AngleUnit.RADIANS);
            vl.launcherTwo.setVelocity(200, AngleUnit.RADIANS);
        } else {
            vl.launcherOne.setVelocity(0, AngleUnit.RADIANS);
            vl.launcherTwo.setVelocity(0, AngleUnit.RADIANS);
        }
        if (aimPad.isAnyBumperHeld()) {
            intake.setMode(Intake.IntakeMode.IN);
        }

        if (aimPad.isAHeld()) {
            vl.launcherOne.setPower(1);
            vl.launcherTwo.setPower(1);
        }

        telemetry.addData("launcher 1 velo", vl.launcherOne.getVelocity(AngleUnit.RADIANS));
        telemetry.addData("launcher 1 ticks", vl.launcherOne.getCurrentPosition());
        telemetry.update();
    }

}
