package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.sequences.PrepIntakeSample
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.functions.deg
import kotlin.math.abs
import kotlin.math.sign

class ManualSampleInput : ISubsystem {
	var x = 0.0
	var y = 0.0
	var angle = 0.0

	override fun reset() = Unit
	override fun read() = Unit
	override fun update() = Unit

	fun path(): PathChain {
		val gx = (AGAINST_THE_WALL_X + x) - Positions.Extension.MAX
		val gy = y.coerceIn(SUB_RIGHT_Y_BOUNDARY, SUB_LEFT_Y_BOUNDARY)
		val theta = angle.sign * 40.0 * abs(angle) / 90.0

		val robotPose = Robot.follower.pose

		return PathBuilder()
			.addPath(
				BezierLine(
					Point(robotPose.x, robotPose.y, Point.CARTESIAN),
					Point(gx, gy, Point.CARTESIAN)
				)
			)
			.addParametricCallback(0.6) { Robot.scheduler.schedule(PrepIntakeSample().andThen(TwistIntake(theta.deg))) }
			.setPathEndTimeoutConstraint(0.0)
			.setZeroPowerAccelerationMultiplier(4.0)
			.setConstantHeadingInterpolation(0.deg)
			.build()
	}

	override fun write() {
		Robot.telemetry.addData("Sample Position", "{ x: $x, y: $y, angle: $angle }")
	}

	companion object {
		const val AGAINST_THE_WALL_X = 43.25 // inches
		const val SUB_LEFT_Y_BOUNDARY = 86.5 // inches
		const val SUB_RIGHT_Y_BOUNDARY = 57.5 // inches
	}
}