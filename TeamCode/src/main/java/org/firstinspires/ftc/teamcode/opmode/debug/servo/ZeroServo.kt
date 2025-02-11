package org.firstinspires.ftc.teamcode.opmode.debug.servo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@TeleOp
class ZeroServos : BaseTemplate() {
	private val leftArm by lazy { Robot.Servos.Deposit.left }
	private val rightArm by lazy { Robot.Servos.Deposit.right }
	private val frontClaw by lazy { Robot.Servos.Diffy.claw }

	override fun initialize() {  }

	override fun cycle() {
		leftArm.position = 355.0.deg
		rightArm.position = 355.0.deg
	}
}