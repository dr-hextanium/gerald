package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.PedroPathCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.AutoTemplate
import org.firstinspires.ftc.teamcode.paths.FivePlusOnePaths
import org.firstinspires.ftc.teamcode.paths.FivePlusOnePathsV3
import org.firstinspires.ftc.teamcode.utility.functions.deg

@Autonomous(name = "5 Specimen 1 Sample V3")
class FivePlusOneV3 : AutoTemplate(FivePlusOnePathsV3.startPose) {
	val paths = FivePlusOnePathsV3

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

	val scoreSecond by lazy {
		PedroPathCommand(
			paths.scoreSecond, follower, maxPower = 1.0, holdEnd = true
		)
	}

//	val grabThird by lazy {
//		PedroPathCommand(
//			paths.grabThird, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//	val scoreThird by lazy {
//		PedroPathCommand(
//			paths.scoreThird, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//
//	val grabFourth by lazy {
//		PedroPathCommand(
//			paths.grabFourth, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//	val scoreFourth by lazy {
//		PedroPathCommand(
//			paths.scoreFourth, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//
//	val grabFifth by lazy {
//		PedroPathCommand(
//			paths.grabFifth, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//	val scoreFifth by lazy {
//		PedroPathCommand(
//			paths.scoreFifth, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//	val driveToPickup by lazy {
//		PedroPathCommand(
//			paths.driveToPickup, follower, maxPower = 1.0, holdEnd = true
//		)
//	}
//	val scoreSample by lazy {
//		PedroPathCommand(
//			paths.scoreSample, follower, maxPower = 1.0, holdEnd = true
//		)
//	}

	override fun start() {
		super.start()

		Robot.scheduler.schedule(
			SequentialCommandGroup(
				// score preload
				ParallelCommandGroup(
					SequentialCommandGroup(
						WaitCommand(200),
						preload,
						ScoreSpecimen(),
					),
					PrepScoringSpecimen()
				),

				// push the specimens
				pushFirst,
				pushSecond,

				ParallelCommandGroup(
					SequentialCommandGroup(
						LiftToUntil(-2.0, 0.1, 500),
						SwingDeposit(Positions.Deposit.Arm.EXTENDED_GRAB),
						PivotDeposit(Positions.Deposit.Pivot.EXTENDED_GRAB),
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
			).alongWith(
				ExtensionToUntil(-0.5, 0.0, 500),
			)
		)
	}
}