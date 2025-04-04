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
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Positions.Lift
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake
import org.firstinspires.ftc.teamcode.utility.functions.deg

class PrepIntakeSample : SequentialCommandGroup(
	ExtensionToUntil(Positions.Extension.MAX, time = 300),
	TurnTurret(Intake.Turret.CENTER),

	SwingIntake(Intake.Arm.INTERMEDIATE_ANGLE),
	PitchIntake(Intake.Claw.INTERMEDIATE_PITCH),

	TwistIntake(0.0.deg)
)