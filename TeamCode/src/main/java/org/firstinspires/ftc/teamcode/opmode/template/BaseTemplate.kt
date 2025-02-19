package org.firstinspires.ftc.teamcode.opmode.template

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.utility.functions.deg

abstract class BaseTemplate : OpMode() {
	val primary by lazy { Robot.gamepad1 }
	val secondary by lazy { Robot.gamepad2 }

	var last = 0.0

	private fun logLoopTime() {
		val now = System.nanoTime().toDouble()
		telemetry.addData("loop time (hz)", 1e9 / (now - last))
		last = now
	}

	override fun init() {
		telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)
		Robot.init(hardwareMap, telemetry, gamepad1, gamepad2)

		initialize()

		Subsystems.odometry.reset()

		telemetry.addLine("initializing")
	}

	override fun start() {
		Robot.scheduler.schedule(
			OpenIntake(),
			OpenDeposit(),

			ParallelCommandGroup(
				SequentialCommandGroup(
					SwingDeposit(355.deg),
					WaitCommand(350),
				),

				SequentialCommandGroup(
					SwingIntake(70.deg),
					WaitCommand(250),
					PitchIntake(180.deg),
					WaitCommand(250),
					TurnTurret(80.deg),
					WaitCommand(500),
				),
			),

			WaitCommand(350),

			SequentialCommandGroup(
				TurnTurret(215.deg),
				WaitCommand(250),
				PitchIntake(260.deg),
				WaitCommand(250),
				SwingIntake(70.deg),
			)
		)
	}

	override fun loop() {
		Robot.hubs.forEach { it.clearBulkCache() }

		Robot.read()
		Robot.update()

		cycle()

		Robot.scheduler.run()

		Robot.write()

		logLoopTime()

		telemetry.update()
	}

	abstract fun initialize()

	abstract fun cycle()

	companion object {
		val CROSS = GamepadKeys.Button.A
		val CIRCLE = GamepadKeys.Button.B
		val TRIANGLE = GamepadKeys.Button.Y
		val SQUARE = GamepadKeys.Button.X
	}
}