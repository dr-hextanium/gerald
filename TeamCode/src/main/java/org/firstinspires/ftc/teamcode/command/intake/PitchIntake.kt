package org.firstinspires.ftc.teamcode.command.intake

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class PitchIntake(val angle: Double) : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.intake.pitch(angle) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}