package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class OpenDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.open() }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}