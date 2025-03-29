package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

class FiveSpecimenWithSlidingPaths {
	val preload: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 65.000, Point.CARTESIAN),
				Point(35.500, 63.000, Point.CARTESIAN),
				Point(43.250, 72.000, Point.CARTESIAN)
			)
		)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val firstPush: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 72.000, Point.CARTESIAN),
				Point(28.500, 65.000, Point.CARTESIAN),
				Point(21.000, 26.000, Point.CARTESIAN),
				Point(43.000, 42.000, Point.CARTESIAN),
				Point(50.000, 40.000, Point.CARTESIAN),
				Point(95.500, 21.000, Point.CARTESIAN),
				Point(34.000, 20.500, Point.CARTESIAN),
				Point(13.500, 24.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val secondPush: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(13.500, 24.000, Point.CARTESIAN),
				Point(77.000, 26.000, Point.CARTESIAN),
				Point(68.000, 9.000, Point.CARTESIAN),
				Point(15.500, 13.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val thirdPush: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(15.500, 13.000, Point.CARTESIAN),
				Point(41.250, 14.250, Point.CARTESIAN),
				Point(101.000, 5.000, Point.CARTESIAN),
				Point(29.500, 7.500, Point.CARTESIAN),
				Point(15.500, 7.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(5.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val grabSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(15.500, 7.000, Point.CARTESIAN),
				Point(17.500, 20.000, Point.CARTESIAN),
				Point(6.500, 22.500, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 22.500, Point.CARTESIAN),
				Point(24.000, 64.500, Point.CARTESIAN),
				Point(43.250, 69.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val slideOver: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(43.250, 69.000, Point.CARTESIAN),
				Point(43.250, 73.000, Point.CARTESIAN)
			)
		)
		.setPathEndTValueConstraint(0.5)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(8.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val inFrontOfWall: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(43.250, 72.000, Point.CARTESIAN),
				Point(12.000, 35.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val pushIntoWall: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(12.000, 35.000, Point.CARTESIAN),
				Point(6.500, 35.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	val scoreFromWall: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 35.000, Point.CARTESIAN),
				Point(43.250, 69.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setPathEndTimeoutConstraint(0.0)
		.setConstantHeadingInterpolation(0.deg)
		.build()

	companion object {
		val startPose = Pose(6.5, 65.0, 0.deg)
	}
}
