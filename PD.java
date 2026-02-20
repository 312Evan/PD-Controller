package frc.robot.utility;

import edu.wpi.first.wpilibj.Timer;

public class PD {
    private double p;
    private double d;
    private double prevError;
    private double prevUpdate;
    private Timer time = new Timer();

    public PD(double p, double d){
        this.p = p;
        this.d = d;
        time.start();
        prevUpdate = time.get();
    }

    public double calculate(double current, double setpoint){
        double error = setpoint - current;
        double p = this.p * error;
        double timeBetween = time.get() - prevUpdate;
        double d = this.d * (error - prevError) / timeBetween;

        prevUpdate = time.get();
        prevError = error;

        return p + d;
    }

    public void reset(){
        prevError = 0;
        prevUpdate = time.get();
    }
}