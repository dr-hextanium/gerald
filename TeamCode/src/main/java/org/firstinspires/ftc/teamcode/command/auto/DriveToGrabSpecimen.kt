package org.firstinspires.ftc.teamcode.command.auto

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.functions.deg

class DriveToGrabSpecimen : SequentialCommandGroup(
	InstantCommand({ Globals.TELEOP_AUTO = true }),

	PedroPathCommand(
		PathBuilder()
			.addPath(
				BezierLine(
					Robot.follower.pose.let { Point(it.x, it.y, Point.CARTESIAN) },
					Point(18.000, 36.000, Point.CARTESIAN)
				)
			)
			.setZeroPowerAccelerationMultiplier(3.0)
			.setPathEndTimeoutConstraint(0.0)
			.setConstantHeadingInterpolation(0.deg)
			.addPath(
				BezierLine(
					Point(18.000, 36.000, Point.CARTESIAN),
					Point(6.75, 36.000, Point.CARTESIAN)
				)
			)
			.setZeroPowerAccelerationMultiplier(3.0)
			.setPathEndTimeoutConstraint(0.0)
			.setConstantHeadingInterpolation(0.deg)
			.build(),

		Robot.follower
	),

	InstantCommand({ Globals.TELEOP_AUTO = false }),
	InstantCommand({ Robot.follower.startTeleopDrive() })
)