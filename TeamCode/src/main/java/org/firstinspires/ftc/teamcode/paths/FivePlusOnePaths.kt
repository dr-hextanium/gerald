package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import org.firstinspires.ftc.teamcode.utility.functions.deg
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.command.sequences.specimen.ScoreSpecimen
import org.firstinspires.ftc.teamcode.hardware.Robot

object FivePlusOnePaths {
	val startPose = Pose(6.5, 65.0, 0.deg)
	val grabX = 6.0

	var preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 69.000, Point.CARTESIAN),
				Point(42.500, 75.000, Point.CARTESIAN)
			)
		)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	var pushFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 75.000, Point.CARTESIAN),
				Point(5.000, 2.000, Point.CARTESIAN),
				Point(85.500, 67.000, Point.CARTESIAN),
				Point(66.000, 10.000, Point.CARTESIAN),
				Point(21.000, 23.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	var pushSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(21.000, 23.000, Point.CARTESIAN),
				Point(44.000, 24.500, Point.CARTESIAN),
				Point(85.000, 29.000, Point.CARTESIAN),
				Point(51.500, 6.000, Point.CARTESIAN),
				Point(21.000, 12.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	var pushThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(21.000, 12.000, Point.CARTESIAN),
				Point(61.000, 21.000, Point.CARTESIAN),
				Point(61.000, 3.000, Point.CARTESIAN),
				Point(71.000, 6.000, Point.CARTESIAN),
				Point(10.000, grabX, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	var scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(10.000, grabX, Point.CARTESIAN),
				Point(27.000, 67.000, Point.CARTESIAN),
				Point(42.500, 67.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(4.0)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.build()

	var grabThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 67.000, Point.CARTESIAN),
				Point(23.000, 65.000, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(grabX, 34.500, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.setReversed(true)
		.build()

	var scoreThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabX, 34.500, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(24.500, 67.000, Point.CARTESIAN),
				Point(42.500, 67.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(4.0)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.build()

	var grabFourth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 67.000, Point.CARTESIAN),
				Point(23.000, 67.000, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(grabX, 34.500, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.setReversed(true)
		.build()

	var scoreFourth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabX, 34.500, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(24.500, 67.000, Point.CARTESIAN),
				Point(42.500, 67.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(4.0)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.build()

	var grabFifth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 67.000, Point.CARTESIAN),
				Point(23.000, 67.000, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(grabX, 34.500, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setPathEndTValueConstraint(0.97)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.setReversed(true)
		.build()

	var scoreFifth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabX, 34.500, Point.CARTESIAN),
				Point(37.500, 34.500, Point.CARTESIAN),
				Point(24.500, 67.000, Point.CARTESIAN),
				Point(42.500, 67.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setPathEndTimeoutConstraint(0.0)
		.setTangentHeadingInterpolation()
		.build()

	var driveToPickup: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 67.000, Point.CARTESIAN),
				Point(10.000, 67.000, Point.CARTESIAN),
				Point(10.000, 50.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(0.deg, 270.deg)
		.setReversed(true)
		.build()

	var scoreSample: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(10.000, 50.000, Point.CARTESIAN),
				Point(10.000, 127.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setPathEndTimeoutConstraint(0.0)
		.setLinearHeadingInterpolation(270.deg, 290.deg)
		.build()
}
