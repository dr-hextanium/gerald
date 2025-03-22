package org.firstinspires.ftc.teamcode.opmode.template

import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
import com.pedropathing.util.Constants
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.TuckIntake
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.pedro.constants.FConstants
import org.firstinspires.ftc.teamcode.pedro.constants.LConstants
import org.firstinspires.ftc.teamcode.utility.functions.deg

open class AutoTemplate(val start: Pose) : BaseTemplate() {
	val follower by lazy { Follower(hardwareMap) }

	override fun initialize() {
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

		Globals.AUTO = true

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