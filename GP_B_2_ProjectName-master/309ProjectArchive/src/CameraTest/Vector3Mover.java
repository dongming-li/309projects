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
import org.joml.Vector3f;

public class Vector3Mover {

    public static final float SMALL_VALUE_THRESHOLD = 1E-5f;

    /**
     * The maximum acceleration directly towards the target.
     */
    public float maxDirectAcceleration = 20.0f;

    /**
     * The maximum deceleration directly towards the target.
     */
    public float maxDirectDeceleration = 100.0f;

    /**
     * The maximum deceleration (in positive values) towards the velocity
     * component perpendicular to the target direction.
     */
    public float maxPerpendicularDeceleration = 30.0f;

    /**
     * The current position. This will change after an invocation to
     * {@link #update(float)}.
     */
    public final Vector3f current = new Vector3f();

    /**
     * The desired target position. Set this to any value at any time.
     */
    public final Vector3f target = new Vector3f();

    /**
     * The current acceleration. MUST NOT be modified from outside.
     */
    public final Vector3f acceleration = new Vector3f();
    /**
     * The current velocity. MUST NOT be modified from outside.
     */
    public final Vector3f velocity = new Vector3f();

    /* Some helper objects. JOML did not use any, but joml-camera now has to. */

    private final Vector3f currentToTarget = new Vector3f();
    private final Vector3f currentToTargetNormalized = new Vector3f();
    private final Vector3f perpendicularVelocityComponent = new Vector3f();
    private final Vector3f directVelocityComponent = new Vector3f();
    private final Vector3f directAcceleration = new Vector3f();
    private final Vector3f perpendicularAcceleration = new Vector3f();
    private final Vector3f newAcceleration = new Vector3f();
    private final Vector3f newVelocity = new Vector3f();
    private final Vector3f way = new Vector3f();

    /**
     * Update the simulation based on the elapsed time since the last update.
     * 
     * @param elapsedTimeInSeconds
     *            the elapsed time in seconds since the last update
     */
    public void update(float elapsedTimeInSeconds) {
        /* Compute the way we need to got */
        currentToTarget.set(target).sub(current);
        if (currentToTarget.length() < 1E-5) {
            return;
        }
        currentToTargetNormalized.set(currentToTarget).normalize();

        /*
         * Dot product in order to project the velocity onto the target
         * direction.
         */
        float dot = currentToTargetNormalized.dot(velocity);

        /*
         * Compute the perpendicular velocity component (how much of the current
         * velocity is directed exactly perpendicular to the target).
         */
        perpendicularVelocityComponent.set(currentToTargetNormalized);
        perpendicularVelocityComponent.mul(dot);
        perpendicularVelocityComponent.sub(velocity);
        /*
         * Now this contains the vector to eliminate the perpendicular
         * component, i.e. it is directed towards the line of sight between the
         * target and current.
         */

        /*
         * Compute the direct velocity component (how much of the current
         * velocity is directed towards the target).
         */
        directVelocityComponent.set(currentToTargetNormalized);
        directVelocityComponent.mul(Math.abs(dot));

        /*
         * In which time can we reach complete zero perpendicular movement?
         */
        float timeToStopPerpendicular = perpendicularVelocityComponent.length() / maxPerpendicularDeceleration;
        /*
         * This is how long our whole movement to the target needs to take at
         * least in order for the perpendicular movement to stop (which we
         * want!). The problem now is that the length of the direct way depends
         * on the perpendicular movement. The more we move in the perpendicular
         * direction, the longer the direct path becomes.
         */

        /*
         * Compute how far we would move along the direct component if we
         * completely eliminate this velocity component.
         */
        float directStopDistance = directVelocityComponent.lengthSquared() / (2.0f * maxDirectDeceleration);
        /*
         * Now see how much time it will take us to fully stop the direct
         * movement.
         */
        float timeToStopDirect = directVelocityComponent.length() / maxDirectDeceleration;

        /*
         * Check if we need to decelerate the direct component, because we would
         * move too far if we didn't.
         */
        if (dot >= SMALL_VALUE_THRESHOLD
                && (directStopDistance >= currentToTarget.length() || timeToStopPerpendicular > timeToStopDirect)) {
            /* We need to decelerate the direct component */
            directAcceleration.set(currentToTargetNormalized).mul(maxDirectDeceleration).negate();
        } else {
            /*
             * We can still accelerate directly towards the target. Compute the
             * necessary acceleration to reach the target in the elapsed time.
             */
            float neededDirectAcc = currentToTarget.length() / elapsedTimeInSeconds;
            float directAcc = neededDirectAcc;
            /* Check if that would be too much acceleration */
            if (neededDirectAcc > maxDirectAcceleration) {
                /* Limit to maximum allowed acceleration */
                directAcc = maxDirectAcceleration;
            }
            directAcceleration.set(currentToTargetNormalized).mul(directAcc);
        }

        /*
         * Compute the perpendicular deceleration. If maximum deceleration would
         * be too much for the time, we compute the optimal deceleration based
         * on the elapsed time.
         */
        float neededPerpendicularAcc = perpendicularVelocityComponent.length() / elapsedTimeInSeconds;
        float perpendicularDeceleration = neededPerpendicularAcc;
        /* Check if that would be too much acceleration */
        if (neededPerpendicularAcc > maxPerpendicularDeceleration) {
            /* Limit to maximum allowed acceleration */
            perpendicularDeceleration = maxPerpendicularDeceleration;
        }
        /* If the perpendicular velocity would be too small */
        if (perpendicularVelocityComponent.length() > SMALL_VALUE_THRESHOLD) {
            perpendicularAcceleration.set(perpendicularVelocityComponent).normalize().mul(perpendicularDeceleration);
        } else {
            perpendicularAcceleration.set(0.0f, 0.0f, 0.0f);
        }

        /* Compute new acceleration */
        newAcceleration.set(directAcceleration).add(perpendicularAcceleration);
        /* Compute new velocity */
        newVelocity.set(newAcceleration).mul(elapsedTimeInSeconds).add(velocity);
        velocity.set(newVelocity);

        way.set(velocity).mul(elapsedTimeInSeconds);
        if (way.length() > currentToTarget.length()) {
            velocity.zero();
            way.set(currentToTarget);
        }

        /* Compute new current position based on updated velocity */
        current.add(way);
    }

}
