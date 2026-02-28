package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepsRed {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(16,18)//ask for correct height
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55 + Math.sqrt(50),55 - Math.sqrt(50), Math.toRadians(135))) // starts with middle on launch line
                .strafeTo(new Vector2d(-10, 10))
                        .waitSeconds(3)
                .setTangent(90)
                .splineToLinearHeading(new Pose2d(-11.5, 25, Math.toRadians(90)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-11.5, 53, Math.toRadians(90)), Math.toRadians(90)) // push into wall
                        .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-10, 10, Math.toRadians(135)), Math.toRadians(270)) // go to shooting
                        .waitSeconds(3) // shoot

                // next three artifacts
                        .setTangent(Math.toRadians(45))
                .splineToLinearHeading(new Pose2d(12, 25, Math.toRadians(90)), Math.toRadians(90)) // setup position
                .splineToLinearHeading(new Pose2d(12, 53, Math.toRadians(90)), Math.toRadians(90)) // push into wall has to be 90
                        .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-10, 10, Math.toRadians(90)), Math.toRadians(270)) // go to shooting
                        .waitSeconds(3)

                //last three artifacts
                        .setTangent(Math.toRadians(10))
                .splineToLinearHeading(new Pose2d(36, 25, Math.toRadians(90)), Math.toRadians(15))//setup(might be hard to stop on a dime here)
                        .setTangent(Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(36, 53, Math.toRadians(90)), Math.toRadians(90))//collect
                        .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-10, 10, Math.toRadians(135)), Math.toRadians(230))//to shooting
                        .waitSeconds(3)
                //park
                        .setTangent(Math.toRadians(320))
                .splineToLinearHeading(new Pose2d(38, -33, Math.toRadians(90)), Math.toRadians(330))
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}