package com.example.meepmeep.Far;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepsRedFar {
    public static void main(String[] args) {
        double SHOOTING_ANGLE = (Math.PI) - Math.atan((double) 44 / 118.5);
        Pose2d SHOOTING_POS = new Pose2d(55, 15, SHOOTING_ANGLE);
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(18, 16)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(SHOOTING_POS)
                .waitSeconds(3)
                //first three artifacts
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(40, 25, Math.toRadians(90)), Math.toRadians(180))//setup
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(40, 59, Math.toRadians(90)), Math.toRadians(-90))//collect
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(0))//to shooting
                .setTangent(Math.toRadians(-230))
                .splineToLinearHeading(new Pose2d(30, 45, Math.toRadians(-340)), Math.toRadians(-320))
                //.splineToLinearHeading(new Pose2d(38, -62, Math.toRadians(0)), Math.toRadians(270))
                .strafeTo(new Vector2d(55,60))
                .strafeTo(new Vector2d(40,49))
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(62, 52))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(180))
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(40, 15, Math.toRadians(180)), Math.toRadians(180))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}