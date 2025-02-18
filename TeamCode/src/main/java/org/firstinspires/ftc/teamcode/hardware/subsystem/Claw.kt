package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo

class Claw(val servo: UsefulServo, val opened: Double, val closed: Double) : ISubsystem {
	var position = 0.0

	override fun reset() {}

	override fun read() {}

	override fun update() {}

	override fun write() {
		servo.position = position
	}

	fun manual(position: Double) {
		this.position = position
		write()
	}

	fun open() {
		this.position = opened
		write()
	}

	fun close() {
		this.position = closed
		write()
	}

	fun isOpen() = position == opened
	fun isClosed() = position == closed
}