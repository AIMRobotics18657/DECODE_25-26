package org.firstinspires.ftc.teamcode.Trash;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor extends Mechanism {

    private NormalizedColorSensor colorSen;

    private float normRed, normGreen, normBlue;

    private enum DetectedColor {
        GREEN,
        PURPLE,
        UNKNOWN
    }



    public void init(HardwareMap hwMap) {
        colorSen = hwMap.get(NormalizedColorSensor.class, "sen");

        ///TODO gain is default one play around with it and see what works
        colorSen.setGain(1);
    }

    public void loop(AIMPad aimPad) {

    }

    public DetectedColor getDetectedColor() {
        NormalizedRGBA colors = colorSen.getNormalizedColors();


        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        ///TODO add if statements for specific colors added

        return DetectedColor.UNKNOWN;
    }

    public void telemetry (Telemetry telemetry) {
        telemetry.addData("Red:", normRed);
        telemetry.addData("Green:", normGreen);
        telemetry.addData("Blue:", normBlue);
    }

}
