package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp
public class Zen4A extends OpMode {
    double Speed = -gamepad1.left_stick_y * 0.5;
    double X = gamepad1.right_stick_x;
    double Y = gamepad1.right_stick_y;

    @Override
    public void init(){
    }

    @Override
    public  void loop(){
        if(gamepad1.left_stick_y < -0.5){
            telemetry.addData("Left stick", " is negative and large");
        }

        else if (gamepad1.left_stick_y < 0){
            telemetry.addData("Left stick", " is negative and small");
        }

        else if (gamepad1.left_stick_y < 0.5){
            telemetry.addData("Left stick", " is positive and small");
        }

        else {
            telemetry.addData("Left stick", " is positive and large");
        }

        //Turbo Mode
        if (gamepad1.a){
            Speed = Speed * 2;
        } else {
            Speed = 0.5;
        }

        //Crazy mode
        if (gamepad1.a){
            if(gamepad1.right_stick_y > 0){
                telemetry.addData("Y ", X);

            }

            if(gamepad1.right_stick_x > 0){
                telemetry.addData("X", Y);
            }
        }

        else{
            telemetry.addData("X", X);
            telemetry.addData("Y", Y);
        }
    }