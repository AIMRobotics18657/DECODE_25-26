package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.InputHandler;

public class Robot extends Mechanism {

    public Drivebase db;
    public Ramp ramp = new Ramp();
    public Launcher launcher = new Launcher();
    public Riser riser = new Riser();

    private InputHandler handler = new InputHandler();

    public Pose2d startingPose;
    public boolean isAuto;

    public enum robotState {
        SCORING,
        ENDGAME;
    }

    public robotState activeState = robotState.SCORING;

    public Robot (Pose2d startingPose, boolean isAuto) {
        this.startingPose = startingPose;
        this.isAuto = isAuto;
        db = new Drivebase(startingPose);
    }

    @Override
    public void init(HardwareMap hwMap) {
        db.init(hwMap);
        ramp.init(hwMap);
        launcher.init(hwMap);
        riser.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimPad1, AIMPad aimPad2) {
        handler.updateInputs(aimPad1, aimPad2);
        ramp.loop(aimPad1);
        launcher.loop(aimPad1);
        riser.loop(aimPad1);

        if (!isAuto){
            db.loop(aimPad2);

            switch(activeState) {
                case SCORING:
                    scoringState();
                    break;
                case ENDGAME:
                    endgameState();
                    break;
            }
        }

    }

    public void scoringState() {
        if (handler.SPIN_IN) {
            ramp.spinIn();
        } else if (handler.SPIN_OUT) {
            ramp.spinOut();
        } else {
            ramp.stopSpin();
        }

        if (handler.FAR_LAUNCH) {
            launcher.setTargetPower(Launcher.launchPower.FAR);
        } else if (handler.CLOSE_LAUNCH) {
            launcher.setTargetPower(Launcher.launchPower.CLOSE);
        } else {
            launcher.setTargetPower(Launcher.launchPower.OFF);
        }

        if (handler.TOGGLE_ENDGAME) {
            activeState = robotState.ENDGAME;
        }
    }

    public void endgameState() {

        if (handler.TOGGLE_RISE) {
            if (riser.activeRiserPosition == Riser.riserPosition.DOWN) {
                riser.setTargetPosition(Riser.riserPosition.UP);
            } else {
                riser.setTargetPosition(Riser.riserPosition.DOWN);
            }
        }


        if (handler.TOGGLE_ENDGAME) {
            activeState = robotState.SCORING;
        }
    }
}
