package org.firstinspires.ftc.teamcode.command.deposit

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit.Extension.EXTENDED
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit.Extension.TRANSFER_EXTENSION
import org.firstinspires.ftc.teamcode.hardware.Robot

class ExtendDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.extension.position = EXTENDED }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}

class TransferExtendDeposit : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.deposit.extension.position = TRANSFER_EXTENSION }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}

