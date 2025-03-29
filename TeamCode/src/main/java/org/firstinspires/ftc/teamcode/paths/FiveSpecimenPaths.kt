package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

class FiveSpecimenPaths {
	var preload: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 65.000, Point.CARTESIAN),
				Point(35.500, 63.000, Point.CARTESIAN),
				Point(scoreX, 77.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var firstPush: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 77.000, Point.CARTESIAN),
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
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var secondPush: PathChain = PathBuilder()
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
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var thirdPush: PathChain = PathBuilder()
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
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var grabSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(15.500, 7.000, Point.CARTESIAN),
				Point(17.500, 20.000, Point.CARTESIAN),
				Point(6.500, 22.500, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(6.500, 22.500, Point.CARTESIAN),
				Point(25.000, 69.000, Point.CARTESIAN),
				Point(scoreX, 74.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var grabThird: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 74.000, Point.CARTESIAN),
				Point(16.000, 64.000, Point.CARTESIAN),
				Point(38.000, 32.500, Point.CARTESIAN),
				Point(grabPose)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var scoreThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(grabPose),
				Point(scoreX, 71.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var grabFourth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 71.000, Point.CARTESIAN),
				Point(16.000, 64.000, Point.CARTESIAN),
				Point(38.000, 32.500, Point.CARTESIAN),
				Point(grabPose)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var scoreFourth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabPose),
				Point(22.500, 62.000, Point.CARTESIAN),
				Point(scoreX, 68.000, Point.CARTESIAN)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var grabFifth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(43.250, 68.000, Point.CARTESIAN),
				Point(16.000, 64.000, Point.CARTESIAN),
				Point(38.000, 32.500, Point.CARTESIAN),
				Point(grabPose)
			)
		)
		.setPathEndTimeoutConstraint(0.0)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	var scoreFifth: PathChain = PathBuilder()
		.addPath(
			BezierCurve(
				Point(grabPose),
				Point(22.500, 62.000, Point.CARTESIAN),
				Point(scoreX, 66.000, Point.CARTESIAN)
			)
		)
		.setZeroPowerAccelerationMultiplier(3.5)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	companion object {
		val startPose = Pose(6.5, 65.0, 0.deg)
		val grabPose = Pose(6.5, 35.0, 0.deg)
		val scoreX = 43.500
	}
}