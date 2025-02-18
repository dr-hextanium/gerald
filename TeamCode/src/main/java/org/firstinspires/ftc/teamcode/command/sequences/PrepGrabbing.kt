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
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.INTERMEDIATE_ANGLE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.INTERMEDIATE_PITCH
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Turret.ASIDE
import org.firstinspires.ftc.teamcode.utility.functions.deg

class PrepGrabbing(intake: Boolean = true) : SequentialCommandGroup(
	PivotDeposit(Deposit.Pivot.GRAB_SPECIMEN),
	SwingDeposit(Deposit.Arm.GRAB_SPECIMEN),
	LiftToUntil(Positions.Lift.GRAB_SPECIMEN, time = 250),

	if (intake) {
		SequentialCommandGroup(
			SwingIntake(INTERMEDIATE_ANGLE),
			PitchIntake(INTERMEDIATE_PITCH),
			TwistIntake(0.0.deg),
			WaitCommand(200),

			ExtensionToUntil(0.0, time = 250),
			WaitCommand(200),

			CloseIntake(),
			PitchIntake(260.deg),
			SwingIntake(60.deg),
			TwistIntake((-45).deg),

			WaitCommand(200),

			TurnTurret(ASIDE)
		)
	} else {
		InstantCommand()
	}
)

