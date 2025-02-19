package org.firstinspires.ftc.teamcode.command.lift

import com.arcrobotics.ftclib.command.ParallelRaceGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class AltLiftTo(val position: Double, val tolerance: Double) : CommandTemplate() {
	val lift = Robot.Subsystems.altLift

	override fun initialize() {
		lift.target(position)
		lift.go()
	}

	override fun execute() { }

	override fun isFinished() = lift.within(tolerance)
}

class AltLiftToUntil(position: Double, tolerance: Double = 0.02, time: Long = 1000) : ParallelRaceGroup(
	AltLiftTo(position, tolerance),
	WaitCommand(time)
)