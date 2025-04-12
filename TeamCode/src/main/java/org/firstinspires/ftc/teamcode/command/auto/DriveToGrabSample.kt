package org.firstinspires.ftc.teamcode.command.auto

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.functions.deg

class DriveToGrabSample : SequentialCommandGroup(
	InstantCommand({ Globals.TELEOP_AUTO = true }),

	PedroPathCommand(
		PathBuilder()
			.addPath(
				BezierLine(
					Robot.follower.pose.let { Point(it.x, it.y, Point.CARTESIAN) },
					Point(30.0, 120.0, Point.CARTESIAN)
				)
			)
			.setZeroPowerAccelerationMultiplier(3.0)
			.setPathEndTimeoutConstraint(0.0)
			.setConstantHeadingInterpolation((-45).deg)
			.addPath(
				BezierLine(
					Point(30.0, 120.0, Point.CARTESIAN),
					Point(11.750, 131.00, Point.CARTESIAN)
				)
			)
			.setZeroPowerAccelerationMultiplier(3.0)
			.setPathEndTimeoutConstraint(0.0)
			.setConstantHeadingInterpolation((-45).deg)
			.build(),

		Robot.follower,

		maxPower = 0.8
	),

	InstantCommand({ Globals.TELEOP_AUTO = false }),
	InstantCommand({ Robot.follower.startTeleopDrive() })
)