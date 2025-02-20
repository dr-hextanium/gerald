package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions.Lift
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit

class PrepScoringSpecimen : SequentialCommandGroup(
	OpenIntake(),
	CloseDeposit(),

	ParallelCommandGroup(
		LiftToUntil(Lift.CLEARANCE, time = 500),

		ParallelCommandGroup(
			SwingDeposit(Deposit.Arm.SCORE_SPECIMEN),
			PivotDeposit(Deposit.Pivot.SCORE_SPECIMEN)
		),
	),
)