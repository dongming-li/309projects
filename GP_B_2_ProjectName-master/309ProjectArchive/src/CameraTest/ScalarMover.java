/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CameraTest;

/**
 *
 * @author mlscholl
 */

public class ScalarMover {

    public double maxAcceleration = 200.0f;
    public double maxDeceleration = 200.0f;
    public double current;
    public double target;
    public double velocity;

    public void update(float elapsedTimeInSeconds) {
        if (current == target) {
            return;
        }
        double currentToTarget = target - current;
        double directStopDistance = (velocity * velocity) / (2.0 * maxDeceleration);
        double acceleration = 0.0;
        if (velocity * currentToTarget > 0.0 && directStopDistance >= Math.abs(currentToTarget)) {
            /* Decelerate */
            double directDec = maxDeceleration;
            acceleration = (currentToTarget < 0.0 ? -1 : 1) * -directDec;
        } else {
            /* Accelerate */
            double directAcc = maxAcceleration;
            acceleration = (currentToTarget < 0.0 ? -1 : 1) * directAcc;
        }
        velocity += acceleration * elapsedTimeInSeconds;
        double way = velocity * elapsedTimeInSeconds;
        if (velocity * currentToTarget > 0.0 && Math.abs(way) > Math.abs(currentToTarget)) {
            /* We would move too far */
            velocity = 0.0;
            current = target;
        } else {
            current += way;
        }
    }

}