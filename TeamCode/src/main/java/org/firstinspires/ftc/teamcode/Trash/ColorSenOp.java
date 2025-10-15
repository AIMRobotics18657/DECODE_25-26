package org.firstinspires.ftc.teamcode.Trash;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ColorSenOp extends OpMode {

    ColorSensor colorSensor = new ColorSensor();

    public void init () {
        colorSensor.init(hardwareMap);
    }

    public void loop() {

    }
}
