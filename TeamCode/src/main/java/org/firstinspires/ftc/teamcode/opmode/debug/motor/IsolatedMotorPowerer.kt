package org.firstinspires.ftc.teamcode.opmode.debug.motor

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.utility.curve

@TeleOp
class IsolatedMotorPowerer : OpMode() {
	val motor by lazy { hardwareMap["e"] as DcMotorEx }

	override fun init() { }

	override fun loop() {
		val left = curve(gamepad1.left_trigger)
		val right = curve(gamepad1.right_trigger)

		val power = right - left

		motor.power = power

		telemetry.addData("power", power)
	}
}