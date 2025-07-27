package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "Hi")
public class FirstRoadRunnerAuton extends LinearOpMode {

    public static class Intake {
        private final DcMotor intakeMotor;


        public Intake(HardwareMap hardwareMap) {
            intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
            intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public void setPower(double power) {
            intakeMotor.setPower(power);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(55, 63, 0))
                        .strafeTo(new Vector2d(45,-33))
                        /*.afterTime(0.1, () -> somerandommotor.moveintake(-1)) // Moves intake piece down
                        .afterTime(0.05, () -> somerandommotor.rotatefans(1))//starts intake*/
                        .strafeTo(new Vector2d(-58, -58))
                        /* .stopAndAdd(() ->somerandommotor.moveintake(1))//moves intake piece back to the blue bin
                         .afterTime(0.1,() -> somerandommotor.rotateWrist(-1)) //rotate wrist towards blue
                         .afterTime(0.1, () ->somerandommotor.rotatefans(1)) // drops pixel into blue bin
                         .afterTime(0.5, () -> {
                             somerandommotor.moveSlides(0.6);     // Raise slides
                             somerandommotor.setArmPosition(0.8); // Move servo arm
                             somerandommotor.moveSlides(0);     // stop slides
                         })*/
                        .strafeTo(new Vector2d(55, -65))
                        .build()
        );

        intake.setPower(0); // stop intake
    }
}
