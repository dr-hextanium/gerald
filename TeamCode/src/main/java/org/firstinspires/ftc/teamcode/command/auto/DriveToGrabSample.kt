package org.firstinspires.ftc.teamcode.command.auto

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot

class DriveToGrabSample : SequentialCommandGroup(
	InstantCommand({ Globals.TELEOP_AUTO = true }),

	PedroPathCommand(
		PathBuilder()
			.addPath(
				BezierCurve(
					Robot.follower.pose.let { Point(it.x, it.y, Point.CARTESIAN) },
					Point(Robot.follower.pose.x, 112.000, Point.CARTESIAN),
					Point(48.000, 96.000, Point.CARTESIAN),
					Point(13.500, 130.000, Point.CARTESIAN)
				)
			)
			.setZeroPowerAccelerationMultiplier(6.0)
			.setPathEndTimeoutConstraint(0.0)
			.setTangentHeadingInterpolation()
			.setReversed(true)
			.build(),

		Robot.follower,

		maxPower = 1.0
	),

	InstantCommand({ Globals.TELEOP_AUTO = false }),
	InstantCommand({ Robot.follower.startTeleopDrive() })
)