package org.firstinspires.ftc.teamcode.command.extension

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.functions.deg
import kotlin.math.abs
import kotlin.math.sign

class ExtensionWithVision : CommandTemplate() {
	override fun initialize() {
		val values = Robot.Subsystems.vision.findDistAngle()
		val distance = values[0]
		val angle = values[1]

		val mapped = angle.sign * 40.0 * abs(angle) / 90.0

		Robot.scheduler.schedule(
			ExtensionToUntil(distance - 1.0, 0.5, time = 500),
			TurnTurret(Intake.Turret.CENTER),

			SwingIntake(Intake.Arm.INTERMEDIATE_ANGLE),
			PitchIntake(Intake.Claw.INTERMEDIATE_PITCH),

			TwistIntake(mapped.deg),
			OpenIntake(),
			PitchIntake(Intake.Claw.INTAKE_PITCH),
			CloseIntake()
		)
	}

	override fun execute() {}

	override fun isFinished() = true
}