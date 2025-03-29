package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.functions.deg
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

class Deposit(val pivot: UsefulServo, val arm: Arm, val claw: Claw, val extension: UsefulServo) : ISubsystem {
	val all = listOf(arm, claw)

	override fun reset() = all.forEach { it.reset() }
	override fun read() = all.forEach { it.read() }
	override fun update() = all.forEach { it.update() }
	override fun write() = all.forEach { it.write() }

	fun pitchTo(angle: Double) { pivot.position = angle }

	fun raiseTo(angle: Double) = arm.turn(angle)

	fun extendTo(x: Double) {
		val numerator = x + sqrt(L.pow(2) - x.pow(2))
		val denominator = 2.0 * R

		val theta = acos(numerator / denominator)

		extension.position = theta
	}

	fun open() = claw.open()
	fun close() = claw.close()

	companion object {
		val L = 3.5
		val R = 3.5
	}
}