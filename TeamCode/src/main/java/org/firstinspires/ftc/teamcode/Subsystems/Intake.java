package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.OpModes.Teleop.TeleOp;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Intake extends Mechanism {

    public DcMotorEx intake;

    public enum IntakeMode {
        IN(1),
        OUT(-1),
        OFF(0);

        public final double power;

        IntakeMode (double power) {this.power = power;}
    }

    public IntakeMode activeIntakeMode = IntakeMode.OFF;

    @Override
    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, ConfigInfo.intake.getDeviceName());
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop (AIMPad aimPad) {
        setPower(activeIntakeMode);
    }

    public void setMode(IntakeMode mode) {
        activeIntakeMode = mode;
    }
    private void setPower(IntakeMode mode) {
        intake.setPower(activeIntakeMode.power);
    }


    public void telemetry (Telemetry telemetry) {
        telemetry.addData("Intake Mode", activeIntakeMode);
    }

}
