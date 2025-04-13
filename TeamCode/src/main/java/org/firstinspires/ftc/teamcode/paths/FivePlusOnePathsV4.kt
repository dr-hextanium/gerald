package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point

object FivePlusOnePathsV4 {
	var preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 65.000, Point.CARTESIAN),
				Point(42.500, 69.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var goBeforeFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(42.500, 69.000, Point.CARTESIAN),
				Point(9.000, 7.000, Point.CARTESIAN),
				Point(85.000, 60.000, Point.CARTESIAN),
				Point(50.000, 19.000, Point.CARTESIAN),
				Point(55.000, 23.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.6)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushFirst: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(55.000, 23.000, Point.CARTESIAN),
				Point(19.000, 23.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.95)
		.setZeroPowerAccelerationMultiplier(7.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var goBeforeSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(19.000, 23.000, Point.CARTESIAN),
				Point(66.000, 24.000, Point.CARTESIAN),
				Point(55.000, 13.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.6)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushSecond: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(55.000, 13.000, Point.CARTESIAN),
				Point(19.000, 13.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.95)
		.setZeroPowerAccelerationMultiplier(7.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var goBeforeThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(19.000, 13.000, Point.CARTESIAN),
				Point(66.000, 11.000, Point.CARTESIAN),
				Point(55.000, 7.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.6)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var pushThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(55.000, 7.000, Point.CARTESIAN),
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
				Point(7.000, 7.000, Point.CARTESIAN),
				Point(13.500, 33.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(67.0))
		.addPath(
			BezierCurve(
				Point(13.500, 33.000, Point.CARTESIAN),
				Point(15.000, 66.000, Point.CARTESIAN),
				Point(42.500, 66.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setTangentHeadingInterpolation()
		.build()

	var inFrontOfSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(42.500, 66.000, Point.CARTESIAN),
				Point(13.000, 35.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.6)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var grabSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(13.000, 35.000, Point.CARTESIAN),
				Point(7.000, 35.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.9)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var scoreSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.900, 35.000, Point.CARTESIAN),
				Point(47.000, 69.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setPathEndTValueConstraint(0.8)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(Math.toRadians(0.0))
		.build()

	var grabSample: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(47.000, 69.000, Point.CARTESIAN),
				Point(12.000, 56.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(250.0)
		.setZeroPowerAccelerationMultiplier(3.0)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(270.0))
		.build()

	var scoreSample: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(12.000, 56.000, Point.CARTESIAN),
				Point(8.000, 126.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setTangentHeadingInterpolation()
		.setReversed(true)
		.build()
}
