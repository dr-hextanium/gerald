package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.deg

class Arm(val left: UsefulServo, val right: UsefulServo) : ISubsystem {
	var angle = 0.deg

	override fun reset() {
		left.bound()
		right.bound()

		left.position = 0.deg
		right.position = 0.deg
	}

	override fun read() {}

	override fun update() {}

	override fun write() {
		left.position = angle
		right.position = angle
	}

	fun turn(angle: Double) { this.angle = angle }
}