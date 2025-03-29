package org.firstinspires.ftc.teamcode.opmode.driver

import com.arcrobotics.ftclib.command.ConditionalCommand
import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.ToggleIntake
import org.firstinspires.ftc.teamcode.command.intake.TwistIntakeRelatively
import org.firstinspires.ftc.teamcode.command.lift.NudgeLift
import org.firstinspires.ftc.teamcode.command.sequences.IntakeSample
import org.firstinspires.ftc.teamcode.command.sequences.PrimaryCrossBind
import org.firstinspires.ftc.teamcode.command.sequences.PrimarySquareBind
import org.firstinspires.ftc.teamcode.command.sequences.SecondaryCrossBind
import org.firstinspires.ftc.teamcode.command.sequences.sample.Transfer
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.deg
import org.firstinspires.ftc.teamcode.utility.functions.rad
import org.firstinspires.ftc.teamcode.utility.functions.signedSquare
import kotlin.math.abs
import kotlin.math.sign

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

		GamepadButton(primary, CIRCLE)
			.whenPressed(
				ConditionalCommand(
					InstantCommand({ mode = Mode.SAMPLE }),
					InstantCommand({ mode = Mode.SPECIMEN })
				) { mode == Mode.SPECIMEN }
			)
	}

	override fun start() {
		super.start()

		Robot.follower.startTeleopDrive()
	}

	override fun cycle() {
		val pose = Robot.follower.pose
		val extended = Robot.Subsystems.extension.extended

		val input = object {
			val x = -primary.leftX
			val y = primary.leftY
			val omega = -primary.rightX
		}

		var powers = Inputs(
			x = input.x,
			y = input.y * 1.1,
			omega = input.omega * 0.8
		)

		if (extended) {
			powers = Inputs(
				x = 0.3 * powers.x.signedSquare,
				y = 0.3 * powers.y.signedSquare,
				omega = powers.omega.signedSquare
			)
		}

		Robot.follower.setTeleOpMovementVectors(
			powers.y,
			powers.x * 1.1,
			powers.omega * 0.5,
			false
		)

		telemetry.addData("heading", pose.heading.rad)
		telemetry.addData("x", pose.x)
		telemetry.addData("y", pose.y)
		telemetry.addData("mode", mode)
		telemetry.addData("extended", extended)
	}

	enum class Mode {
		SAMPLE,
		SPECIMEN
	}

	data class Inputs(
		val x: Double,
		val y: Double,
		val omega: Double
	)
}