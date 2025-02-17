package org.firstinspires.ftc.teamcode.opmode.template

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.OpMode
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

		Subsystems.intake.claw.manual(4.5.deg)
		Subsystems.intake.twist((-45).deg)
		Subsystems.intake.pitch(260.deg)
		Subsystems.intake.dropTo(60.deg)
		Subsystems.intake.turnTo(215.deg)
		Subsystems.deposit.raiseTo(0.deg)
		Subsystems.deposit.pitchTo(120.deg)
		Subsystems.deposit.claw.manual(1.5.deg)

		initialize()

		Robot.write()
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