package org.firstinspires.ftc.teamcode.OpModes.Auto.Close;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AutoConstants;
import org.firstinspires.ftc.teamcode.Subsystems.Gate;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ScoringAssemblyV2;

@Autonomous(name = "blue gate skip")
public class BlueGateSkip extends LinearOpMode {
    private final ScoringAssemblyV2 scorer = new ScoringAssemblyV2();
    private final ElapsedTime timer = new ElapsedTime();
    private boolean shootIsDone = false;

    @Override
    public void runOpMode() {
        scorer.init(hardwareMap);
        Follower follower = AutoConstants.createFollower(hardwareMap);

        Pose startPose = toPedroPose(AutoConstants.BLUE_START);
        Pose shootPose = toPedroPose(AutoConstants.BLUE_SHOOT);
        Pose lineOneSetupPose = toPedroPose(AutoConstants.BLUE_LINE_ONE_SETUP);
        Pose lineOneWallPose = toPedroPose(AutoConstants.BLUE_LINE_ONE_WALL);
        Pose gateSetupPose = toPedroPose(AutoConstants.BLUE_GATE_SETUP);
        Pose gateOpenPose = new Pose(AutoConstants.BLUE_GATE_OPEN.x, AutoConstants.BLUE_GATE_OPEN.y);
        Pose lineTwoSetupPose = toPedroPose(AutoConstants.BLUE_LINE_TWO_SETUP);
        Pose lineTwoWallPose = toPedroPose(AutoConstants.BLUE_LINE_TWO_WALL);
        Pose parkPose = new Pose(38, -33, Math.toRadians(-90));

        follower.setStartingPose(startPose);

        PathChain initialToShoot = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();

        PathChain getFirstBalls = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, lineOneSetupPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), lineOneSetupPose.getHeading())
                .addPath(new BezierLine(lineOneSetupPose, lineOneWallPose))
                .setConstantHeadingInterpolation(lineOneWallPose.getHeading())
                .addPath(new BezierLine(lineOneWallPose, shootPose))
                .setLinearHeadingInterpolation(lineOneWallPose.getHeading(), shootPose.getHeading())
                .build();

        // openGate returns to shootPose so getSecondBalls can start cleanly
        PathChain openGate = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, gateSetupPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), gateSetupPose.getHeading())
                .addPath(new BezierLine(gateSetupPose, gateOpenPose))
                .setConstantHeadingInterpolation(gateSetupPose.getHeading())
                .addPath(new BezierLine(gateOpenPose, shootPose))
                .setLinearHeadingInterpolation(gateSetupPose.getHeading(), shootPose.getHeading())
                .build();

        PathChain getSecondBalls = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, lineTwoSetupPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), lineTwoSetupPose.getHeading())
                .addPath(new BezierLine(lineTwoSetupPose, lineTwoWallPose))
                .setConstantHeadingInterpolation(lineTwoWallPose.getHeading())
                .addPath(new BezierLine(lineTwoWallPose, shootPose))
                .setLinearHeadingInterpolation(lineTwoWallPose.getHeading(), shootPose.getHeading())
                .build();

        PathChain park = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, parkPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), parkPose.getHeading())
                .build();

        waitForStart();

        if (!opModeIsActive()) return;

        follower.followPath(initialToShoot);
        runFollowerUntilDone(follower);
        shootThree();

        follower.followPath(getFirstBalls);
        runFollowerUntilDone(follower);
        shootThree();

        follower.followPath(openGate);
        runFollowerUntilDone(follower);

        follower.followPath(getSecondBalls);
        runFollowerUntilDone(follower);
        shootThree();

        follower.followPath(park);
        runFollowerUntilDone(follower);
    }

    private void runFollowerUntilDone(Follower follower) {
        while (opModeIsActive() && follower.isBusy()) {
            scorer.loop(new AIMPad(gamepad1));
            scorer.intake.setMode(Intake.IntakeMode.IN);
            scorer.launcher.setVelo(190 * 2 * Math.PI / 628);
            follower.update();
            idle();
        }
    }

    private void shootThree() {
        shootIsDone = false;
        timer.reset();

        while (opModeIsActive() && !shootIsDone) {
            scorer.loop(new AIMPad(gamepad1));

            if (timer.milliseconds() <= 100) {
                scorer.score(63 * 0.0254, 190, 10, 0, -10);
            } else if (timer.milliseconds() > 200 && timer.milliseconds() <= 3000) {
                scorer.gate.setMode(Gate.GateMode.IN);
                scorer.intake.setMode(Intake.IntakeMode.IN);
            } else if (timer.milliseconds() > 3000) {
                scorer.launcher.setVelo(0);
                scorer.gate.setMode(Gate.GateMode.OFF);
                shootIsDone = true;
            }

            idle();
        }
    }

    private static Pose toPedroPose(Pose2d pose) {
        return new Pose(pose.position.x, pose.position.y);
    }
}
