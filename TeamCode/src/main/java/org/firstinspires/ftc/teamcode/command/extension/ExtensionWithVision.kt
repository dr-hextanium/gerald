package org.firstinspires.ftc.teamcode.command.extension

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class ExtensionWithVision : CommandTemplate() {
	var distance = 0.0

	override fun initialize() {
		distance = Robot.Subsystems.vision.distance
		Robot.scheduler.schedule(ExtensionToUntil(distance, 0.5, time = 500))
	}

	override fun execute() {}

	override fun isFinished() = true
}