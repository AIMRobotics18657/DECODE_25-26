package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name="testRumble")
@Disabled
public class TestRumble extends OpMode {
    boolean lastA = false;                      // Use to track the prior button state.
    boolean lastLB = false;                     // Use to track the prior button state.
    boolean highLevel = false;                  // used to prevent multiple level-based rumbles.
    boolean secondHalf = false;                 // Use to prevent multiple half-time warning rumbles.

    Gamepad.RumbleEffect customRumbleEffect;    // Use to build a custom rumble sequence.
    ElapsedTime runtime = new ElapsedTime();    // Use to determine when end game is starting.

    final double HALF_TIME = 60.0;              // Wait this many seconds before rumble-alert for half-time.
    final double TRIGGER_THRESHOLD  = 0.75;     // Squeeze more than 3/4 to get rumble.


    public void init() {

    }

    @Override
    public void loop() {
        boolean currentA = gamepad1.a ;
        boolean currentLB = gamepad1.left_bumper ;

        // Display the current Rumble status.  Just for interest.
        telemetry.addData(">", "Are we RUMBLING? %s\n", gamepad1.isRumbling() ? "YES" : "no" );

        if (currentLB) {
            // Left Bumper is being pressed, so send left and right "trigger" values to left and right rumble motors.
            gamepad1.rumble(gamepad1.left_trigger, gamepad1.right_trigger, Gamepad.RUMBLE_DURATION_CONTINUOUS);

            // Show what is being sent to rumbles
            telemetry.addData(">", "Squeeze triggers to control rumbles");
            telemetry.addData("> : Rumble", "Left: %.0f%%   Right: %.0f%%", gamepad1.left_trigger * 100, gamepad1.right_trigger * 100);
        } else {
            // Make sure rumble is turned off when Left Bumper is released (only one time each press)
            if (lastLB) {
                gamepad1.stopRumble();
            }

            //  Prompt for manual rumble action
            telemetry.addData(">", "Hold Left-Bumper to test Manual Rumble");
            telemetry.addData(">", "Press A (Cross) for three blips");
            telemetry.addData(">", "Squeeze right trigger slowly for 1 blip");
        }
        lastLB = currentLB; // remember the current button state for next time around the loop

        telemetry.update();
    }

}
