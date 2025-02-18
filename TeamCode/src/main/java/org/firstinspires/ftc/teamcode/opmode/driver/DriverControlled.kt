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
import org.firstinspires.ftc.teamcode.command.intake.TwistIntakeRelatively
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
		val heading = Subsystems.odometry.getDegrees()

		Subsystems.drive.driveFieldCentric(
			-curve(Robot.gamepad1.leftX),
			-curve(Robot.gamepad1.leftY),
			-curve(Robot.gamepad1.rightX),
			heading
		)
	}
}