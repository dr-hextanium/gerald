package org.firstinspires.ftc.teamcode.opmode.template

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
import com.pedropathing.util.Constants
import org.firstinspires.ftc.teamcode.command.deposit.ExtendDeposit
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.ToggleIntake
import org.firstinspires.ftc.teamcode.command.intake.TuckIntake
import org.firstinspires.ftc.teamcode.command.sequences.sample.BringToTransfer
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.pedro.constants.FConstants
import org.firstinspires.ftc.teamcode.pedro.constants.LConstants
import org.firstinspires.ftc.teamcode.utility.functions.deg

open class AutoTemplate(val start: Pose) : BaseTemplate() {
	val follower
		get() = Robot.follower

	override fun init() {
		Globals.AUTO = true

		super.init()
	}

	override fun initialize() {
		GamepadButton(primary, GamepadKeys.Button.DPAD_UP)
			.whenPressed(ToggleIntake())

		GamepadButton(primary, GamepadKeys.Button.DPAD_DOWN)
			.whenPressed(ToggleDeposit())

		GamepadButton(primary, GamepadKeys.Button.DPAD_LEFT)
			.whileHeld(RetractHang())
			.whenReleased(StopHang())

		GamepadButton(primary, CIRCLE)
			.whenPressed(BringToTransfer())

		GamepadButton(primary, GamepadKeys.Button.DPAD_RIGHT)
			.whileHeld(ExtendHang())
			.whenReleased(StopHang())

		Robot.scheduler.schedule(
			TuckIntake(),
			ParallelCommandGroup(
				SwingDeposit(30.deg),
				PivotDeposit(355.deg)
			),
			OpenIntake(),
			OpenDeposit(),
			ExtendDeposit(),
		)

		Constants.setConstants(FConstants::class.java, LConstants::class.java)
		follower.setStartingPose(start)
	}

	override fun cycle() {
		follower.update()

		telemetry.addData("x", follower.pose.x)
		telemetry.addData("y", follower.pose.y)
		telemetry.addData("heading", follower.pose.heading)
	}
}