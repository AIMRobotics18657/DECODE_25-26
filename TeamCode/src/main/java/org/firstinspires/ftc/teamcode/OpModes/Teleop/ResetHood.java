package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ScoringAssemblyV2;

@TeleOp (name="Reset Hood")
public class ResetHood extends OpMode {

    public ScoringAssemblyV2 scorer = new ScoringAssemblyV2();

    @Override
    public void init() {
        scorer.init(hardwareMap);
        scorer.hood.hood.setPosition(0);
    }

    @Override
    public void loop() {

    }
}
