package org.firstinspires.ftc.teamcode.opmode.driver

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@TeleOp
class DriverControlled : BaseTemplate() {
	override fun initialize() {

	}

	override fun cycle() {
		Robot.Subsystems.frontArm.turn((90.0 * gamepad1.left_stick_y).deg)
		Robot.Subsystems.turret.turn((270.0 * gamepad1.left_stick_x).deg)

		Robot.Subsystems.backArm.turn((300.0 * gamepad1.right_stick_y).deg)
		Robot.Servos.Deposit.pivot.position = (180.0 * gamepad1.right_stick_x).deg
	}
}