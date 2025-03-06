package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

class PrepScoringSample : SequentialCommandGroup(
	LiftToUntil(10.0),
	ParallelCommandGroup(
		SwingDeposit(Positions.Deposit.Arm.SCORE_SAMPLE),
		PivotDeposit(Positions.Deposit.Pivot.SCORE_SAMPLE)
	)
)