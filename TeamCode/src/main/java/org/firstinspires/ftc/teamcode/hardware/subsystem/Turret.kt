package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.deg

class Turret(val servo: UsefulServo) : ISubsystem {
    var angle = 0.deg

    override fun reset() {
        servo.position = 0.deg
    }

    override fun read() {}

    override fun update() {}

    override fun write() { servo.position = angle }

    fun turn(angle: Double) { this.angle = angle }
}