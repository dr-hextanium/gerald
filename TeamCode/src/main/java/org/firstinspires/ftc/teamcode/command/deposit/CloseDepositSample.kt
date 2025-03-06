package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit.Claw.CLOSED_SAMPLE
import org.firstinspires.ftc.teamcode.hardware.Robot

class CloseDepositSample : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.claw.manual(CLOSED_SAMPLE) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}