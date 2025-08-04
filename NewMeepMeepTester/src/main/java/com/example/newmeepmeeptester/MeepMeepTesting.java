package com.example.newmeepmeeptester;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -58, 0))
                .turn(Math.toRadians(90))

                .strafeTo(new Vector2d(58,-38))

                //drop off
                .strafeTo(new Vector2d(0,-45))
                .turn(Math.toRadians(135))
                .strafeTo(new Vector2d(-51,-52))


                //second sample pickup
                .strafeTo(new Vector2d(0,-45))
                .turn(Math.toRadians(-135))
                .strafeTo(new Vector2d(48,-38))

                //second sample drop off
                .strafeTo(new Vector2d(0,-46))
                .turn(Math.toRadians(135))
                .strafeTo(new Vector2d(-51,-52))

                //head to middle
//                        .strafeTo(new Vector2d(-36,-28))
//                        .turn(Math.toRadians(45))
//                        .lineToY(-12)
//                        .strafeTo(new Vector2d(-26,-12))
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
