package org.firstinspires.ftc.teamcode.hardware.subsystem.intake

import org.firstinspires.ftc.teamcode.hardware.subsystem.Arm
import org.firstinspires.ftc.teamcode.hardware.subsystem.Claw
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem

class Intake(val turret: Turret, val arm: Arm, val diffy: Diffy, val claw: Claw) : ISubsystem {
	val all = listOf(turret, arm, diffy, claw)

	override fun reset() = all.forEach { it.reset() }
	override fun read() = all.forEach { it.read() }
	override fun update() = all.forEach { it.update() }
	override fun write() = all.forEach { it.write() }

	fun turnTo(angle: Double) = turret.turn(angle)

	fun dropTo(angle: Double) = arm.turn(angle)

	fun twist(angle: Double) = diffy.yaw(angle)

	fun pitch(angle: Double) = diffy.pitch(angle)

	fun open() = claw.open()

	fun close() = claw.close()
}