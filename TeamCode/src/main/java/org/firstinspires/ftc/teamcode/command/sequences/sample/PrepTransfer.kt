package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

class PrepTransfer : SequentialCommandGroup(
	ParallelCommandGroup(
		SwingIntake(Positions.Intake.Arm.TRANSFER_SAMPLE),
		PitchIntake(Positions.Intake.Claw.TRANSFER_SAMPLE_PITCH),
		TurnTurret(Positions.Intake.Turret.CENTER),
		ExtensionToUntil(0.0, time = 500),
	),
	WaitCommand(100),

	ParallelCommandGroup(
		LiftToUntil(0.0),
		SwingDeposit(Positions.Deposit.Arm.TRANSFER_SAMPLE),
		PivotDeposit(Positions.Deposit.Pivot.TRANSFER_SAMPLE),
		OpenDeposit()
	),
)