package org.firstinspires.ftc.teamcode.opmode.debug.motor

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.curve

@TeleOp(group = "Debug")
class TwoMotorDirectionDebugger : BaseTemplate() {
	val left by lazy { Robot.Motors.Lift.left }
	val right by lazy { Robot.Motors.Lift.right }

	override fun initialize() {
		left.power = 0.0
		right.power = 0.0
	}

	override fun cycle() {
		val l = curve(gamepad1.left_trigger)
		val r = curve(gamepad1.right_trigger)

		val ticks = (r - l) * 1000

		Robot.Subsystems.lift.target = ticks

		telemetry.addData("ticks", ticks)
		telemetry.addData("position", Robot.Subsystems.lift.position)
		telemetry.addData("power", Robot.Subsystems.lift.power)
	}
}