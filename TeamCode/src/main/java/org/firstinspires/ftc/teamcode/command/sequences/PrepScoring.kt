package org.firstinspires.ftc.teamcode.command.sequences

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

class PrepScoring : SequentialCommandGroup(
	OpenIntake(),
	WaitCommand(100),
	CloseDeposit(),
	WaitCommand(200),

	LiftToUntil(Lift.CLEARANCE, time = 250),

	ParallelCommandGroup(
		SwingDeposit(Deposit.Arm.SCORE_SPECIMEN),
		PivotDeposit(Deposit.Pivot.SCORE_SPECIMEN)
	),

	WaitCommand(100)
)