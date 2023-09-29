package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Zen4A extends OpMode {
    private DcMotor motor1;
    double Speed = -gamepad1.left_stick_y * 0.5;
    double X = gamepad1.right_stick_x;
    double Y = gamepad1.right_stick_y;

    @Override
    public void init() {
<<<<<<< HEAD
        motor1  = hardwareMap.get(DcMotor.class, "testMotor1");
=======
>>>>>>> 62e4dbbcb86fb9d62cb60fa82b6e711cc8b5b530
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y < -0.5) {
            telemetry.addData("Left stick", " is negative and large");
        } else if (gamepad1.left_stick_y < 0) {
            telemetry.addData("Left stick", " is negative and small");
        } else if (gamepad1.left_stick_y < 0.5) {
            telemetry.addData("Left stick", " is positive and small");
        } else {
            telemetry.addData("Left stick", " is positive and large");
        }

        //Turbo Mode
        if (gamepad1.a) {
            Speed = Speed * 2;
        } else {
            Speed = 0.5;
        }
        //if driver presses b press motor `
        if (gamepad1.b) {
            motor1.setPower(1);
        }

        //Crazy mode
        if (gamepad1.a) {
            if (gamepad1.right_stick_y > 0) {
                telemetry.addData("Y ", X);

            }

            if (gamepad1.right_stick_x > 0) {
                telemetry.addData("X", Y);
            }
        } else {
            telemetry.addData("X", X);
            telemetry.addData("Y", Y);
        }
    }
}