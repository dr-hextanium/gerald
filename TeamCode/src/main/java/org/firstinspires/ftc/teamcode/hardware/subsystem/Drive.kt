package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.curve

class Drive : ISubsystem {
	var x = 0.0
	var y = 0.0
	var theta = 0.0

	override fun reset() {

	}

	override fun read() {

	}

	override fun update() {
		x = curve(Robot.gamepad1.leftX)
		y = curve(Robot.gamepad1.leftY)
		theta = curve(Robot.gamepad1.rightX)
	}

	override fun write() {
	}
}