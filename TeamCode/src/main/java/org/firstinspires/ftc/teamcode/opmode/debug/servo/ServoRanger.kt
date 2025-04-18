package org.firstinspires.ftc.teamcode.opmode.debug.servo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.deg
import kotlin.math.max
import kotlin.math.sin

@TeleOp
class ServoRanger : BaseTemplate() {
	var start = 0.deg
	var end = 0.deg
	var sweep = false

	val servo by lazy { Robot.Servos.Deposit.extension }
	val range by lazy { servo.range.radians }

	override fun initialize() {
		start = range.lower
		end = range.upper
	}

	override fun cycle() {
		if (gamepad1.left_bumper) sweep = false
		if (gamepad1.right_bumper) sweep = true

		start += when {
			gamepad1.dpad_right -> 1.deg
			gamepad1.dpad_left -> (-1).deg
			else -> 0.0
		}

		end += when {
			gamepad1.dpad_up -> 1.deg
			gamepad1.dpad_down -> (-1).deg
			else -> 0.0
		}

		start = start.coerceIn(range.lower, range.upper)
		end = end.coerceIn(range.lower, range.upper)

		end = max(start, end)

		servo.position = when {
			gamepad1.right_trigger > 0.0 -> servo.convert(gamepad1.right_trigger.toDouble())

			gamepad1.square -> start
			gamepad1.circle -> end

			else -> servo.convert(
				if (sweep) sin(runtime) / 2.0 + 0.5 else 0.5
			)
		}

		telemetry.addData("position", servo.position)
		telemetry.addData("start", Math.toDegrees(start))
		telemetry.addData("end", Math.toDegrees(end))
	}
}