package org.firstinspires.ftc.teamcode.command.extension

import com.arcrobotics.ftclib.command.ParallelRaceGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.command.lift.LiftTo
import org.firstinspires.ftc.teamcode.hardware.Robot

class ExtensionTo(val position: Double, val tolerance: Double) : CommandTemplate() {
	val extension = Robot.Subsystems.extension

	override fun initialize() { extension.target = position }

	override fun execute() { }

	override fun isFinished() = extension.within(tolerance)
}

class ExtensionToUntil(position: Double, tolerance: Double = 0.02, time: Long = 1000) : ParallelRaceGroup(
	ExtensionTo(position, tolerance),
	WaitCommand(time)
)