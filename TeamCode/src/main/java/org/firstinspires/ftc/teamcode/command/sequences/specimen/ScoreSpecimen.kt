package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit

class ScoreSpecimen(letGo: Boolean = true, shouldExtend: Boolean = true) : SequentialCommandGroup(
	if (letGo) {
		SequentialCommandGroup(
			OpenDeposit(),
			ParallelCommandGroup(
				PrepGrabbingSpecimen(intake = false, shouldExtend = shouldExtend)
			)
		)
	} else {
		InstantCommand()
	}
)