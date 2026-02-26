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
    public Limelight limelight = new Limelight();
    public double hoodDeg;
    double lldist;

    private double g = 9.8;
    private double deltaH = 1.0815; //meters

    public enum distancePhase {
        ONE,
        TWO,
        THREE,
        FAR,
        NA;
    }
    public distancePhase distPhase = distancePhase.NA;

    @Override
    public void init(HardwareMap hwMap) {
        launcher.init(hwMap);
        intake.init(hwMap);
        hood.init(hwMap);
        gate.init(hwMap);
        limelight.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimPad) {
        launcher.loop(aimPad);
        intake.loop(aimPad);
        hood.loop(aimPad);
        gate.loop(aimPad);
        limelight.loop(aimPad);

        //TODO fix inches for borders
        if (limelight.llResult.isValid()) {
            if (limelight.distance <= 55) {
                distPhase = distancePhase.ONE;
            } else if (limelight.distance > 55 && limelight.distance <= 70) {
                distPhase = distancePhase.TWO;
            } else if (limelight.distance > 72 && limelight.distance <= 82) {
                distPhase = distancePhase.THREE;
            } else if (limelight.distance > 82) {
                distPhase = distancePhase.FAR;
            }
        } else {
            distPhase = distancePhase.NA;
        }

        lldist = limelight.distance * 0.0254;
        //get dist
    }

    /**
     * main function used for scoring
     **/
    public void score(double dist, double omega, double offsetDeg, double distOffset, double omegaOffset) {

        double scale = 2 * Math.PI / 628;
        double gearRatio = 14/10;
        double launcherOmegaScaled = omega * scale;
        double hoodOmegaScaled = omega * gearRatio;
        double offsetDist = dist + distOffset;
        double offsetOmega = hoodOmegaScaled - omegaOffset;
        hood.hood.setPosition(hoodDegrees(offsetDist, offsetOmega, offsetDeg)); //TODO does this get the scale?\
        launcher.setVelo(launcherOmegaScaled);

        //TODO add thing for gate to sync with the hood and launcher
    }

    private double hoodDegrees(double dist, double omega, double offsetDeg){
        double wheelRadius = 0.096;
        double v = omega * wheelRadius;

        double hoodRad = solveTheta(dist, v);
        hoodDeg = radToDeg(hoodRad);
        double minDeg = 32;//17;
        double maxDeg = 72;//56;
        hoodDeg += offsetDeg;
        //double complementAngle = 90 - hoodDeg;


        double hoodPos = (hoodDeg - minDeg) /(maxDeg - minDeg); //TODO make it so we can never get illegal servo exception

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
        telemetry.addData("hood angle", hoodDeg);
        telemetry.addData("distance phase", distPhase);
        launcher.telemetry(telemetry);
        intake.telemetry(telemetry);
        hood.telemetry(telemetry);
        limelight.telemetry(telemetry);
    }
}
