package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class DropDeposit(val angle: Double) : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.raiseTo(angle) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}