package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.functions.deg

class Arm(val left: UsefulServo, val right: UsefulServo) : ISubsystem {
	var angle = 0.deg

	override fun reset() {
		left.bound()
		right.bound()
	}

	override fun read() {}

	override fun update() {}

	override fun write() {
		left.position = angle
		right.position = angle
	}

	fun turn(angle: Double) { this.angle = angle }
}