package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.functions.deg

object FiveSpecimenV2Paths {
	val preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(7.000, 65.000, Point.CARTESIAN),
				Point(43.250, 74.000, Point.CARTESIAN)
			)
		)
		.addParametricCallback(0.95) { Robot.scheduler.schedule(ScoreSpecimen()) }
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val pushFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 74.000, Point.CARTESIAN),
				Point(28.500, 65.000, Point.CARTESIAN),
				Point(21.000, 26.000, Point.CARTESIAN),
				Point(43.000, 42.000, Point.CARTESIAN),
				Point(50.000, 40.000, Point.CARTESIAN),
				Point(95.500, 21.000, Point.CARTESIAN),
				Point(34.000, 20.500, Point.CARTESIAN),
				Point(17.000, 24.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val pushSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(17.000, 24.000, Point.CARTESIAN),
				Point(77.000, 26.000, Point.CARTESIAN),
				Point(68.000, 9.000, Point.CARTESIAN),
				Point(18.000, 13.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val pushThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(18.000, 13.000, Point.CARTESIAN),
				Point(41.250, 14.250, Point.CARTESIAN),
				Point(101.000, 5.000, Point.CARTESIAN),
				Point(29.500, 7.500, Point.CARTESIAN),
				Point(18.000, 7.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val grabSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(18.000, 7.000, Point.CARTESIAN),
				Point(17.500, 20.000, Point.CARTESIAN),
				Point(6.500, 22.500, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 22.500, Point.CARTESIAN),
				Point(24.000, 67.000, Point.CARTESIAN),
				Point(43.250, 72.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val goInFrontForThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(43.250, 72.000, Point.CARTESIAN),
				Point(12.000, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val grabThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(12.000, 36.000, Point.CARTESIAN),
				Point(6.500, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 36.000, Point.CARTESIAN),
				Point(43.250, 70.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val goInFrontForFourth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(43.250, 70.000, Point.CARTESIAN),
				Point(12.000, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val grabFourth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(12.000, 36.000, Point.CARTESIAN),
				Point(6.500, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreFourth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 36.000, Point.CARTESIAN),
				Point(43.250, 68.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val goInFrontForFifth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(43.250, 68.000, Point.CARTESIAN),
				Point(12.000, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val grabFifth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(12.000, 36.000, Point.CARTESIAN),
				Point(6.500, 36.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreFifth: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 36.000, Point.CARTESIAN),
				Point(43.250, 67.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val startPose = Pose(7.0, 65.0, 0.deg)
}