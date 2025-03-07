package org.firstinspires.ftc.teamcode.opmode.auto

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.hardware.sparkfun.SparkFunOTOS
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.command.auto.GoToPoint
import org.firstinspires.ftc.teamcode.command.auto.GoToPointTimed
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepGrabbingSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.PrepScoringSpecimen
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.deg

@Autonomous
class Specimen : BaseTemplate() {
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
			PrepGrabbingSpecimen(true),
			ParallelCommandGroup(
				SwingDeposit(30.deg),
				PivotDeposit(100.deg)
			),
			OpenIntake(),
			OpenDeposit(),
		)
	}

	override fun start() {
		Subsystems.odometry.resetPose()

		Robot.scheduler.schedule(
			LiftToUntil(0.0, time = 250),

			SequentialCommandGroup(
				ParallelCommandGroup(
					// score the first one
					GoToPointTimed(Pose2D(-8.0, -30.0, 0.0), 1500),
					PrepScoringSpecimen()
				),

				WaitCommand(250),
				ScoreSpecimen(),

				// go back
				GoToPointTimed(Pose2D(0.0, -15.0, 0.0), 2000),

				// go right
				GoToPointTimed(Pose2D(21.0, -15.0, 0.0), 2000),
				// go up
				GoToPointTimed(Pose2D(21.0, -35.0, 0.0), 2000),

				// push in
				GoToPointTimed(Pose2D(28.0, -35.0, 0.0), 2000),
				GoToPointTimed(Pose2D(28.0, -5.0, 0.0), 2000),
//
//				// go back
				GoToPointTimed(Pose2D(28.0, -35.0, 0.0), 2000),
//
				// slide over
				GoToPointTimed(Pose2D(33.5, -35.0, 0.0), 2000),
				// slide in
				GoToPointTimed(Pose2D(33.5, -2.0, 0.0), 2000),

				WaitCommand(250),
//
				// grab
				GoToPointTimed(Pose2D(28.0, 4.0, 0.0), 2000),
				PrepScoringSpecimen(),
				WaitCommand(500),
//
				GoToPointTimed(Pose2D(7.0, -5.0, 0.0), 2000),
				GoToPointTimed(Pose2D(0.0, -33.0, 0.0), 1000),
				WaitCommand(500),
				ScoreSpecimen(),
			)
		)
	}

	override fun cycle() {
		val pose = Subsystems.odometry.pose
		val heading = pose.h
		val x = pose.x
		val y = pose.y

		telemetry.addData("heading", heading)
		telemetry.addData("x", x)
		telemetry.addData("y", y)
	}
}