package org.firstinspires.ftc.teamcode.command.hang

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class ExtendHang: CommandTemplate() {
	val hang = Robot.Subsystems.hang

	override fun initialize() {
		hang.power = -1.0
	}

	override fun execute() {  }

	override fun isFinished(): Boolean = true
}