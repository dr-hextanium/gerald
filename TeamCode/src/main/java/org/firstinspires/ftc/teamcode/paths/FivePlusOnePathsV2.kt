package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

object FivePlusOnePathsV2 {
	val startPose = Pose(6.5, 65.0, 0.deg)

	val grabX = 6.25
	val grabY = 33.0
	val scoreX = 43.0
	val scoreY = 67.0

	var preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 65.000, Point.CARTESIAN),
				Point(42.500, 69.000, Point.CARTESIAN)
			)
		)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 69.000, Point.CARTESIAN),
				Point(5.000, 2.000, Point.CARTESIAN),
				Point(85.500, 67.000, Point.CARTESIAN),
				Point(66.000, 10.000, Point.CARTESIAN),
				Point(21.000, 23.000, Point.CARTESIAN)
			)
		)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
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
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(21.000, 12.000, Point.CARTESIAN),
				Point(61.000, 21.000, Point.CARTESIAN),
				Point(61.000, 3.000, Point.CARTESIAN),
				Point(71.000, 6.000, Point.CARTESIAN),
				Point(10.000, 6.500, Point.CARTESIAN)
			)
		)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(10.000, 6.500, Point.CARTESIAN),
				Point(27.000, 67.000, Point.CARTESIAN),
				Point(scoreX, scoreY, Point.CARTESIAN)
			)
		)
		.setTangentHeadingInterpolation()
		.build()

	var grabSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(scoreX, scoreY, Point.CARTESIAN),
				Point(22.000, 65.000, Point.CARTESIAN),
				Point(36.000, 32.000, Point.CARTESIAN),
				Point(grabX, grabY, Point.CARTESIAN),
			)
		)
		.setTangentHeadingInterpolation()
		.setReversed(true)
		.build()

	var scoreSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabX, grabY, Point.CARTESIAN),
				Point(40.000, 32.000, Point.CARTESIAN),
				Point(12.000, 67.000, Point.CARTESIAN),
				Point(scoreX, scoreY, Point.CARTESIAN)
			)
		)
		.setTangentHeadingInterpolation()
		.build()

	var driveToPickup: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.000, 67.000, Point.CARTESIAN),
				Point(10.000, 67.000, Point.CARTESIAN),
				Point(10.000, 50.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(270.0))
		.setReversed(true)
		.build()

	var scoreSample: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(10.000, 50.000, Point.CARTESIAN),
				Point(10.000, 127.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(Math.toRadians(270.0), Math.toRadians(290.0))
		.build()
}