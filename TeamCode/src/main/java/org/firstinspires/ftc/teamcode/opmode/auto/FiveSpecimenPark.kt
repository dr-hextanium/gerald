package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ExtendDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.command.sequences.PrepIntakeSample
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Positions.Lift
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FivePlusOnePathsV3
import org.firstinspires.ftc.teamcode.paths.FivePlusOnePathsV4
import org.firstinspires.ftc.teamcode.utility.functions.deg

@Autonomous(name = "5 Specimen, Park")
class FiveSpecimenPark : AutoTemplate(FivePlusOnePathsV3.startPose) {
	val paths = FivePlusOnePathsV4

	val preload by lazy {
		PedroPathCommand(
			paths.preload, follower, maxPower = 0.8, holdEnd = true
		)
	}
	val goBeforeFirst by lazy {
		PedroPathCommand(
			paths.goBeforeFirst, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val pushFirst by lazy {
		PedroPathCommand(
			paths.pushFirst, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val goBeforeSecond by lazy {
		PedroPathCommand(
			paths.goBeforeSecond, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val pushSecond by lazy {
		PedroPathCommand(
			paths.pushSecond, follower, maxPower = 1.0, holdEnd = false
		)
	}
	val goBeforeThird by lazy {
		PedroPathCommand(
			paths.goBeforeThird, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val pushThird by lazy {
		PedroPathCommand(
			paths.pushThird, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreSecond by lazy {
		PedroPathCommand(
			paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val inFrontOfSpecimen by lazy {
		PedroPathCommand(
			paths.inFrontOfSpecimen, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val grabSpecimen by lazy {
		PedroPathCommand(
			paths.grabSpecimen, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreSpecimen by lazy {
		PedroPathCommand(
			paths.scoreSpecimen, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val grabSample by lazy {
		PedroPathCommand(
			paths.grabSample, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val scoreSample by lazy {
		PedroPathCommand(
			paths.scoreSample, follower, maxPower = 1.0, holdEnd = true
		)
	}
	val ScoreCycle by lazy {
		SequentialCommandGroup(
			ParallelCommandGroup(
				inFrontOfSpecimen,
				SwingDeposit(Deposit.Arm.GRAB_SPECIMEN + 10.deg),
				PivotDeposit(Deposit.Pivot.GRAB_SPECIMEN)
			),

			grabSpecimen,
			CloseDeposit(),

			ParallelCommandGroup(
				scoreSpecimen,
//				follower.isRobotStuck
				PrepScoringSpecimen(),
			),

			ScoreSpecimen(),
		)
	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// score preload
				ParallelCommandGroup(
					SequentialCommandGroup(
						WaitCommand(250),
						preload,
						ScoreSpecimen(),
					),
					SequentialCommandGroup(
						OpenIntake(),
						CloseDeposit(),

						WaitCommand(50),

						ParallelCommandGroup(
							LiftToUntil(Lift.HIGH_CHAMBER, time = 750),
							SequentialCommandGroup(
								ParallelCommandGroup(
									SwingDeposit(Deposit.Arm.SCORE_SPECIMEN),
									PivotDeposit(Deposit.Pivot.SCORE_SPECIMEN)
								),
							)
						),

						WaitCommand(100),

						ExtendDeposit(),
					)
				),

				// push specimens
				goBeforeFirst,
				pushFirst,

				goBeforeSecond,
				pushSecond,

				goBeforeThird,

				ParallelCommandGroup(
					SequentialCommandGroup(
						LiftToUntil(-2.0, 0.1, 500),
						SwingDeposit(Deposit.Arm.EXTENDED_GRAB),
						PivotDeposit(Deposit.Pivot.EXTENDED_GRAB),
					),
					pushThird
				),
				WaitCommand(100),
				CloseDeposit(),

				ParallelCommandGroup(
					scoreSecond,
					PrepScoringSpecimen(),
				),
				ScoreSpecimen(),

				ScoreCycle, // third specimen
				ScoreCycle, // fourth specimen
				ScoreCycle, // fifth specimen

				PedroPathCommand(
					paths.inFrontOfSpecimen, follower, maxPower = 1.0, holdEnd = true
				)
			).alongWith(
				ExtensionToUntil(-0.5, 0.0, 500),
			)
		)
	}
}