package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.GRAB_ANGLE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.INTERMEDIATE_ANGLE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.INTAKE_PITCH
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.INTERMEDIATE_PITCH
import org.firstinspires.ftc.teamcode.utility.functions.deg

class IntakeSample : SequentialCommandGroup(
	OpenIntake(),
	SwingIntake(GRAB_ANGLE),
	PitchIntake(INTAKE_PITCH),
	WaitCommand(200),

	CloseIntake(),
	WaitCommand(200),

	SwingIntake(INTERMEDIATE_ANGLE),
	PitchIntake(INTERMEDIATE_PITCH),
	TwistIntake(0.0.deg)
)