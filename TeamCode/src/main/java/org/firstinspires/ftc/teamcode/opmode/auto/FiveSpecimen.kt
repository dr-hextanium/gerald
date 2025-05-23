package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FiveSpecimenPaths

@Autonomous(name = "5 Specimen")
class FiveSpecimen : AutoTemplate(FiveSpecimenPaths.startPose) {
	val paths = FiveSpecimenPaths

	val preload by lazy {
		PedroPathCommand(
			paths.preload, follower, maxPower = 0.8, holdEnd = true
		)
	}
	val pushFirst by lazy {
		PedroPathCommand(
			paths.pushFirst, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val pushSecond by lazy {
		PedroPathCommand(
			paths.pushSecond, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val pushThird by lazy {
		PedroPathCommand(
			paths.pushThird, follower, maxPower = 1.0, holdEnd = true
		)
	}

	val grabSecond by lazy {
		PedroPathCommand(
			paths.grabSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreSecond by lazy {
		PedroPathCommand(
			paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val goInFrontForThird by lazy {
		PedroPathCommand(
			paths.goInFrontForThird, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val grabThird by lazy {
		PedroPathCommand(
			paths.grabThird, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreThird by lazy {
		PedroPathCommand(
			paths.scoreThird, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val goInFrontForFourth by lazy {
		PedroPathCommand(
			paths.goInFrontForFourth, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val grabFourth by lazy {
		PedroPathCommand(
			paths.grabFourth, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreFourth by lazy {
		PedroPathCommand(
			paths.scoreFourth, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val goInFrontForFifth by lazy {
		PedroPathCommand(
			paths.goInFrontForFifth, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val grabFifth by lazy {
		PedroPathCommand(
			paths.grabFifth, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreFifth by lazy {
		PedroPathCommand(
			paths.scoreFifth, follower, maxPower = 1.0, holdEnd = true
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// score preload
				ParallelCommandGroup(
					SequentialCommandGroup(
						WaitCommand(500),
						preload,
					),
					PrepScoringSpecimen()
				),

				// push the specimens
				pushFirst,
				pushSecond,
				pushThird,

				// grab second
				grabSecond,
				WaitCommand(100),
				CloseDeposit(),
				ParallelCommandGroup(
					scoreSecond,
					PrepScoringSpecimen(),
				),
				ScoreSpecimen(),

				goInFrontForThird,
				grabThird,
				WaitCommand(100),
				CloseDeposit(),
				ParallelCommandGroup(
					scoreThird,
					PrepScoringSpecimen(),
				),
				ScoreSpecimen(),

				goInFrontForFourth,
				grabFourth,
				WaitCommand(100),
				CloseDeposit(),
				ParallelCommandGroup(
					scoreFourth,
					PrepScoringSpecimen(),
				),
				ScoreSpecimen(),

				goInFrontForFifth,
				grabFifth,
				WaitCommand(100),
				CloseDeposit(),
				ParallelCommandGroup(
					scoreFifth,
					PrepScoringSpecimen(),
				),
				ScoreSpecimen(),
			).alongWith(
				ExtensionToUntil(-0.5, 0.0, 500),
			)
		)
	}
}