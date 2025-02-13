package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.deg

class Deposit(val pivot: UsefulServo, val arm: Arm, val claw: Claw) : ISubsystem {
	val all = listOf(arm, claw)

	override fun reset() = all.forEach { it.reset() }
	override fun read() = all.forEach { it.read() }
	override fun update() = all.forEach { it.update() }
	override fun write() = all.forEach { it.write() }

	fun pitchTo(angle: Double) { pivot.position = angle }

	fun raiseTo(angle: Double) = arm.turn(angle)

	fun open() = claw.open()

	fun close() = claw.close()

	companion object {
		object Intake {
			val INTAKE_SAMPLE = 40.deg
		}

		object Deposit {
			val GRAB_SPECIMEN = 340.deg
		}
	}
}