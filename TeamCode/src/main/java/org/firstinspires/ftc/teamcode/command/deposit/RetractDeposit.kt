package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit.Extension.RETRACTED
import org.firstinspires.ftc.teamcode.hardware.Robot

class RetractDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.extension.position = RETRACTED }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}