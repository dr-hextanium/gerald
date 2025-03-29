package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FiveSpecimenPaths

@Autonomous(name = "5 Specimen")
class FiveSpecimen : AutoTemplate(FiveSpecimenPaths.startPose) {
	val paths by lazy { FiveSpecimenPaths() }

	val preload by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.preload, follower, maxPower = 0.7, holdEnd = true)
		)
	}

	val firstPush by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.firstPush, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val secondPush by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.secondPush, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val thirdPush by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.thirdPush, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val grabSecond by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.grabSecond, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val scoreSecond by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val grabThird by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.grabThird, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val scoreThird by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreThird, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val grabFourth by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.grabFourth, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val scoreFourth by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreFourth, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val grabFifth by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.grabFifth, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	val scoreFifth by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreFifth, follower, maxPower = 1.0, holdEnd = true)
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				ParallelCommandGroup(
					preload,
					SequentialCommandGroup(
						WaitCommand(250),
						PrepScoringSpecimen()
					)
				),

				ScoreSpecimen(),

				firstPush,
				secondPush,
				thirdPush,

				grabSecond,
				PrepScoringSpecimen(),
				scoreSecond,
				ScoreSpecimen(),

				grabThird,
				PrepScoringSpecimen(),
				scoreThird,
				ScoreSpecimen(),

				grabFourth,
				PrepScoringSpecimen(),
				scoreFourth,
				ScoreSpecimen(),

				grabFifth,
				PrepScoringSpecimen(),
				scoreFifth,
				ScoreSpecimen(),
			)
		)
	}
}