package org.firstinspires.ftc.teamcode.opmode.auto.new

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
	val paths by lazy { FiveSpecimenPaths(follower) }

	val first by lazy {
		SequentialCommandGroup(
			ParallelCommandGroup(
				PedroPathCommand(paths.preload, follower),

				SequentialCommandGroup(
					WaitCommand(250),
					PrepScoringSpecimen(),
				)
			),

			ScoreSpecimen(),
		)
	}

	val moveBehindFirst by lazy {
		SequentialCommandGroup(
			PedroPathCommand(paths.behindFirst, follower)
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				first,
				moveBehindFirst
			)
		)
	}
}