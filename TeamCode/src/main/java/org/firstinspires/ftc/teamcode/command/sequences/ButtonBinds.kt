package org.firstinspires.ftc.teamcode.command.sequences

import com.arcrobotics.ftclib.command.ConditionalCommand
import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.command.sequences.sample.Transfer
import org.firstinspires.ftc.teamcode.command.sequences.sample.ScoreSample
import org.firstinspires.ftc.teamcode.command.sequences.sample.BringToTransfer
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepGrabbingSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Robot

class PrimarySquareBind : ConditionalCommand(
	PrepIntakeSample(),
	PrepGrabbingSpecimen(),
	{ Robot.Subsystems.extension.target <= 0.0 }
)

class SecondarySquareBind : ConditionalCommand(
	PrepIntakeSample(),
	BringToTransfer(),
	{ Robot.Subsystems.extension.target <= 0.0 }
)

class PrimaryCrossBind : ConditionalCommand(
	ScoreSpecimen(),
	PrepScoringSpecimen().alongWith(InstantCommand({ Robot.follower.pose = Pose(6.500, 36.000, Robot.follower.pose.heading) })),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Arm.SCORE_SPECIMEN }
)

class SecondaryCrossBind : ConditionalCommand(
	ConditionalCommand(
		ParallelCommandGroup(
			BringToTransfer(safe = false),
			Transfer(),
		),
		Transfer(),
		{ Robot.Subsystems.extension.target >= 0.0 }
	),
	ScoreSample().alongWith(InstantCommand({ Robot.follower.pose = Pose(11.750, 131.00, Robot.follower.pose.heading) })),
	{ Robot.Subsystems.deposit.arm.angle == Deposit.Arm.TRANSFER_SAMPLE }
)