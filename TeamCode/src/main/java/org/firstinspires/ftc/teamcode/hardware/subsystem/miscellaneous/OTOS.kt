package org.firstinspires.ftc.teamcode.hardware.subsystem.miscellaneous

import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem
import org.firstinspires.ftc.teamcode.hardware.wrapper.SparkFunOTOSCorrected

class OTOS(val sensor: SparkFunOTOSCorrected) : ISubsystem {
    val calibrationProgress: Int
        get() = sensor.imuCalibrationProgress

    val isDoneCalibration: Boolean
        get() = calibrationProgress < 1

    val pose: Pose2D
        get() {
            val raw = sensor.position

            val xMultiplier = (48.0 / 40.38) * (48.0 / 52.0)
            val yMultiplier = 1.0

            return Pose2D(raw.x * xMultiplier, raw.y * yMultiplier, raw.h)
        }

    fun getDegrees() = pose.h

    fun setPosition(x: Double, y: Double) {
        sensor.position = Pose2D(x, y, pose.h)
    }

    override fun reset() {
        sensor.linearUnit = DistanceUnit.INCH
        sensor.angularUnit = AngleUnit.DEGREES

        // 2 * Math.PI - Math.PI / 2
        sensor.offset = Pose2D(-1.645, 0.0, 0.0)
        // 48.0 / listOf(43.3734, 42.3882, 41.8716).average()
//        sensor.setLinearScalar(1.00)
        sensor.setLinearScalar(1.127)
        sensor.setAngularScalar(3600.0 / 3594.0)

        sensor.calibrateImu(255, true)

        sensor.resetTracking()

        resetPose()
    }

    override fun read() {

    }

    override fun update() { }

    override fun write() { }

    fun resetHeading() {
        sensor.position = Pose2D(pose.x, pose.y, 0.0)
    }

    fun resetPose() {
        sensor.position = Pose2D(0.0, 0.0, 0.0)
    }
}