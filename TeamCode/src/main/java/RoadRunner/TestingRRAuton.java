package RoadRunner;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class TestingRRAuton extends LinearOpMode {
    ;
    public static class  Motors{
        DcMotor intakeMotor, slides;
        Servo armServo;
        CRServo intake, wrist;
        public Motors(HardwareMap hardwareMap) {
            intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
            intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            slides = hardwareMap.get(DcMotor.class, "Intake");
            slides.setDirection(DcMotorSimple.Direction.REVERSE);
            slides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armServo = hardwareMap.get(Servo.class,"armServo");
            intake = hardwareMap.get(CRServo.class,"intake");
            wrist = hardwareMap.get(CRServo.class,"wrist");

        }
        public void moveSlides(double power) {
            slides.setPower(power);
        }
        public void setArmPosition(double position) {
            armServo.setPosition(position); // 0.0 to 1.0
        }
        public void moveintake(double power){
            intakeMotor.setPower(power);
        }
        public void rotatefans(double power){
            intake.setPower(power);
        }
        public void rotateWrist(double power){
            wrist.setPower(power);
        }

    }

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Motors somerandommotor = new Motors(hardwareMap);


        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(55, -65, 0)) //drives to first pixel mark
                        .strafeTo(new Vector2d(45,-33))
                        .afterTime(0.1, () -> somerandommotor.moveintake(-1)) // Moves intake piece down
                        .afterTime(0.05, () -> somerandommotor.rotatefans(1))//starts intake
                        .strafeTo(new Vector2d(-58, -58))
                        .stopAndAdd(() ->somerandommotor.moveintake(1))//moves intake piece back to the blue bin
                        .afterTime(0.1,() -> somerandommotor.rotateWrist(-1)) //rotate wrist towards blue
                        .afterTime(0.1, () ->somerandommotor.rotatefans(1)) // drops pixel into blue bin
                        .afterTime(0.5, () -> {
                            somerandommotor.moveSlides(0.6);     // Raise slides
                            somerandommotor.setArmPosition(0.8); // Move servo arm
                            somerandommotor.moveSlides(0);     // stop slides
                        })
                        .strafeTo(new Vector2d(55, -65))
                        .build()
        ); somerandommotor.moveintake(0);
        somerandommotor.moveSlides(0);

    }
}

        /*alr so this code is kinda confusing and complicated but i decided to do it this way anyway. so bscly
how the code works is, in the main class, we have a class called motors. In that class we got all our diff motors.
Now the thing is, we gotta call these motors at 48 so we can use it during the trajectory sequence, but we got multiple
motors and we can call it once with one variable. So what i decided to do is make actions before the "TS", like "moveSlides" and "stopSlides".
Now i can js say variablename.actionname(power/position). for example "somerandommotor.moveSlides(0.6)" when i want to move the slides.
Now i dunno if this is the smartest way but im coding this at 11 after traveling a while and nothing makes sense so. now i js gotta pray this works.*/
