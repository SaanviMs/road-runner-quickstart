package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name = "First RoadRunner Auton")
public class FirstRoadRunnerAuton extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Servo servo = hardwareMap.servo.get("servo");

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .lineToX(30)
                        .turn(Math.toRadians(90))
                        .lineToY(30)
                        .turn(Math.toRadians(90))
                        .lineToX(0)
                        .turn(Math.toRadians(90))
                        .lineToY(0)
                        .turn(Math.toRadians(90))
                        .stopAndAdd(new ServoAction(servo, 0.0))
                        .build()
        );
    }

    public static class ServoAction implements Action {
        private final Servo servo;
        private final double position;

        public ServoAction(Servo servo, double position) {
            this.servo = servo;
            this.position = position;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            servo.setPosition(position);
            return false;
        }
    }
}
