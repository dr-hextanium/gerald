package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class PivotDeposit(val angle: Double) : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.pitchTo(angle) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}