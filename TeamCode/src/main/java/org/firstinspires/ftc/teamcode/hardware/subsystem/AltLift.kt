package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController
import kotlin.math.abs
import kotlin.math.sign

@Config
class AltLift(
	val left: CachingDcMotorEx,
	val right: CachingDcMotorEx
) : ISubsystem {
	val motors = listOf(left, right)

	var position = 0
	var target = 0

	val inches
		get() = ticksToInches(position)

	override fun reset() {
		left.direction = DcMotorSimple.Direction.REVERSE

		position = 0
		target = 0

		motors.forEach {
			it.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
			it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
			it.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
			it.targetPositionTolerance = THRESHOLD

			it.targetPosition = 0
			it.power = 0.0

			it.setCurrentAlert(3.5, CurrentUnit.AMPS)
		}
	}

	override fun read() { position = right.currentPosition }

	override fun update() {
		motors.forEach { it.targetPosition = target }
		motors.forEach { it.mode = DcMotor.RunMode.RUN_TO_POSITION }
	}

	override fun write() { }

	fun target(inches: Double) { target = (inches * ticksPerInch).toInt() }

	fun nudge(inches: Double) { target += inchesToTicks(inches) }

	fun go() = motors.forEach { it.power = MAX_POWER }
	fun stop() = motors.forEach { it.power = 0.0 }
	fun busy() = motors.map { it.isBusy }.reduce { a, b -> a || b }
	fun within(tolerance: Double) = abs(target - position) <= tolerance

	fun inchesToTicks(inches: Double) = (inches * ticksPerInch).toInt()
	fun ticksToInches(ticks: Double) = (ticks * inchesPerTick).toInt()
	fun ticksToInches(ticks: Int) = ticksToInches(ticks.toDouble())

	companion object {
		const val ticksPerInch = 1150.0 / 35.0
		const val inchesPerTick = 1.0 / ticksPerInch

		const val MAX_POWER = 0.8
		const val THRESHOLD = 3 // ticks
	}
}