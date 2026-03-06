package com.example.meepmeep.Far;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepsBlueFar {
    public static void main(String[] args) {
        double SHOOTING_ANGLE = (Math.PI) - Math.atan((double) -44 / 118.5);
        Pose2d SHOOTING_POS = new Pose2d(55, -15, SHOOTING_ANGLE);
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(18, 16)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(SHOOTING_POS)
                .waitSeconds(3)
                //first three artifacts
                .setTangent(Math.toRadians(-10))
                .splineToLinearHeading(new Pose2d(36, -25, Math.toRadians(-90)), Math.toRadians(-90))//setup(might be hard to stop on a dime here)
                .setTangent(Math.toRadians(-90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(36, -53, Math.toRadians(-90)), Math.toRadians(90))//collect
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(0))//to shooting
                .waitSeconds(3)
                .splineToLinearHeading(new Pose2d(50.8, -40, Math.toRadians(-90)), Math.toRadians(-90))
                .strafeTo(new Vector2d(50.8, -57.5))
                .strafeTo(new Vector2d(50.8, -40))
                .strafeTo(new Vector2d(27.5, -40))
                .strafeTo(new Vector2d(27.5, -57.5))
                .strafeTo(new Vector2d(27.5, -40))
                .splineToLinearHeading(SHOOTING_POS, Math.toRadians(90))
                .waitSeconds(3)
                .setTangent(Math.toRadians(320))
                .splineToLinearHeading(new Pose2d(38, -33, Math.toRadians(-90)), Math.toRadians(-90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}