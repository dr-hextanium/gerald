package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

object FivePlusOnePathsV3 {
	val startPose = Pose(6.5, 65.0, 0.deg)
	val grabPose = Pose(6.5, 65.0, 0.deg)
	val scorePose = Pose(10.0, 67.0, 0.deg)

	var preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 65.000, Point.CARTESIAN),
				Point(42.500, 69.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 69.000, Point.CARTESIAN),
				Point(5.000, 2.000, Point.CARTESIAN),
				Point(85.500, 67.000, Point.CARTESIAN),
				Point(66.000, 10.000, Point.CARTESIAN),
				Point(19.000, 23.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(19.000, 23.000, Point.CARTESIAN),
				Point(44.000, 24.500, Point.CARTESIAN),
				Point(83.000, 23.000, Point.CARTESIAN),
				Point(51.500, 6.000, Point.CARTESIAN),
				Point(19.000, 12.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(7.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(19.000, 12.000, Point.CARTESIAN),
				Point(64.000, 17.000, Point.CARTESIAN),
				Point(60.000, 5.000, Point.CARTESIAN),
				Point(71.000, 6.000, Point.CARTESIAN),
				Point(7.000, 7.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.97)
		.setZeroPowerAccelerationMultiplier(7.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(7.000, 6.500, Point.CARTESIAN),
				Point(10.000, 30.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(90.0))
		.addPath(
			BezierCurve(
				Point(10.000, 30.000, Point.CARTESIAN),
				Point(10.000, 67.000, Point.CARTESIAN),
				Point(42.000, 66.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setTangentHeadingInterpolation()
		.build()
}