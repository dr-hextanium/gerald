package org.firstinspires.ftc.teamcode.opmode.driver

import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.command.deposit.ToggleDeposit
import org.firstinspires.ftc.teamcode.command.hang.ExtendHang
import org.firstinspires.ftc.teamcode.command.hang.RetractHang
import org.firstinspires.ftc.teamcode.command.hang.StopHang
import org.firstinspires.ftc.teamcode.command.intake.ToggleIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.intake.TwistIntakeRelatively
import org.firstinspires.ftc.teamcode.command.sequences.CrossBind
import org.firstinspires.ftc.teamcode.command.sequences.IntakeSample
import org.firstinspires.ftc.teamcode.command.sequences.SquareBind
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@TeleOp
class DriverControlled : BaseTemplate() {
	override fun initialize() {
		val primary = Robot.gamepad1
		val secondary = Robot.gamepad2

		GamepadButton(primary, SQUARE)
			.whenPressed(SquareBind())

		GamepadButton(primary, TRIANGLE)
			.whenPressed(IntakeSample())

		GamepadButton(primary, CROSS)
			.whenPressed(CrossBind())

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

		GamepadButton(primary, CIRCLE).whenPressed(TurnTurret(79.deg))
	}

	override fun init_loop() {
		Subsystems.intake.claw.manual(4.5)
		Subsystems.intake.twist((-45).deg)
		Subsystems.intake.pitch(260.deg)
		Subsystems.intake.dropTo(60.deg)
		Subsystems.intake.turnTo(215.deg)
		Subsystems.deposit.raiseTo(0.deg)
		Subsystems.deposit.pitchTo(120.deg)
		Subsystems.deposit.claw.manual(1.5)
	}

	override fun cycle() {
		telemetry.addData("diffy state", Subsystems.intake.diffy.state)
		telemetry.addData("extension target", Subsystems.extension.target)
	}

	companion object {
		val CROSS = GamepadKeys.Button.A
		val CIRCLE = GamepadKeys.Button.B
		val TRIANGLE = GamepadKeys.Button.Y
		val SQUARE = GamepadKeys.Button.X
	}
}