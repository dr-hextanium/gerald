package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.command.sequences.SecondaryCrossBind
import org.firstinspires.ftc.teamcode.command.sequences.SecondarySquareBind
import org.firstinspires.ftc.teamcode.command.sequences.sample.ScoreSample
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FourSamplePaths

@Autonomous(name = "4 Sample")
class FourSample : AutoTemplate(FourSamplePaths.startPose) {
	val paths by lazy { FourSamplePaths }

	val preload by lazy {
		PedroPathCommand(
			paths.preload, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val lineUpFirst by lazy {
		PedroPathCommand(
			paths.lineUpFirst, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val scoreFirst by lazy {
		PedroPathCommand(
			paths.scoreFirst, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val lineUpSecond by lazy {
		PedroPathCommand(
			paths.lineUpSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val scoreSecond by lazy {
		PedroPathCommand(
			paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val lineUpThird by lazy {
		PedroPathCommand(
			paths.lineUpThird, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val scoreThird by lazy {
		PedroPathCommand(
			paths.scoreThird, follower, maxPower = 1.0, holdEnd = true
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// score preload
				ParallelCommandGroup(
					preload,
					SequentialCommandGroup(
						LiftToUntil(Positions.Lift.HIGH_BASKET),
						ParallelCommandGroup(
							SwingDeposit(Positions.Deposit.Arm.SCORE_SAMPLE),
							PivotDeposit(Positions.Deposit.Pivot.SCORE_SAMPLE)
						)
					)
				),
				WaitCommand(300),
				ScoreSample(),

				lineUpFirst,
				SecondarySquareBind(),
				SecondarySquareBind(),
				SecondaryCrossBind(),

				scoreFirst,
				WaitCommand(300),
				SecondaryCrossBind(),

				lineUpSecond,
				SecondarySquareBind(),
				SecondarySquareBind(),
				SecondaryCrossBind(),

				scoreSecond,
				WaitCommand(300),
				SecondaryCrossBind(),

				lineUpThird,
				SecondarySquareBind(),
				SecondarySquareBind(),
				SecondaryCrossBind(),

				scoreThird,
				WaitCommand(300),
				SecondaryCrossBind(),
			)
		)
	}
}