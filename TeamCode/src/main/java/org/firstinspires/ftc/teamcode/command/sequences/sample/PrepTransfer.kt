package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.ExtendDeposit
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.RetractDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.TransferExtendDeposit
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.TRANSFER_INTERMEDIATE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.TRANSFER_INTERMEDIATE_SQUARED
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.TRANSFER_SAMPLE

//class PrepTransfer : SequentialCommandGroup(
//	SwingDeposit(Positions.Deposit.Arm.TRANSFER_INTERMEDIATE),
//	OpenDeposit(),
//
//	TurnTurret(Positions.Intake.Turret.CENTER),
//	PitchIntake(Positions.Intake.Claw.TRANSFER_SAMPLE_PITCH),
//	SwingIntake(TRANSFER_SAMPLE),
//
//	TransferExtendDeposit(),
//
//	LiftToUntil(-1.0, time = 400),
//
//	SwingDeposit(Positions.Deposit.Arm.TRANSFER_SAMPLE),
//	PivotDeposit(Positions.Deposit.Pivot.TRANSFER_SAMPLE),
//	WaitCommand(100),
//)

class PrepTransfer : SequentialCommandGroup(
	OpenDeposit(),
	RetractDeposit(),
	SwingDeposit(Positions.Deposit.Arm.TRANSFER_SAMPLE),
	PivotDeposit(Positions.Deposit.Pivot.TRANSFER_SAMPLE),
	PitchIntake(Positions.Intake.Claw.TRANSFER_SAMPLE_PITCH),
	SwingIntake(TRANSFER_INTERMEDIATE_SQUARED),
	LiftToUntil(-1.0, time = 400),
	ParallelCommandGroup(
		SwingIntake(Positions.Intake.Arm.TRANSFER_SAMPLE),
		TurnTurret(Positions.Intake.Turret.CENTER),
	),
	WaitCommand(100),
)