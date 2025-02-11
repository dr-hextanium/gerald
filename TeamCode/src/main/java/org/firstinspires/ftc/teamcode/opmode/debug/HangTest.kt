package org.firstinspires.ftc.teamcode.opmode.debug

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate

@TeleOp
class HangTest : BaseTemplate() {
	val leftHang by lazy { Robot.Servos.Hang.left }
	val rightHang by lazy { Robot.Servos.Hang.right }

	override fun initialize() { }

	override fun cycle() {
		if(gamepad1.dpad_up) leftHang.power = 1.0
		else if(gamepad1.dpad_down) leftHang.power = -1.0
		else leftHang.power = 0.0

		if(gamepad1.y) rightHang.power = 1.0
		else if(gamepad1.a) rightHang.power = -1.0
		else rightHang.power = 0.0
	}
}