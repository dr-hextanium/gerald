package org.firstinspires.ftc.teamcode.command.intake

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.Claw

class OpenIntake : CommandTemplate() {
	override fun initialize() {
		Robot.Subsystems.intake.claw.manual(Positions.Intake.Claw.OPEN)
	}

	override fun execute() {}

	override fun isFinished(): Boolean = true
}