package org.firstinspires.ftc.teamcode.hardware.subsystem

import dev.frozenmilk.dairy.cachinghardware.CachingCRServo

class Hang(
	val left1: CachingCRServo,
	val left2: CachingCRServo,
	val right1: CachingCRServo,
	val right2: CachingCRServo
): ISubsystem {
	var power = 0.0

	override fun reset() { power = 0.0 }

	override fun read() {  }

	override fun update() {  }

	override fun write() {
		left1.power = power
		left2.power = power
		right1.power = -(power)
		right2.power = -(power)
	}
}