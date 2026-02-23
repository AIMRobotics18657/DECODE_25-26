//package org.firstinspires.ftc.teamcode.OpModes.Teleop;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Subsystems.Gate;
//import org.firstinspires.ftc.teamcode.Subsystems.Hood;
//import org.firstinspires.ftc.teamcode.Subsystems.Intake;
//import org.firstinspires.ftc.teamcode.Subsystems.ScoringAssemblyV2;
//import org.firstinspires.ftc.teamcode.Subsystems.VeloLaunch;
//
//@TeleOp (name = "calc testing")
//public class calcShooting extends OpMode {
//
//    VeloLaunch launcher = new VeloLaunch();
//    Hood hood = new Hood();
//    Intake intake = new Intake();
//    ScoringAssemblyV2 sa = new ScoringAssemblyV2();
//    Gate gate = new Gate();
//
//    @Override
//    public void init() {
//        launcher.init(hardwareMap);
//        hood.init(hardwareMap);
//        intake.init(hardwareMap);
//        sa.init(hardwareMap);
//        gate.init(hardwareMap);
//    }
//
//    @Override
//    public void loop() {
//        intake.loop(new AIMPad(gamepad1));
//        gate.loop(new AIMPad(gamepad1));
//
//
//
//        double wheelRadius = 0.096;
//        double omega = 600; // rad/s
//        double v = omega * wheelRadius;
//        double hoodRad = sa.solveTheta(3.175, v);
//        double hoodDeg = sa.radToDeg(hoodRad);
//        double minDeg = 30;
//        double maxDeg = 65;
//        double offsetDeg = 20; // tweak experimentally
//        hoodDeg += offsetDeg;
//        //double hoodPos =  1 - hoodDeg * 0.02085714285;
//        double hoodPos = (hoodDeg - minDeg) /(maxDeg - minDeg);
//
//        if (gamepad1.a) {
//            hood.hood.setPosition(hoodPos);
//        } else if (gamepad1.b) {
//            launcher.launcherOne.setVelocity(600, AngleUnit.RADIANS);
//            launcher.launcherTwo.setVelocity(600, AngleUnit.RADIANS);
//        } else if (gamepad1.y) {
//            launcher.launcherOne.setPower(1);
//            launcher.launcherTwo.setPower(1);//see if this velo on telemetry is above 4.2
//        }
//
//        if (gamepad1.x) {
//            intake.setMode(Intake.IntakeMode.IN);
//        }
//
//        if (gamepad1.dpad_down) {
//            gate.gate.setPower(1);
//        }
//
//
//        hood.telemetry(telemetry);
//        intake.telemetry(telemetry);
//        launcher.telemetry(telemetry);
//        sa.telemetry(telemetry);
//        telemetry.addData("hood deg opmode", hoodDeg);
//        telemetry.addData("hood pos", hoodPos);
//        telemetry.addData("hood rads", hoodRad);
//        telemetry.update();
//
//    }
//
//
//}
