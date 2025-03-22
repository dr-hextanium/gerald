package org.firstinspires.ftc.teamcode.opmode.auto.old

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.command.auto.GoToPointTimed
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.TuckIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.deg

@Disabled
@Autonomous(name = "3 Spec")
class `3Spec` : BaseTemplate() {
	override fun initialize() {
		Globals.AUTO = true

		GamepadButton(primary, GamepadKeys.Button.DPAD_DOWN)
			.whenPressed(ToggleDeposit())

		GamepadButton(primary, GamepadKeys.Button.DPAD_LEFT)
			.whileHeld(RetractHang())
			.whenReleased(StopHang())

		GamepadButton(primary, GamepadKeys.Button.DPAD_RIGHT)
			.whileHeld(ExtendHang())
			.whenReleased(StopHang())

		Robot.scheduler.schedule(
			TuckIntake(),
			ParallelCommandGroup(
				SwingDeposit(30.deg),
				PivotDeposit(100.deg)
			),
			OpenIntake(),
			OpenDeposit(),
		)
	}

	override fun start() {
		Robot.scheduler.schedule(
			LiftToUntil(0.0, time = 250),

			SequentialCommandGroup(
				ParallelCommandGroup(
					// score the first one
					GoToPointTimed(Pose2D(36.0, 4.0, 0.0), 2000, 0.7),
					SequentialCommandGroup(
						WaitCommand(250),
						PrepScoringSpecimen(),
						WaitCommand(850),
						ScoreSpecimen(),
					)
				),

				// go to corner of 4 tiles
				GoToPointTimed(Pose2D(19.0, -30.0, 0.0), 1000, 0.8),

				// go back
				GoToPointTimed(Pose2D(50.0, -30.0, 0.0), 1500, 0.8),
				// strafe to first
				GoToPointTimed(Pose2D(50.0, -32.0, 0.0), 500, 2.0),

				// push in
				GoToPointTimed(Pose2D(16.0, -32.0, 0.0), 850, 1.2),
				// come back
				GoToPointTimed(Pose2D(20.0, -32.0, 0.0), 1300, 1.0),
				// push up
				GoToPointTimed(Pose2D(2.0, -34.0, 0.0), 1000, 0.75),

				WaitCommand(500),

				// grab
				PrepScoringSpecimen(),
//				// inter 1
				GoToPointTimed(Pose2D(7.0, 0.0, 0.0), 1000, 1.2),

				// go and score second
				ParallelCommandGroup(
					GoToPointTimed(Pose2D(43.0, 24.0, 0.0), 2000, 0.9),
					SequentialCommandGroup(
						WaitCommand(1600),
						ScoreSpecimen(),
					)
				),

				// go to corner of 4 tiles
				GoToPointTimed(Pose2D(16.0, -34.0, 0.0), 1500, 1.1),
				WaitCommand(300),

				// push into third
				GoToPointTimed(Pose2D(3.0, -34.0, 0.0), 1000, 0.8),
				WaitCommand(250),

				// grab third
				PrepScoringSpecimen(),

				// consider if the three waits are needed, 750 ms
				WaitCommand(250),

				// go and score third
				// try to comment these two bottom ones out, might save 1.5 seconds
				GoToPointTimed(Pose2D(12.0, 26.0, 0.0), 600, 1.2),

				ParallelCommandGroup(
					GoToPointTimed(Pose2D(42.0, 26.0, 0.0), 2000, 0.9),
					SequentialCommandGroup(
						WaitCommand(1300),
						ScoreSpecimen(),
					)
				),
			)
		)
	}

	override fun cycle() {
		val pose = Robot.follower.pose
		val heading = pose.heading
		val x = pose.x
		val y = pose.y

		telemetry.addData("heading", heading)
		telemetry.addData("x", x)
		telemetry.addData("y", y)
	}
}