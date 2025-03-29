package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

class ScoreSpecimen(letGo: Boolean = true, shouldExtend: Boolean = true) : SequentialCommandGroup(
	if (letGo) {
		SequentialCommandGroup(
			OpenDeposit(),
			ParallelCommandGroup(
				LiftToUntil(Positions.Lift.GRAB_SPECIMEN, time = 250),
				PrepGrabbingSpecimen(intake = false, shouldExtend = shouldExtend)
			)
		)
	} else {
		InstantCommand()
	}
)