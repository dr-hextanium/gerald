package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionTo
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftTo
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.subsystem.Deposit
import org.firstinspires.ftc.teamcode.hardware.subsystem.Intake
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift
import org.firstinspires.ftc.teamcode.utility.deg

class ToIntake : SequentialCommandGroup(
	PivotDeposit(Deposit.Companion.Deposit.PIVOT_GRAB_SPECIMEN),
	SwingDeposit(Deposit.Companion.Deposit.ARM_GRAB_SPECIMEN),
	LiftToUntil(Lift.GRAB_SPEC, 300),

	ExtensionToUntil(15.0, 300),
	TurnTurret(79.deg),
	SwingIntake(Intake.INTERMEDIATE_ARM_ANGLE),
	PitchIntake(Intake.INTERMEDIATE_INTAKE_PITCH),
	TwistIntake(0.0.deg)
)