package org.firstinspires.ftc.teamcode.command.hang

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class StopHang: CommandTemplate() {
	val hang = Robot.Subsystems.hang

	override fun initialize() {
		hang.power = 0.0
	}

	override fun execute() {  }

	override fun isFinished(): Boolean = true
}