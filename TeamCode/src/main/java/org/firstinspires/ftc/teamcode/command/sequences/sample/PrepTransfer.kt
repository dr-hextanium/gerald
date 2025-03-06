package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.utility.functions.deg

class PrepTransfer : SequentialCommandGroup(
	ParallelCommandGroup(
		LiftToUntil(0.0),
		SwingDeposit(Positions.Deposit.Arm.TRANSFER_SAMPLE),
		PivotDeposit(Positions.Deposit.Pivot.TRANSFER_SAMPLE),
		OpenDeposit()
	),
	WaitCommand(100),
	ParallelCommandGroup(
		SwingIntake(Positions.Intake.Arm.TRANSFER_SAMPLE),
		PitchIntake(Positions.Intake.Claw.TRANSFER_SAMPLE_PITCH),
		TurnTurret(79.0.deg)
	)
)