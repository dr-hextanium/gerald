package org.firstinspires.ftc.teamcode.command.intake

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.hardware.Positions

class TuckIntake : SequentialCommandGroup(
	ParallelCommandGroup(
		SwingIntake(Positions.Intake.Arm.TUCK),
		PitchIntake(Positions.Intake.Claw.TUCK_PITCH)
	),
	WaitCommand(100),
	TurnTurret(Positions.Intake.Turret.TUCK)
)