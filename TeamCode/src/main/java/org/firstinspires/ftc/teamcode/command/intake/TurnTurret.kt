package org.firstinspires.ftc.teamcode.command.intake

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class TurnTurret(val angle: Double) : CommandTemplate(Robot.Subsystems.intake) {
	override fun initialize() { Robot.Subsystems.intake.turnTo(angle) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}