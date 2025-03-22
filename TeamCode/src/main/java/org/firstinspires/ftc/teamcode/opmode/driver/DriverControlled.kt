package org.firstinspires.ftc.teamcode.opmode.driver

import com.arcrobotics.ftclib.command.ConditionalCommand
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
import org.firstinspires.ftc.teamcode.command.sequences.SecondaryCrossBind
import org.firstinspires.ftc.teamcode.command.sequences.sample.Transfer
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.curve
import org.firstinspires.ftc.teamcode.utility.functions.deg
import java.util.concurrent.locks.Condition

@TeleOp
class DriverControlled : BaseTemplate() {
	private var mode = Mode.SPECIMEN

	override fun initialize() {
		Globals.AUTO = false

		GamepadButton(primary, SQUARE)
			.whenPressed(
				ConditionalCommand(
					PrimarySquareBind(),
					Transfer()
				) { mode == Mode.SPECIMEN }
			)

		GamepadButton(primary, TRIANGLE)
			.whenPressed(IntakeSample())

		GamepadButton(primary, CROSS)
			.whenPressed(
				ConditionalCommand(
					PrimaryCrossBind(),
					SecondaryCrossBind()
				) { mode == Mode.SPECIMEN }
			)

		GamepadButton(primary, GamepadKeys.Button.LEFT_STICK_BUTTON)
			.whenPressed(InstantCommand({ Robot.follower.resetOffset() }))

		GamepadButton(primary, GamepadKeys.Button.DPAD_UP)
			.whenPressed(ToggleIntake())

		GamepadButton(primary, GamepadKeys.Button.DPAD_DOWN)
			.whenPressed(ToggleDeposit())

		GamepadButton(secondary, GamepadKeys.Button.DPAD_LEFT)
			.whileHeld(RetractHang())
			.whenReleased(StopHang())

		GamepadButton(secondary, GamepadKeys.Button.DPAD_RIGHT)
			.whileHeld(ExtendHang())
			.whenReleased(StopHang())

		GamepadButton(primary, GamepadKeys.Button.LEFT_BUMPER)
			.whenPressed(TwistIntakeRelatively((-20.0).deg))

		GamepadButton(primary, GamepadKeys.Button.RIGHT_BUMPER)
			.whenPressed(TwistIntakeRelatively(20.0.deg))

		GamepadButton(primary, GamepadKeys.Button.BACK)
			.whenPressed(NudgeLift(-5.0))

		GamepadButton(primary, GamepadKeys.Button.START)
			.whenPressed(NudgeLift(5.0))

//		GamepadButton(primary, CIRCLE)
//			.whenPressed(
//				ConditionalCommand(
//					InstantCommand({ mode = Mode.SAMPLE }),
//					InstantCommand({ mode = Mode.SPECIMEN })
//				) { mode == Mode.SPECIMEN }
//			)
	}

	override fun cycle() {
		val pose = Robot.follower.pose
		val heading = pose.heading
		val x = pose.x
		val y = pose.y

		Subsystems.drive.driveFieldCentric(
			-curve(Robot.gamepad1.leftX),
			-curve(Robot.gamepad1.leftY),
			-curve(Robot.gamepad1.rightX) * 0.5,
			heading
		)

		telemetry.addData("heading", heading)
		telemetry.addData("x", x)
		telemetry.addData("y", y)
		telemetry.addData("mode", mode)
	}

	enum class Mode {
		SAMPLE,
		SPECIMEN
	}
}