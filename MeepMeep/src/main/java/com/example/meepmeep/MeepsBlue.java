package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepsBlue {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0,0, Math.toRadians(90))).splineToLinearHeading(new Pose2d(-12, 20, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(-12, 58, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(225)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(12, 20, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(12, 58, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(225)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(36, 20, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(36, 58, Math.toRadians(90)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(225)), Math.toRadians(270)).splineToLinearHeading(new Pose2d(37.5, 33, Math.toRadians(0)), Math.toRadians(45)).build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}