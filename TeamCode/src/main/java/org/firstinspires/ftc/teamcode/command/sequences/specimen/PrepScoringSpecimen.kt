package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions.Lift
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit

class PrepScoringSpecimen(val lift: Boolean = true) : SequentialCommandGroup(
	OpenIntake(),
	CloseDeposit(),

	WaitCommand(200),

	ParallelCommandGroup(
		if (lift) {
			LiftToUntil(Lift.CLEARANCE, time = 500)
		} else InstantCommand(),

		ParallelCommandGroup(
			SwingDeposit(Deposit.Arm.SCORE_SPECIMEN),
			PivotDeposit(Deposit.Pivot.SCORE_SPECIMEN)
		),
	),
)