package org.firstinspires.ftc.teamcode;

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
                drive.actionBuilder(new Pose2d(0, -58, 0))
                        .turn(Math.toRadians(16.5))
                        .lineToX(58)
                        .turn(Math.toRadians(73.5)) //174
                        .turn(Math.toRadians(100.5))
                        .lineToX(-40)
                        .turn(Math.toRadians(181))
                        .lineToX(48)
                        .turn(Math.toRadians(75))
//                        .turn(Math.toRadians(90))
//                        .lineToX(-37)
//                        .turn(Math.toRadians(-95))
//                        .lineToY(-7)

                        .build());


        intake.setPower(0); // stop intake
    }
}
