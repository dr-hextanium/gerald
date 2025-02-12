package org.firstinspires.ftc.teamcode.opmode.debug.servo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.Diffy
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@TeleOp
class ZeroServos : BaseTemplate() {
	private val left by lazy { Robot.Servos.Diffy.left }
	private val right by lazy { Robot.Servos.Diffy.right }

	override fun initialize() {

	}

	override fun cycle() {
		val vertical = (355.0) * gamepad1.right_trigger
		val horizontal = (355.0) * gamepad1.left_trigger

		Robot.Subsystems.diffy.to(Diffy.Kinematics.State(vertical.deg, horizontal.deg))
	}
}