package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public class Joey4A {
}
@TeleOp()
public class HelloWorldCommented extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello","World");
    }
    @Override
    public void loop() {

    }
}