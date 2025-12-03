package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ScoringAssembly {
    public Launcher launcher = new Launcher();
    public Ramp ramp = new Ramp();

    private ElapsedTime shootTimeThree = new ElapsedTime();
    private ElapsedTime shootTimeTwo = new ElapsedTime();
    private ElapsedTime shootTimeOne = new ElapsedTime();
    private static final double SHOOT_THREE_MS = 3000;
    private static final double SHOOT_TWO_MS = 2000;
    private static final double SHOOT_ONE_MS = 1000;
    public boolean shootingFinished;

    public enum ShootCount {
        THREE,
        TWO,
        ONE,
        NONE;
    }
    public ShootCount activeShootCount = ShootCount.NONE;

    public void init(HardwareMap hwMap) {
        launcher.init(hwMap);
        ramp.init(hwMap);
        shootingFinished = false;
    }

    public void loop(AIMPad aimPad1) {
        launcher.loop(aimPad1);
        ramp.loop(aimPad1);

        //only for auto?
        switch (activeShootCount) {
            case THREE:
                updateShootThree();
                launcher.loop(aimPad1);
                ramp.loop(aimPad1);
                break;
            case TWO:
                updateShootTwo();
                launcher.loop(aimPad1);
                ramp.loop(aimPad1);
                break;
            case ONE:
                updateShootOne();
                launcher.loop(aimPad1);
                ramp.loop(aimPad1);
                break;
            case NONE:
                launcher.setTargetPower(Launcher.launchPower.OFF);
                ramp.stopSpin();
                launcher.loop(aimPad1);
                ramp.loop(aimPad1);
                break;
        }

    }

    public void updateShootThree() {
            launcher.setTargetPower(Launcher.launchPower.FAR);
            ramp.spinIn();
            if (shootTimeThree.milliseconds() > SHOOT_THREE_MS) {
                launcher.setTargetPower(Launcher.launchPower.OFF);
                shootingFinished = true;
                activeShootCount = ShootCount.NONE;
        }
    }

    public void updateShootTwo() {
        launcher.setTargetPower(Launcher.launchPower.FAR);
        ramp.spinIn();
        if (shootTimeTwo.milliseconds() > SHOOT_TWO_MS) {
            launcher.setTargetPower(Launcher.launchPower.OFF);
            shootingFinished = true;
            activeShootCount = ShootCount.NONE;
        }
    }


    public void updateShootOne() {
        launcher.setTargetPower(Launcher.launchPower.FAR);
        ramp.spinIn();
        if (shootTimeOne.milliseconds() > SHOOT_ONE_MS) {
            launcher.setTargetPower(Launcher.launchPower.OFF);
            shootingFinished = true;
            activeShootCount = ShootCount.NONE;
        }
    }

    public void startShootThree() {
        activeShootCount = ShootCount.THREE;
        shootingFinished = false;
        shootTimeThree.reset();
    }

    public void startShootTwo() {
        activeShootCount = ShootCount.TWO;
        shootingFinished = false;
        shootTimeTwo.reset();
    }

    public void startShootOne() {
        activeShootCount = ShootCount.ONE;
        shootingFinished = false;
        shootTimeOne.reset();
    }




}
