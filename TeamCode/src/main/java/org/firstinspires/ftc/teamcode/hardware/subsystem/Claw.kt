package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.Bound
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServoRange

class Claw(val servo: UsefulServo, val opened: Double, val closed: Double) : ISubsystem {
    val range = servo.range.radians
    var position = 0.0

    override fun reset() {}

    override fun read() {}

    override fun update() {}

    override fun write() { servo.position = position.coerceIn(range.lower, range.upper) }

    fun open() { position = opened }
    fun close() { position = closed }
}