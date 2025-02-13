package org.firstinspires.ftc.teamcode.opmode.driver

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate

@TeleOp
class DriverControlled : BaseTemplate() {
	override fun initialize() {
		val primary = Robot.gamepad1
		val secondary = Robot.gamepad2


	}

	override fun cycle() {

	}
}