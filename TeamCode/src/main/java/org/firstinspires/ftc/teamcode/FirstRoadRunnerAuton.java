package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="Hi")
public class FirstRoadRunnerAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0)); //enter acc stuff later
        Servo servo = hardwareMap.servo.get("servo");


        waitForStart();

        Actions.runBlocking(

                drive.actionBuilder(new Pose2d(65, -65, 0))

                        .splineTo(new Vector2d(30, -20), Math.toRadians(90))

                        .build());
    }
}