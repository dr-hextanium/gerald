package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class CloseDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.close() }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}