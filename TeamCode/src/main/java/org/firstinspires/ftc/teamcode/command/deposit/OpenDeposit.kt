package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit.Claw.OPEN
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.Claw

class OpenDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.claw.manual(OPEN) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}