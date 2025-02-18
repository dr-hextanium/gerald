package org.firstinspires.ftc.teamcode.command.auto

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.utility.controller.PIDFController
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController
import org.firstinspires.ftc.teamcode.utility.functions.angularDifference
import kotlin.math.absoluteValue
import kotlin.math.hypot

@Config
class GoToPoint(val target: Pose2D) : CommandTemplate() {
    constructor(x: Double, y: Double, theta: Double) : this(Pose2D(x, y, theta))

    val xController: PIDFController
    val yController: PIDFController
    val hController: PIDFController

    // i have no clue if this works with dashboard but we'll see
    init {
        xController = VCPIDFController(X.kP, X.kI, X.kD, X.kF, 13.0)
        yController = VCPIDFController(Y.kP, Y.kI, Y.kD, Y.kF, 13.0)
        hController = VCPIDFController(H.kP, H.kI, H.kD, H.kF, 13.0)

        xController.updateCoefficients(X.kP, X.kI, X.kD, X.kF, X.alpha)
        xController.updateCoefficients(Y.kP, Y.kI, Y.kD, Y.kF, Y.alpha)
        xController.updateCoefficients(H.kP, H.kI, H.kD, H.kF, H.alpha)
    }

    override fun initialize() {}

    override fun execute() {
        val pose = Robot.Subsystems.odometry.pose

        Robot.Subsystems.drive.driveFieldCentric(
            xController.calculate(pose.x, target.x),
            xController.calculate(pose.y, target.y),
            xController.calculate(pose.h, target.h),
            pose.h
        )
    }

    override fun isFinished(): Boolean {
        val pose = Robot.Subsystems.odometry.pose

        val dx = target.x - pose.x
        val dy = target.y - pose.y

        val ds = hypot(dx, dy)
        val da = angularDifference(pose.h, target.h).absoluteValue

        return (ds <= DISPLACEMENT_TOLERANCE) && (da <= ANGULAR_TOLERANCE)
    }

    companion object {
        @JvmField
        var DISPLACEMENT_TOLERANCE = 1.0

        @JvmField
        var ANGULAR_TOLERANCE = 0.05

        object X {
            @JvmField var kP = 0.00
            @JvmField var kI = 0.00
            @JvmField var kD = 0.000
            @JvmField var kF = 0.00
            @JvmField var alpha = 0.8
        }

        object Y {
            @JvmField var kP = 0.00
            @JvmField var kI = 0.00
            @JvmField var kD = 0.000
            @JvmField var kF = 0.00
            @JvmField var alpha = 0.8
        }

        object H {
            @JvmField var kP = 0.00
            @JvmField var kI = 0.00
            @JvmField var kD = 0.000
            @JvmField var kF = 0.00
            @JvmField var alpha = 0.8
        }
    }
}