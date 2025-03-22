package org.firstinspires.ftc.teamcode.paths

import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathChain
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.utility.functions.deg

class FiveSpecimenPaths(val follower: Follower) {
	val builder = follower.pathBuilder()

	val preload: PathChain = builder
		.addPath(
			BezierLine(
				Point(6.500, 66.000, Point.CARTESIAN),
				Point(43.000, 66.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	val behindFirst: PathChain = builder
		.addPath(
			BezierCurve(
				Point(43.000, 66.000, Point.CARTESIAN),
				Point(17.500, 69.000, Point.CARTESIAN),
				Point(22.500, 29.000, Point.CARTESIAN),
				Point(50.250, 55.500, Point.CARTESIAN),
				Point(60.500, 24.000, Point.CARTESIAN)
			)
		)
		.setLinearHeadingInterpolation(0.deg, 0.deg)
		.build()

	companion object {
		val startPose = Pose(6.5, 66.0, 0.deg)
	}
}
