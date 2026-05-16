package org.firstinspires.ftc.teamcode.OpModes.Auto.Far;

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

@Autonomous(name = "RedFar")
public class RedFar extends LinearOpMode {
    private final ScoringAssemblyV2 scorer = new ScoringAssemblyV2();
    private final ElapsedTime timer = new ElapsedTime();
    private boolean shootIsDone = false;

    @Override
    public void runOpMode() {
        scorer.init(hardwareMap);
        Follower follower = AutoConstants.createFollower(hardwareMap);

        Pose startPose = new Pose(AutoConstants.RED_FAR_START.position.x,
                AutoConstants.RED_FAR_START.position.y,
                Math.toRadians(180));
        Pose shootPose = new Pose(AutoConstants.RED_FAR_SHOOT.position.x,
                AutoConstants.RED_FAR_SHOOT.position.y,
                AutoConstants.RED_FAR_SHOOTING_ANGLE);

        // getFirstBalls waypoints
        Pose setupPose = new Pose(39, 15, Math.toRadians(90));
        Pose wallPose = new Pose(30, 59, Math.toRadians(90));

        // gateBalls waypoints
        Pose gateApproach = new Pose(30, 45, Math.toRadians(20));
        Pose gate1 = new Pose(55, 55);
        Pose gate2 = new Pose(40, 45);
        Pose gate3 = new Pose(62, 55, 0);

        Pose parkPose = new Pose(40, 15, shootPose.getHeading());

        follower.setStartingPose(startPose);

        PathChain initialToShoot = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();

        PathChain getFirstBalls = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, setupPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), setupPose.getHeading())
                .addPath(new BezierLine(setupPose, wallPose))
                .setConstantHeadingInterpolation(setupPose.getHeading())
                .addPath(new BezierLine(wallPose, shootPose))
                .setLinearHeadingInterpolation(wallPose.getHeading(), shootPose.getHeading())
                .build();

        PathChain gateBalls = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, gateApproach))
                .setLinearHeadingInterpolation(shootPose.getHeading(), gateApproach.getHeading())
                .addPath(new BezierLine(gateApproach, gate1))
                .setConstantHeadingInterpolation(gateApproach.getHeading())
                .addPath(new BezierLine(gate1, gate2))
                .setConstantHeadingInterpolation(gateApproach.getHeading())
                .addPath(new BezierLine(gate2, gate3))
                .setLinearHeadingInterpolation(gateApproach.getHeading(), 0)
                .addPath(new BezierLine(gate3, shootPose))
                .setLinearHeadingInterpolation(0, shootPose.getHeading())
                .build();

        PathChain park = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, parkPose))
                .setConstantHeadingInterpolation(shootPose.getHeading())
                .build();

        waitForStart();

        if (!opModeIsActive()) return;

        scorer.intake.setMode(Intake.IntakeMode.IN);
        scorer.hood.setPosition((35 - 32.0) / (72 - 32.0));

        follower.followPath(initialToShoot);
        runFollowerUntilDone(follower, AutoConstants.FAR_LAUNCHER_FAST);
        sleep(1000);
        shootThreeFar(AutoConstants.FAR_LAUNCHER_FAST);

        follower.followPath(getFirstBalls);
        runFollowerUntilDone(follower, AutoConstants.FAR_LAUNCHER_NORMAL);
        shootThreeFar(AutoConstants.FAR_LAUNCHER_NORMAL);

        follower.followPath(gateBalls);
        runFollowerUntilDone(follower, AutoConstants.FAR_LAUNCHER_NORMAL);
        shootThreeFar(AutoConstants.FAR_LAUNCHER_NORMAL);

        follower.followPath(park);
        runFollowerUntilDone(follower, 0);
    }

    private void runFollowerUntilDone(Follower follower, double launcherVelo) {
        while (opModeIsActive() && follower.isBusy()) {
            scorer.loop(new AIMPad(gamepad1));
            scorer.intake.setMode(Intake.IntakeMode.IN);
            if (launcherVelo > 0) scorer.launcher.setVelo(launcherVelo);
            follower.update();
            idle();
        }
    }

    private void shootThreeFar(double launcherVelo) {
        shootIsDone = false;
        timer.reset();

        while (opModeIsActive() && !shootIsDone) {
            scorer.loop(new AIMPad(gamepad1));
            scorer.launcher.setVelo(launcherVelo);

            if (timer.milliseconds() > 600 && timer.milliseconds() <= 3000) {
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
}
