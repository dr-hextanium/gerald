package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.ConditionalCommand
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Robot

class SquareBind : ConditionalCommand(
	PrepIntakeSample(),
	PrepGrabbingSpecimen(),
	{ Robot.Subsystems.extension.target == 0.0 }
)

class CrossBind : ConditionalCommand(
	ScoreSpecimen(),
	PrepScoringSpecimen(),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Arm.SCORE_SPECIMEN }
)