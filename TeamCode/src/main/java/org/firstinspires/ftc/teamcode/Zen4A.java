package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Zen4A", group="Linear OpMode")
public class Zen4A extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotorEx arm = null;
    private DigitalChannel touch = null;
    Servo leftIntake;
    Servo rightIntake;

    public void runOpMode(){
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        arm = (DcMotorEx) hardwareMap.get(DcMotor.class, "arm");
        leftIntake = hardwareMap.get(Servo.class, "leftIntake");
        rightIntake = hardwareMap.get(Servo.class, "rightIntake");
        touch = hardwareMap.get(DigitalChannel.class, "touch");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (opModeIsActive()){
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;

            double leftPower = Range.clip(drive + turn, -1.0, 1.0);
            double rightPower = Range.clip(drive - turn, -1.0, 1.0);

            double leftIntakePos = -.1 * gamepad1.right_trigger + .5;
            double rightIntakePos = .1 * gamepad1.right_trigger + .5;

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            if (!touch.getState()) {
                if(gamepad1.right_bumper){
                    arm.setVelocity(-300);
                }
                else{
                    arm.setVelocity(0);
                }
            }
            else if(touch.getState()){
                if(gamepad1.right_bumper){
                    arm.setVelocity(-300);
                }
                else if(gamepad1.left_bumper){
                    arm.setVelocity(300);
                }
            }

            if(gamepad1.a) {
                arm.setTargetPosition(-1500);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setVelocity(300);
            }
            else if(gamepad1.b) {
                arm.setTargetPosition(-750);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setVelocity(300);
            }
            else {
                arm.setVelocity(0);
            }
            leftIntake.setPosition(leftIntakePos);
            rightIntake.setPosition(rightIntakePos);

            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Intake", "left (%.2f), right (%.2f)", leftIntake.getPosition(), rightIntake.getPosition());
        }
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Arm Position,", arm.getCurrentPosition());
        telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
        telemetry.update();

    }
}
