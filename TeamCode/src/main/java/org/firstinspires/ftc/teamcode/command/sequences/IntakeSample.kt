package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.hardware.subsystem.Intake
import org.firstinspires.ftc.teamcode.utility.functions.deg

class IntakeSample : SequentialCommandGroup(
	SwingIntake(Intake.GRAB_ARM_ANGLE),
	PitchIntake(Intake.GRAB_INTAKE_PITCH),
	WaitCommand(500),

	CloseIntake(),
	WaitCommand(200),

	SwingIntake(Intake.INTERMEDIATE_ARM_ANGLE),
	PitchIntake(Intake.INTERMEDIATE_INTAKE_PITCH),
	TwistIntake(0.0.deg)
)