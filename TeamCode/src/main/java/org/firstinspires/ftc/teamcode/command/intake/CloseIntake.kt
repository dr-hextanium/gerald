package org.firstinspires.ftc.teamcode.command.intake

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class CloseIntake : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.intake.open() }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}