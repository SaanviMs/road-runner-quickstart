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
                drive.actionBuilder(new Pose2d(8, 63, 0))
                        .splineTo(new Vector2d(-58, 42), Math.toRadians(270))
                        .afterTime(0.1, () -> intake.setPower(1))  // pick up sample
                        .turn(Math.toRadians(90))
                        .splineTo(new Vector2d(52, 54), Math.toRadians(45))
                        .afterTime(0.1, () -> intake.setPower(-1)) // drop sample
                        .build()
        );

        intake.setPower(0); // stop intake
    }
}
