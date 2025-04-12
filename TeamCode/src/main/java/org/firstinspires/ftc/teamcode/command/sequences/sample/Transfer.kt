package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ExtendDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

//class Transfer : SequentialCommandGroup(
//	PrepTransfer(),
//	WaitCommand(200),
//	CloseDeposit(),
//	OpenIntake(),
//	ExtensionToUntil(0.0, time = 500),
//	ParallelCommandGroup(
//		LiftToUntil(Positions.Lift.HIGH_BASKET, time = 750, tolerance = 0.5),
//		SequentialCommandGroup(
//			WaitCommand(400),
//			ParallelCommandGroup(
//				SwingDeposit(Positions.Deposit.Arm.SCORE_SAMPLE),
//				PivotDeposit(Positions.Deposit.Pivot.SCORE_SAMPLE),
//				SequentialCommandGroup(
//					WaitCommand(100),
//					ExtendDeposit()
//				)
//			),
//		)
//	),
//)


class Transfer : SequentialCommandGroup(
	PrepTransfer(),
	WaitCommand(200),
	CloseDeposit(),
	WaitCommand(150),
	OpenIntake(),
	ExtensionToUntil(0.0, time = 500),
	ParallelCommandGroup(
		LiftToUntil(Positions.Lift.HIGH_BASKET, time = 750, tolerance = 0.5),
		SequentialCommandGroup(
			WaitCommand(400),
			ParallelCommandGroup(
				SwingDeposit(Positions.Deposit.Arm.SCORE_SAMPLE),
				PivotDeposit(Positions.Deposit.Pivot.SCORE_SAMPLE),
				SequentialCommandGroup(
					WaitCommand(100),
					ExtendDeposit()
				)
			),
		)
	),
)