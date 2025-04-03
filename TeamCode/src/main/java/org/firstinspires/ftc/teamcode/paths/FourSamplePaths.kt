package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

object FourSamplePaths {
	val scorePoint = Point(11.750, 131.00, Point.CARTESIAN)

	var preload: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(6.500, 114.000, Point.CARTESIAN),
				scorePoint
			)
		)
		.setLinearHeadingInterpolation(0.deg, (-45).deg)
		.build()

	var lineUpFirst: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				scorePoint,
				Point(16.000, 121.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation((-45).deg, 0.deg)
		.build()

	var scoreFirst: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(16.000, 121.000, Point.CARTESIAN),
				scorePoint
			)
		)
		.setLinearHeadingInterpolation(0.deg, (-45).deg)
		.build()

	var lineUpSecond: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				scorePoint,
				Point(16.000, 131.500, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation((-45).deg, 0.deg)
		.build()

	var scoreSecond: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(16.000, 131.500, Point.CARTESIAN),
				scorePoint
			)
		)
		.setLinearHeadingInterpolation(0.deg, (-45).deg)
		.build()

	var lineUpThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				scorePoint,
				Point(19.500, 126.500, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation((-45).deg, 35.deg)
		.build()

	var scoreThird: PathChain = PathBuilder()
		.addPath(
			BezierLine(
				Point(19.500, 126.500, Point.CARTESIAN),
				scorePoint
			)
		)
		.setLinearHeadingInterpolation(35.deg, (-45).deg)
		.build()

	val startPose = Pose(6.5, 114.0, 0.deg)
}
