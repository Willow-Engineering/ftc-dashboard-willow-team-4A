package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Run_Robot")
@Config
public class Run_Robot extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotorEx armDrive = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    private TouchSensor touchSensor;
    public static double rightServoPosOpen = 1;
    public static double leftServoPosOpen = 0;
    public static double rightServoPosClose = 0.8;
    public static double leftServoPosClose = 0.2;
    public static double armDrivePosUp = 150;
    public static double armDrivePosDown = 150;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        leftDrive  = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightDrive = hardwareMap.get(DcMotor.class, "RightMotor");
        armDrive = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        leftServo = hardwareMap.get(Servo.class, "LeftServo");
        rightServo = hardwareMap.get(Servo.class, "RightServo");
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        //armDrive.setDirection(DcMotor.Direction.REVERSE);
        //leftServo.setDirection(Servo.Direction.FORWARD);
        //rightServo.setDirection(Servo.Direction.FORWARD);




        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        waitForStart();
        runtime.reset();
        armDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            double sensorValue = touchSensor.getValue();

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            if(gamepad1.a) {
                armDrive.setTargetPosition((int) armDrivePosUp);
                armDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                armDrive.setVelocity(150);
            }
            if(gamepad1.b) {
                armDrive.setTargetPosition((int) armDrivePosDown);
                armDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                armDrive.setVelocity(150);
            }
            if(gamepad1.start) {
                armDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                armDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            if(gamepad1.dpad_left){
                rightServo.setPosition(rightServoPosOpen);
                leftServo.setPosition(leftServoPosOpen);
            }
            if(gamepad1.dpad_right){
                rightServo.setPosition(rightServoPosClose);
                leftServo.setPosition(leftServoPosClose);
            }
            if (touchSensor.isPressed()) {
                telemetry.addData("Touching ", "Something");
            }

            telemetry.addData("Arm Test", armDrive.getCurrentPosition());
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("LeftServo", leftServo.getPosition());
            telemetry.addData("RightServo", rightServo.getPosition());
            telemetry.addData("Touch Sensor", touchSensor.isPressed());
            telemetry.update();
        }
    }
}