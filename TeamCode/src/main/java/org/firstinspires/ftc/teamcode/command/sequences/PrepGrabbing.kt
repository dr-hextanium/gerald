package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.subsystem.Deposit
import org.firstinspires.ftc.teamcode.hardware.subsystem.Intake
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift
import org.firstinspires.ftc.teamcode.utility.functions.deg

class PrepGrabbing(intake: Boolean = true) : SequentialCommandGroup(
	PivotDeposit(Deposit.Companion.Deposit.PIVOT_GRAB_SPECIMEN),
	SwingDeposit(Deposit.Companion.Deposit.ARM_GRAB_SPECIMEN),
	LiftToUntil(Lift.GRAB_SPEC, time = 250),

	if (intake) {
		SequentialCommandGroup(
			SwingIntake(Intake.INTERMEDIATE_ARM_ANGLE),
			PitchIntake(Intake.INTERMEDIATE_INTAKE_PITCH),
			TwistIntake(0.0.deg),
			WaitCommand(200),

			ExtensionToUntil(0.0, time = 250),
			WaitCommand(200),

			CloseIntake(),
			PitchIntake(260.deg),
			SwingIntake(60.deg),
			TwistIntake((-45).deg),
			WaitCommand(200),

			TurnTurret(215.deg)
		)
	} else {
		InstantCommand()
	}
)

