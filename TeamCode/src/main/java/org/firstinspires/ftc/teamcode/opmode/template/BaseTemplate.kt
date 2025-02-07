package org.firstinspires.ftc.teamcode.opmode.template

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.hardware.Robot

abstract class BaseTemplate : OpMode() {
	val primary by lazy { Robot.gamepad1 }
	val secondary by lazy { Robot.gamepad2 }

	val timer = ElapsedTime()

	override fun init() {
		Robot.init(hardwareMap, telemetry, gamepad1, gamepad2)

		initialize()
	}

	override fun loop() {
		Robot.hubs.forEach { it.clearBulkCache() }

		Robot.read()
		Robot.update()

		cycle()

		Robot.scheduler.run()

		Robot.write()
	}

	abstract fun initialize()

	abstract fun cycle()
}