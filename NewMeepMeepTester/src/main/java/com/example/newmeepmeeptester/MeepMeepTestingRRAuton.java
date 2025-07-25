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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(55, -65, 0))
                        .strafeTo(new Vector2d(47,-33))
                                .waitSeconds(0.1)                            // simulate intake down
                                .waitSeconds(0.05)                           // simulate intake fan start
                                .strafeTo(new Vector2d(-58, -58))            // move to blue bin
                                .waitSeconds(0.2)                            // simulate intake motor reversed
                                .waitSeconds(0.1)                            // wrist rotate
                                .waitSeconds(0.1)                            // drop pixel
                                .waitSeconds(0.5)                            // slides + servo
                                .strafeTo(new Vector2d(55, -65))            // final strafe
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
