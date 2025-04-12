package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController
import kotlin.math.abs

@Config
class Extension(
	val motor: CachingDcMotorEx,
	val encoder: CachingDcMotorEx
) : ISubsystem {
	val controller = VCPIDFController(kP, kI, kD, kF, 13.0)

	var position = 0.0
	var target = 0.0
	var power = 0.0

	val extended: Boolean
		get() = target > 1.0

	override fun reset() {
		encoder.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
		encoder.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
		motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
		motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
		motor.power = 0.0
		motor.setCurrentAlert(3.5, CurrentUnit.AMPS)

		position = 0.0
		target = 0.0

		controller.clamp(-MAX_POWER, MAX_POWER)
		controller.threshold(THRESHOLD)
	}

	override fun read() {
		position = -(encoder.currentPosition) * inchesPerTick
	}

	override fun update() {
		controller.updateCoefficients(kP, kI, kD, kF, alpha)

		power = controller.calculate(position, target)

		Robot.telemetry.addData("extendo power", power)
		Robot.telemetry.addData("extendo position", position)
		Robot.telemetry.addData("extendo target", target)
	}

	override fun write() {
		motor.power = power
	}

	fun busy() = !controller.atSetPoint()

	fun within(tolerance: Double) = abs(target - position) <= tolerance

	companion object {
		@JvmField
		var kP = 0.11
		@JvmField
		var kI = 0.007
		@JvmField
		var kD = 0.000
		@JvmField
		var kF = 0.00
		@JvmField
		var alpha = 0.8

		const val ticksPerInch = 600.0 / 18.5
		const val inchesPerTick = 1.0 / ticksPerInch

		const val MAX_POWER = 0.8
		const val THRESHOLD = 0.01
	}
}