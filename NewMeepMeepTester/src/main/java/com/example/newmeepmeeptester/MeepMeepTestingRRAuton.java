package com.example.newmeepmeeptester;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestingRRAuton {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(55, -61, 0))
                        .turn(1.6)
                        .strafeTo(new Vector2d(46,-40))
                                .waitSeconds(0.1)                            // simulate intake down
                                .waitSeconds(0.05)
                        .turn(1.6)// simulate intake fan start
                                .strafeTo(new Vector2d(-47, -61))            // move to blue bin
                                .waitSeconds(0.2)                            // simulate intake motor reversed
                                .waitSeconds(0.1)                            // wrist rotate
                                .waitSeconds(0.1)                            // drop pixel
                                .waitSeconds(0.5)
                        .turn(1.5)// slides + servo
                                .strafeTo(new Vector2d(55, -61))            // final strafe
                        .turn(3.3)
                        .strafeTo(new Vector2d(58,-40))//second spike mark
                        .waitSeconds(0.1)                            // simulate intake down
                        .waitSeconds(0.05)
                        .strafeTo(new Vector2d(-47, -61))            // move to blue bin
                        .waitSeconds(0.2)                            // simulate intake motor reversed
                        .waitSeconds(0.1)                            // wrist rotate
                        .waitSeconds(0.1)                            // drop pixel
                        .waitSeconds(0.5)
                        .turn(1.5)// slides + servo
                        .strafeTo(new Vector2d(55, -61))
                        .turn(3.7)
                        .strafeTo(new Vector2d(63,-33))
                        .turn(3)
                        .strafeTo(new Vector2d(-47,-61))
                        .strafeTo(new Vector2d(-23,-5.6))


                        .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
