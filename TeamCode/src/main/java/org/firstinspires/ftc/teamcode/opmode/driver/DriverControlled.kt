package org.firstinspires.ftc.teamcode.opmode.driver

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.ToggleIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntakeRelatively
import org.firstinspires.ftc.teamcode.command.lift.NudgeLift
import org.firstinspires.ftc.teamcode.command.sequences.PrimaryCrossBind
import org.firstinspires.ftc.teamcode.command.sequences.IntakeSample
import org.firstinspires.ftc.teamcode.command.sequences.PrimarySquareBind
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.curve
import org.firstinspires.ftc.teamcode.utility.functions.deg

@TeleOp
class DriverControlled : BaseTemplate() {
	override fun initialize() {
		Globals.AUTO = false

		GamepadButton(primary, SQUARE)
			.whenPressed(PrimarySquareBind())

		GamepadButton(primary, TRIANGLE)
			.whenPressed(IntakeSample())

		GamepadButton(primary, CROSS)
			.whenPressed(PrimaryCrossBind())

		GamepadButton(primary, GamepadKeys.Button.LEFT_STICK_BUTTON)
			.whenPressed(InstantCommand({ Subsystems.odometry.resetPose() }))

		GamepadButton(primary, GamepadKeys.Button.DPAD_UP)
			.whenPressed(ToggleIntake())

		GamepadButton(primary, GamepadKeys.Button.DPAD_DOWN)
			.whenPressed(ToggleDeposit())

		GamepadButton(primary, GamepadKeys.Button.DPAD_LEFT)
			.whileHeld(RetractHang())
			.whenReleased(StopHang())

		GamepadButton(primary, GamepadKeys.Button.DPAD_RIGHT)
			.whileHeld(ExtendHang())
			.whenReleased(StopHang())

		GamepadButton(primary, GamepadKeys.Button.LEFT_BUMPER)
			.whenPressed(TwistIntakeRelatively((-30.0).deg))

		GamepadButton(primary, GamepadKeys.Button.RIGHT_BUMPER)
			.whenPressed(TwistIntakeRelatively(30.0.deg))

		GamepadButton(primary, GamepadKeys.Button.BACK)
			.whenPressed(NudgeLift(-5.0))

		GamepadButton(primary, GamepadKeys.Button.START)
			.whenPressed(NudgeLift(5.0))
	}

	override fun cycle() {
		val pose = Subsystems.odometry.pose
		val heading = pose.h
		val x = pose.x
		val y = pose.y

		Subsystems.drive.driveFieldCentric(
			-curve(Robot.gamepad1.leftX),
			-curve(Robot.gamepad1.leftY),
			-curve(Robot.gamepad1.rightX),
			heading
		)

		telemetry.addData("heading", heading)
		telemetry.addData("x", x)
		telemetry.addData("y", y)
	}
}