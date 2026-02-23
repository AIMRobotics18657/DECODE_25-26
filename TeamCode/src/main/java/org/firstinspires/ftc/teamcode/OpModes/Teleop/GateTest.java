//package org.firstinspires.ftc.teamcode.OpModes.Teleop;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Settings.InputHandler;
//import org.firstinspires.ftc.teamcode.Subsystems.Ramp;
//
//@TeleOp (name="gate")
//public class GateTest extends OpMode {
//
//    AIMPad aimPad1;
//    AIMPad aimPad2;
//
//    public Ramp ramp = new Ramp();
//    public InputHandler handler = new InputHandler();
////
//    @Override
//    public void init() {
//        aimPad1 = new AIMPad(gamepad1);
//        aimPad2 = new AIMPad(gamepad2);
//        ramp.init(hardwareMap);
//    }
//
//    @Override
//    public void loop() {
//
//        aimPad1.update(gamepad1);
//        aimPad2.update(gamepad2);
//        ramp.loop(aimPad1);
//        handler.updateInputs(aimPad1, aimPad2);
//
//        if (aimPad1.isYPressed()) {
//            ramp.openGate();
//        }
//        if (aimPad1.isBPressed()) {
//            ramp.closeGate();
//        }
//
//
//
//        ramp.telemetry(telemetry);
//        telemetry.addData("is Y pressed", aimPad1.isYHeld());
//        telemetry.addData("is B pressed", aimPad1.isBHeld());
//        telemetry.addData("Gate Pos", ramp.gate.getActiveTargetState());
//        telemetry.addData("Previous State", aimPad1.getPreviousState());
//        telemetry.addData("Current State", aimPad1.getCurrentState());
//        telemetry.update();
//    }
//}
