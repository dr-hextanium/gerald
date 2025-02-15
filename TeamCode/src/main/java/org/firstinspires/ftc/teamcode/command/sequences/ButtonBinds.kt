package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.ConditionalCommand
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.Deposit

class SquareBind : ConditionalCommand(
	ToIntake(),
	PrepGrabbing(),
	{ Robot.Subsystems.extension.target == 0.0 }
)

class CrossBind : ConditionalCommand(
	ScoreSpecimen(),
	PrepScoring(),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Companion.Deposit.ARM_SCORE_SPECIMEN }
)