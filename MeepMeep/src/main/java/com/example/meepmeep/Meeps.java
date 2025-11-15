package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Meeps {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, 36, 0)).turnTo(Math.toRadians(315)).waitSeconds(5).splineTo(new Vector2d(0, 15), Math.toRadians(90)).splineTo(new Vector2d(-12, 56), Math.toRadians(90)).splineTo(new Vector2d(-36, 36), Math.toRadians(135)).waitSeconds(2).splineTo(new Vector2d(0, 15), Math.toRadians(90)).splineTo(new Vector2d(16, 56), Math.toRadians(90)).splineTo(new Vector2d(-36, 36), Math.toRadians(135)).waitSeconds(2).splineTo(new Vector2d(0, 15), Math.toRadians(90)).splineTo(new Vector2d(40, 56), Math.toRadians(90)).splineTo(new Vector2d(-36, 36), Math.toRadians(135)).waitSeconds(2).build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}