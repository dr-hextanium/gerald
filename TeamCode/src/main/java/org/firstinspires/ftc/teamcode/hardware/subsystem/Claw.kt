package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.deg

class Claw(val servo: UsefulServo, val opened: Double, val closed: Double) : ISubsystem {
    val range = servo.range.radians
    var position = 0.0

    override fun reset() {}

    override fun read() {}

    override fun update() {}

    override fun write() { servo.position = position }

    fun manual(position: Double) { this.position = position }
    fun open() { position = opened }
    fun close() { position = closed }

    companion object {
        object Deposit {
            val OPEN = 1.5.deg
            val CLOSE = 0.0.deg
        }

        object Intake {
            val OPEN = 4.5.deg
            val CLOSE = 6.0.deg
        }
    }
}