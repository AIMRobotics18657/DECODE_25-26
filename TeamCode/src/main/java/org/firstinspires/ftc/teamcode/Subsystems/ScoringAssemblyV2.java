package org.firstinspires.ftc.teamcode.Subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScoringAssemblyV2 extends Mechanism {

    public VeloLaunch launcher = new VeloLaunch();
    public Intake intake = new Intake();
    public Hood hood = new Hood();
    public Gate gate = new Gate();

    private double g = 9.8;
    private double deltaH = 1.0815; //meters

    @Override
    public void init(HardwareMap hwMap) {
        launcher.init(hwMap);
        intake.init(hwMap);
        hood.init(hwMap);
        gate.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimPad) {
        launcher.loop(aimPad);
        intake.loop(aimPad);
        hood.loop(aimPad);
        gate.loop(aimPad);
        //get dist
    }

    /**
     * main function used for scoring
     **/
    public void score(double dist, double omega, double offsetDeg) {

        double scale = 2 * Math.PI / 628;
        double omegaScaled = omega * scale;
        hood.hood.setPosition(hoodDegrees(dist, omega, offsetDeg)); //TODO does this get the scale?\
        launcher.setVelo(omegaScaled);

        //TODO add thing for gate to sync with the hood and launcher
    }

    private double hoodDegrees(double dist, double omega, double offsetDeg){
        double wheelRadius = 0.096;
        double v = omega * wheelRadius;

        double hoodRad = solveTheta(dist, v);
        double hoodDeg = radToDeg(hoodRad);
        double minDeg = 30;
        double maxDeg = 65;
        hoodDeg += offsetDeg;

        double hoodPos = (hoodDeg - minDeg) /(maxDeg - minDeg);

        return hoodPos;
    }

    private double solveTheta(double dist, double v) {
        double thetaHigh = Math.atan((Math.pow(v, 2) + Math.sqrt(Math.pow(v, 4) - g * (g * Math.pow(dist, 2) + 2 * (deltaH) * Math.pow(v, 2)))) / (g * dist));

        double thetaLow = Math.atan((Math.pow(v, 2) - Math.sqrt(Math.pow(v, 4) - g * (g * Math.pow(dist, 2) + 2 * (deltaH) * Math.pow(v, 2)))) / (g * dist));


        return thetaLow;
    }

    private double radToDeg (double theta) {
        return Math.toDegrees(theta);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        launcher.telemetry(telemetry);
        intake.telemetry(telemetry);
        hood.telemetry(telemetry);
    }
}
