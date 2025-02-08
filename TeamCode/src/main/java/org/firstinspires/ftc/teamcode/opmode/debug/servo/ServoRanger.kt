package org.firstinspires.ftc.teamcode.opmode.debug.servo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg
import kotlin.math.sin

@TeleOp
class ServoRanger : BaseTemplate() {
	var start = 0.0
	var end = 0.5
	var sweep = false

	val servo by lazy { Robot.Servos.turretServo }

	override fun initialize() {}

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

		servo.position = when {
			gamepad1.right_trigger > 0.0 -> servo.convert(gamepad1.right_trigger.toDouble())

			gamepad1.square -> start
			gamepad1.circle -> end

			else -> if (sweep) sin(runtime) / 2.0 + 0.5 else 0.5
		}

		telemetry.addData("position", servo.position)
		telemetry.addData("start", Math.toDegrees(start))
		telemetry.addData("end", Math.toDegrees(end))
	}
}