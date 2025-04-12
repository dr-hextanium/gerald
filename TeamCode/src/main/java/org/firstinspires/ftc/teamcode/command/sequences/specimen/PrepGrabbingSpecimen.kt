package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.RetractDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.ASIDE_PITCH
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.ASIDE_TWIST
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Turret.ASIDE

class PrepGrabbingSpecimen(intake: Boolean = true, shouldExtend: Boolean = true) : SequentialCommandGroup(
	PivotDeposit(Deposit.Pivot.GRAB_SPECIMEN),
	WaitCommand(50),
	RetractDeposit(),
	SwingDeposit(Deposit.Arm.GRAB_SPECIMEN),
	LiftToUntil(Positions.Lift.GRAB_SPECIMEN, time = 250),

	if (intake) {
		SequentialCommandGroup(
			CloseIntake(),

			ParallelCommandGroup(
				if (shouldExtend) {
					SequentialCommandGroup(
						ExtensionToUntil(0.0, time = 250),
						WaitCommand(200),
					)
				} else {
					InstantCommand()
				},

				SequentialCommandGroup(
					PitchIntake(ASIDE_PITCH),
					SwingIntake(Positions.Intake.Arm.ASIDE),
					TwistIntake(ASIDE_TWIST),
					WaitCommand(600),
					TurnTurret(ASIDE)
				)
			),
		)
	} else {
		InstantCommand()
	}
)

