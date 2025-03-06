package org.firstinspires.ftc.teamcode.command.auto

import com.acmerobotics.dashboard.config.Config
import com.arcrobotics.ftclib.command.ParallelRaceGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.controller.PIDFController
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController
import org.firstinspires.ftc.teamcode.utility.functions.angularDifference
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.hypot

@Config
class GoToPoint(val target: Pose2D) : CommandTemplate() {
    constructor(x: Double, y: Double, theta: Double) : this(Pose2D(x, y, theta))

    lateinit var xController: PIDFController
    lateinit var yController: PIDFController
    lateinit var hController: PIDFController

    override fun initialize() {
        xController = VCPIDFController(X.kP, X.kI, X.kD, X.kF, 13.0)
        yController = VCPIDFController(Y.kP, Y.kI, Y.kD, Y.kF, 13.0)
        hController = VCPIDFController(H.kP, H.kI, H.kD, H.kF, 13.0)

        Globals.target = target
    }

    override fun execute() {
        val pose = Robot.Subsystems.odometry.pose

        xController.updateCoefficients(X.kP, X.kI, X.kD, X.kF, X.alpha)
        yController.updateCoefficients(Y.kP, Y.kI, Y.kD, Y.kF, Y.alpha)
        hController.updateCoefficients(H.kP, H.kI, H.kD, H.kF, H.alpha)

        val x = xController.calculate(pose.x, target.x)
        val y = yController.calculate(pose.y, target.y)
        val h = hController.calculate(AngleUnit.normalizeDegrees(pose.h - target.h))

        Robot.telemetry.addData("x", x)
        Robot.telemetry.addData("y", y)
        Robot.telemetry.addData("h", h)

        Robot.Subsystems.drive.driveFieldCentric(-x, y, -h, pose.h)
    }

    override fun isFinished(): Boolean {
        if (Globals.target != target) return true

        val pose = Robot.Subsystems.odometry.pose

        val dx = target.x - pose.x
        val dy = target.y - pose.y

        val ds = hypot(dx, dy)
        val da = AngleUnit.normalizeDegrees(pose.h - target.h)

        Globals.da = da
        Globals.dx = dx
        Globals.dy = dy

        return (ds <= DISPLACEMENT_TOLERANCE) && (abs(da) <= ANGULAR_TOLERANCE)
    }

    override fun end(interrupted: Boolean) {
        Globals.da = 0.0
        Globals.dx = 0.0
        Globals.dy = 0.0
        Robot.Subsystems.drive.driveFieldCentric(0.0, 0.0, 0.0, 0.0)
    }

    @Config
    object X {
        @JvmField var kP = 0.03
        @JvmField var kI = 0.003
        @JvmField var kD = 0.000
        @JvmField var kF = 0.00
        @JvmField var alpha = 0.8
    }

    @Config
    object Y {
        @JvmField var kP = 0.015
        @JvmField var kI = 0.00
        @JvmField var kD = 0.000
        @JvmField var kF = 0.00
        @JvmField var alpha = 0.8
    }

    @Config
    object H {
        @JvmField var kP = 0.01
        @JvmField var kI = 0.03
        @JvmField var kD = 0.001
        @JvmField var kF = 0.1
        @JvmField var alpha = 0.8
    }

    companion object {
        @JvmField
        var DISPLACEMENT_TOLERANCE = 1.0

        @JvmField
        var ANGULAR_TOLERANCE = 1.0
    }
}

class GoToPointTimed(target: Pose2D, time: Long) : ParallelRaceGroup(
    GoToPoint(target),
    WaitCommand(time)
)