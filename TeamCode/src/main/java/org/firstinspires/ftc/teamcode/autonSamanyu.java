package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "autonSamanyu")
public class autonSamanyu extends LinearOpMode {

    private DcMotor arm;  // class-level arm motor
    private MecanumDrive drive;

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

    // Define moveArmTo outside runOpMode, can access class field 'arm'
    private Action moveArmTo(int targetTicks) {
        arm.setTargetPosition(targetTicks);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(1.0);

        return (elapsedTime) -> {
            return !arm.isBusy();
        };
    }


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, -58, 0));
        Intake intake = new Intake(hardwareMap);
        arm = hardwareMap.get(DcMotor.class, "Arm");  // initialize arm motor

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, -58, 0))
                        // go to first sample
                        .turn(Math.toRadians(90))
                        .strafeTo(new Vector2d(58, -38))
                        .afterTime(1, moveArmTo(100))  // raise arm after 1 second driving
                        // first sample drop off
                        .strafeTo(new Vector2d(0, -45))
                        .turn(Math.toRadians(135))
                        .strafeTo(new Vector2d(-51, -52))

                        // second sample pickup
                        .strafeTo(new Vector2d(0, -45))
                        .turn(Math.toRadians(-135))
                        .strafeTo(new Vector2d(48, -38))

                        // second sample drop off
                        .strafeTo(new Vector2d(0, -46))
                        .turn(Math.toRadians(135))
                        .strafeTo(new Vector2d(-51, -52))

                        // head to middle
                        .strafeTo(new Vector2d(-36, -28))
                        .turn(Math.toRadians(45))
                        .lineToY(-12)
                        .strafeTo(new Vector2d(-26, -12))
                        .build()
        );

        intake.setPower(0); // stop intake
    }
}
