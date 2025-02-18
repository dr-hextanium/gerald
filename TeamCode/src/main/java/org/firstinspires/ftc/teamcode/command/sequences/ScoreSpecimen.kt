package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

class ScoreSpecimen : SequentialCommandGroup(
	LiftToUntil(Positions.Lift.HIGH_CHAMBER, time = 500),
	OpenDeposit(),
	WaitCommand(500),
	PrepGrabbingSpecimen(intake = false)
)