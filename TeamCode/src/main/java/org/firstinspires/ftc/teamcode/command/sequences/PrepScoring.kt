package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.subsystem.Deposit
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift

class PrepScoring : SequentialCommandGroup(
	OpenIntake(),
	WaitCommand(100),
	CloseDeposit(),
	WaitCommand(200),

	LiftToUntil(Lift.SPEC_CLEARANCE_HEIGHT, 250),

	ParallelCommandGroup(
		SwingDeposit(Deposit.Companion.Deposit.ARM_SCORE_SPECIMEN),
		PivotDeposit(Deposit.Companion.Deposit.PIVOT_SCORE_SPECIMEN)
	),

	WaitCommand(100)
)