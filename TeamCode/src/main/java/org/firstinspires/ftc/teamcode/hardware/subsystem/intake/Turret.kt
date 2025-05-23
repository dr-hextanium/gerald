package org.firstinspires.ftc.teamcode.hardware.subsystem.intake

import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.functions.deg

class Turret(val servo: UsefulServo, val default: Double = 0.deg) : ISubsystem {
    var angle = default

    override fun reset() {}

    override fun read() {}

    override fun update() {}

    override fun write() { servo.position = angle }

    fun turn(angle: Double) { this.angle = angle }
}