package org.firstinspires.ftc.teamcode.opmode.debug.subsystems.turret

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@TeleOp
class DepositTest : BaseTemplate() {
	override fun initialize() {}

	override fun cycle() {
		Robot.Subsystems.intake.arm.turn((355.0 * gamepad1.right_trigger).deg)

		telemetry.addData("angle", (355.0 * gamepad1.right_trigger))
	}
}