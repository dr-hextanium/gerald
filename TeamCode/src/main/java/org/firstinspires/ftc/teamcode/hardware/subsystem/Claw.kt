package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.Bound

class Claw(val servo: Servo, val bound: Bound) : ISubsystem {
    var position = 0.0

    override fun reset() {
        servo.scaleRange(bound.lower, bound.upper)
    }

    override fun read() { }

    override fun update() { }

    override fun write() { servo.position = position.coerceIn(0.0, 1.0) }

    fun open() { position = OPEN }
    fun close() { position = CLOSED }

    companion object {
        const val OPEN = 0.0
        const val CLOSED = 1.0
    }
}