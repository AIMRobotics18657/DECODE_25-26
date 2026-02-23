//package org.firstinspires.ftc.teamcode.Subsystems;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.aimrobotics.aimlib.util.Mechanism;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//public class ScoringAssembly extends Mechanism {
//    public Launcher launcher = new Launcher();
//    public Intake intake = new Intake();
//    //public Gate gate = new Gate();
//    public Hood hood = new Hood();
//
//    public ElapsedTime shootTimeThree = new ElapsedTime();
//    private ElapsedTime shootTimeTwo = new ElapsedTime();
//    public ElapsedTime shootTimeOne = new ElapsedTime();
//    public ElapsedTime shootTimeWindUp = new ElapsedTime();
//    public static final double SHOOT_THREE_MS = 3000;
//    private static final double SHOOT_TWO_MS = 2000;
//    private static final double SHOOT_ONE_MS = 1000;
//    public static final double SHOOT_WIND_UP_MS = 750;
//    public boolean shootingFinished;
//
//    public enum ShootCount {
//        THREE,
//        TWO,
//        ONE,
//        WIND_UP,
//        NONE;
//    }
//    public ShootCount activeShootCount = ShootCount.NONE;
//
//    public void init(HardwareMap hwMap) {
//        launcher.init(hwMap);
//        intake.init(hwMap);
//        hood.init(hwMap);
//        shootingFinished = false;
//    }
//
//    public void loop(AIMPad aimPad1) {
//        launcher.loop(aimPad1);
//        intake.loop(aimPad1);
//        hood.loop(aimPad1);
//
//        //only for auto?
//        switch (activeShootCount) {
//            case THREE:
//                updateShootThree();
//                launcher.loop(aimPad1);
//                intake.loop(aimPad1);
//                break;
//            case TWO:
//                updateShootTwo();
//                launcher.loop(aimPad1);
//                intake.loop(aimPad1);
//                break;
//            case ONE:
//                updateShootOne();
//                launcher.loop(aimPad1);
//                intake.loop(aimPad1);
//                break;
//            case WIND_UP:
//                if (!shootingFinished) {
//                    updateWindUp();
//                } else {
//                    launcher.setTargetPower(Launcher.launchPower.OFF);
//                    intake.setMode(Intake.IntakeMode.OFF);
//                    activeShootCount = ShootCount.NONE;
//                    shootingFinished = true;
//                }
//                launcher.loop(aimPad1);
//                intake.loop(aimPad1);
//                break;
//            case NONE:
//                launcher.loop(aimPad1);
//                intake.loop(aimPad1);
//                break;
//        }
//
//    }
//
//    public void updateShootThree() {
//        if (shootingFinished) return;
//        updateWindUp();
//        if (shootTimeThree.milliseconds() > SHOOT_THREE_MS) {
//            launcher.setTargetPower(Launcher.launchPower.OFF);
//            shootingFinished = true;
//            activeShootCount = ShootCount.NONE;
//        }
//    }
//
//    public void updateShootTwo() {
//        if (shootingFinished) return;
//        updateWindUp();
//        if (shootTimeTwo.milliseconds() > SHOOT_TWO_MS) {
//            launcher.setTargetPower(Launcher.launchPower.OFF);
//            shootingFinished = true;
//            activeShootCount = ShootCount.NONE;
//        }
//    }
//
//
//    public void updateShootOne() {
//        if (shootingFinished) return;
//        updateWindUp();
//        if (shootTimeOne.milliseconds() > SHOOT_ONE_MS) {
//            launcher.setTargetPower(Launcher.launchPower.OFF);
//            shootingFinished = true;
//            activeShootCount = ShootCount.NONE;
//        }
//    }
//
//    public void startShootThree() {
//        activeShootCount = ShootCount.THREE;
//        shootingFinished = false;
//        shootTimeThree.reset();
//        shootTimeWindUp.reset();
//    }
//
//    public void startShootTwo() {
//        activeShootCount = ShootCount.TWO;
//        shootingFinished = false;
//        shootTimeTwo.reset();
//        shootTimeWindUp.reset();
//    }
//
//    public void startShootOne() {
//        activeShootCount = ShootCount.ONE;
//        shootingFinished = false;
//        shootTimeOne.reset();
//        shootTimeWindUp.reset();
//    }
///*
//    public void updateWindUp(double END_MS, ElapsedTime shootNum) {
//        launcher.setTargetPower(Launcher.launchPower.FAR);
//        if (shootTimeWindUp.milliseconds() > SHOOT_WIND_UP_MS) {
//            ramp.openGate();
//            ramp.spinIn();
//
//        } else if (shootTimeWindUp.milliseconds() > END_MS) {
//
//        }
//    }
//
// */
//    public void updateWindUp() {
//        launcher.setTargetPower(Launcher.launchPower.FAR);
//        //ramp.openGate();// why reversed
//        if (shootTimeWindUp.milliseconds() > SHOOT_WIND_UP_MS) {
//            //ramp.closeGate();
//            //ramp.spinIn();
//        }
//    }
//
//    public void startWindUp() {
//        activeShootCount = ShootCount.WIND_UP;
//        shootingFinished = false;
//        shootTimeWindUp.reset();
//    }
//
//    public void telemetry(Telemetry telemetry){
//        //ramp.telemetry(telemetry);
//        launcher.telemetry(telemetry);
//        intake.telemetry(telemetry);
//        hood.telemetry(telemetry);
//    }
//
//}
