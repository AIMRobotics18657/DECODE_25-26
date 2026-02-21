package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class AutoShoot {

    public DcMotorEx shooter;


    public void setVelo () {
        shooter.setVelocity(2);
    }
}
