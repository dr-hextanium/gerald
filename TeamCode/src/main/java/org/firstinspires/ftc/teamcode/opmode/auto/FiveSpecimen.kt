package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.sequences.NoLongerThan
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FiveSpecimenPaths

@Autonomous(name = "5 Specimen")
class FiveSpecimen : AutoTemplate(FiveSpecimenPaths.startPose) {
	val paths by lazy { FiveSpecimenPaths(follower) }

	val preload by lazy {
		SequentialCommandGroup(
			ParallelCommandGroup(
				PedroPathCommand(paths.preload, follower, 0.6, holdEnd = true),

				SequentialCommandGroup(
					WaitCommand(250),
					PrepScoringSpecimen(),
				)
			),
		)
	}

	val goToAndPushFirst by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.behindAndPushFirst, follower, 1.0, holdEnd = true)
		)
	}

	val pushSecond by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.pushSecond, follower, 1.0, holdEnd = true)
		)
	}

	val pushThird by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.pushThird, follower, 1.0, holdEnd = true)
		)
	}

	val pullBack by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.pullBack, follower, 1.0, holdEnd = true)
		)
	}

	val scoreSecondSpecimen by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreSecondSpecimen, follower, 1.0, holdEnd = true)
		)
	}

	val grabFromWall by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.grabFromWall, follower, 1.0, holdEnd = true)
		)
	}

	val scoreFromWall by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.scoreFromWall, follower, 1.0, holdEnd = true)
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// preload
				NoLongerThan(preload, 2000),
				ScoreSpecimen(),

				// push all
				goToAndPushFirst,
				pushSecond,
				pushThird,

				// second specimen
				pullBack,
				PrepScoringSpecimen(),

				scoreSecondSpecimen,
				ScoreSpecimen(),

				// third specimen
				grabFromWall,
				PrepScoringSpecimen(),

				scoreFromWall,
				ScoreSpecimen(),

				// fourth specimen
				grabFromWall,
				PrepScoringSpecimen(),

				scoreFromWall,
				ScoreSpecimen(),

				// fifth specimen
				grabFromWall,
				PrepScoringSpecimen(),

				scoreFromWall,
				ScoreSpecimen(),
			)
		)
	}
}