package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.arcrobotics.ftclib.command.Subsystem

/**
 * Following the KookyBotz[1] model for the loop-time optimization of subsystems,
 * we have `ISubsystem`. The idea is that subsystems sequence and isolate all of their
 * required reads, updates, and writes respectively.
 *
 * This serves as dual-purpose: firstly, by isolating specific tasks to their own methods,
 * you can see where all of your, say, writes really take place and prevent having many
 * scattered writes throughout. Secondly, by performing these operations in the order described
 * above, we ensure that we are never acting on "stale" information.
 *
 * Before we write to hardware, we update our internal class state, to ensure that writes are
 * actually present.
 *
 * Before we update our class state, we read from our hardware devices, to ensure that the
 * computations performed are reflective of the present.
 *
 * In the method docs below, I will use the example of a lift mechanism.
 *
 * [1] While I am unsure of if KookyBotz pioneered this model, they are attributed to it.
 */
interface ISubsystem : Subsystem {
    /**
     * Reset all hardware and state associated with this subsystem.
     * Should perform exactly the same as an initialization method.
     *
     * Reset lift encoder, set all motor powers to 0.
     */
    fun reset()

    /**
     * Read from all hardware devices associated with this subsystem.
     *
     * Read lift encoder.
     */
    fun read()

    /**
     * Update, compute, and calculate the internal class state.
     *
     * Have target be set, calculate PID loop power, compensate for gravity.
     */
    fun update()

    /**
     * Write to all hardware devices based on class state.
     *
     * Set powers to lift motors.
     */
    fun write()
}