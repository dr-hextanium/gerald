package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

class FiveSpecimenPaths(val follower: Follower) {
	val preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 66.000, Point.CARTESIAN),
				Point(43.500, 66.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(4.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	val behindAndPushFirst: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.000, 66.000, Point.CARTESIAN),
				Point(28.597, 65.147, Point.CARTESIAN),
				Point(20.982, 26.059, Point.CARTESIAN),
				Point(43.149, 42.134, Point.CARTESIAN),
				Point(49.918, 39.934, Point.CARTESIAN),
				Point(95.605, 21.152, Point.CARTESIAN),
				Point(34.012, 20.475, Point.CARTESIAN),
				Point(13.368, 23.690, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	val pushSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(15.500, 24.000, Point.CARTESIAN),
				Point(77.000, 26.000, Point.CARTESIAN),
				Point(68.000, 9.000, Point.CARTESIAN),
				Point(15.500, 13.000, Point.CARTESIAN)
			)
		)
		.setPathEndTValueConstraint(0.990)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	val pushThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(15.500, 13.000, Point.CARTESIAN),
				Point(41.250, 14.250, Point.CARTESIAN),
				Point(101.358, 5.076, Point.CARTESIAN),
				Point(29.443, 7.784, Point.CARTESIAN),
				Point(14.891, 5.415, Point.CARTESIAN)
			)
		)
		.setPathEndTValueConstraint(0.990)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	val pullBack: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(14.891, 5.415, Point.CARTESIAN),
				Point(16.414, 12.860, Point.CARTESIAN),
				Point(6.599, 18.106, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
		.build()

	var scoreSecondSpecimen: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 7.000, Point.CARTESIAN),
				Point(17.000, 70.500, Point.CARTESIAN),
				Point(43.500, 71.500, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(4.0)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
		.build()

	var grabFromWall: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.000, 71.500, Point.CARTESIAN),
				Point(12.500, 70.000, Point.CARTESIAN),
				Point(34.500, 31.500, Point.CARTESIAN),
				Point(6.500, 35.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
		.build()

	var scoreFromWall: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 35.000, Point.CARTESIAN),
				Point(43.000, 68.500, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
		.addPath(
			BezierLine(
				Point(43.000, 68.500, Point.CARTESIAN),
				Point(43.000, 72.500, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(6.0)
		.setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
		.build()

	companion object {
		val startPose = Pose(6.5, 66.0, 0.deg)
	}
}
