package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.RetractDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TuckIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions

class ScoreSample : SequentialCommandGroup(
	OpenDeposit(),
	WaitCommand(150),
	SequentialCommandGroup(
		OpenDeposit(),
		RetractDeposit(),
		SwingDeposit(Positions.Deposit.Arm.TRANSFER_SAMPLE),
		PivotDeposit(Positions.Deposit.Pivot.TRANSFER_SAMPLE),
		PitchIntake(Positions.Intake.Claw.TRANSFER_SAMPLE_PITCH),
		ParallelCommandGroup(
			LiftToUntil(-1.0, time = 400),
			SwingIntake(Positions.Intake.Arm.TUCK),
			TurnTurret(Positions.Intake.Turret.TUCK),
			PitchIntake(Positions.Intake.Claw.TUCK_PITCH)
		)
	)
)