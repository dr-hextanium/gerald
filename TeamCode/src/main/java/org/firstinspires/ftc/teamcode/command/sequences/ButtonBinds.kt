package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.ConditionalCommand
import org.firstinspires.ftc.teamcode.command.sequences.sample.PrepScoringSample
import org.firstinspires.ftc.teamcode.command.sequences.sample.ScoreSample
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepGrabbingSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Robot

class PrimarySquareBind : ConditionalCommand(
	PrepIntakeSample(),
	PrepGrabbingSpecimen(),
	{ Robot.Subsystems.extension.target == 0.0 }
)

class PrimaryCrossBind : ConditionalCommand(
	ScoreSpecimen(),
	PrepScoringSpecimen(),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Arm.SCORE_SPECIMEN }
)

class SecondaryCrossBind : ConditionalCommand(
	PrepScoringSample(),
	ScoreSample(),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Arm.TRANSFER_SAMPLE }
)