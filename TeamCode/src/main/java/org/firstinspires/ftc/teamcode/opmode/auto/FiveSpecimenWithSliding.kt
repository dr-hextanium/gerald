package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.pedropathing.util.CustomFilteredPIDFCoefficients
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FiveSpecimenPaths
import org.firstinspires.ftc.teamcode.paths.FiveSpecimenWithSlidingPaths

@Autonomous(name = "5 Specimen With Sliding")
class FiveSpecimenWithSliding : AutoTemplate(FiveSpecimenPaths.startPose) {
	val paths by lazy { FiveSpecimenWithSlidingPaths() }

	val preload by lazy { PedroPathCommand(paths.preload, follower, maxPower = 0.75, holdEnd = true) }
	val firstPush by lazy { PedroPathCommand(paths.firstPush, follower, maxPower = 1.0, holdEnd = true) }
	val secondPush by lazy { PedroPathCommand(paths.secondPush, follower, maxPower = 1.0, holdEnd = true) }
	val thirdPush by lazy { PedroPathCommand(paths.thirdPush, follower, maxPower = 1.0, holdEnd = true) }
	val grabSecond by lazy { PedroPathCommand(paths.grabSecond, follower, maxPower = 1.0, holdEnd = true) }
	val scoreSecond by lazy { PedroPathCommand(paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true) }
	val slideOver by lazy { PedroPathCommand(paths.slideOver, follower, maxPower = 1.0, holdEnd = true, p = 0.5) }
	val inFrontOfWall by lazy { PedroPathCommand(paths.inFrontOfWall, follower, maxPower = 1.0, holdEnd = true) }
	val pushIntoWall by lazy { PedroPathCommand(paths.pushIntoWall, follower, maxPower = 1.0, holdEnd = true) }
	val scoreFromWall by lazy { PedroPathCommand(paths.scoreFromWall, follower, maxPower = 1.0, holdEnd = true) }

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// score preload
				ParallelCommandGroup(
					preload,
					SequentialCommandGroup(
						WaitCommand(250),
						PrepScoringSpecimen()
					)
				),

				ScoreSpecimen(),

				// push them
				firstPush,
				secondPush,
				thirdPush,

				// grab and score second
				grabSecond,
				PrepScoringSpecimen(),
				scoreSecond,
				ScoreSpecimen(letGo = false),
				slideOver,
				ScoreSpecimen(letGo = true),

				// grab and score third
				inFrontOfWall,
				pushIntoWall,
				PrepScoringSpecimen(),
				scoreFromWall,
				ScoreSpecimen(letGo = false),
				slideOver,
				ScoreSpecimen(letGo = true),

				// grab and score fourth
				inFrontOfWall,
				pushIntoWall,
				PrepScoringSpecimen(),
				scoreFromWall,
				ScoreSpecimen(letGo = false),
				slideOver,
				ScoreSpecimen(letGo = true),

				// grab and score fifth
				inFrontOfWall,
				pushIntoWall,
				PrepScoringSpecimen(),
				scoreFromWall,
				ScoreSpecimen(letGo = true),
			)
		)
	}
}