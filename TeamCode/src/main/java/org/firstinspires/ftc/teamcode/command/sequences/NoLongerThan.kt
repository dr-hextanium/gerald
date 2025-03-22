package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.Command
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.command.CommandTemplate

class NoLongerThan(val command: Command, val time: Long) : CommandTemplate() {
	val timer = ElapsedTime()

	override fun initialize() {
		timer.reset()
		command.initialize()
	}

	override fun execute() {
		command.execute()
	}

	override fun isFinished(): Boolean {
		if (command.isFinished) return true

		if (timer.milliseconds() >= time) {
			command.end(true)
			return true
		}

		return false
	}
}