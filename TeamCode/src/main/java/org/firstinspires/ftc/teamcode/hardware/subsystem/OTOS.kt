package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.arcrobotics.ftclib.geometry.Pose2d
import com.arcrobotics.ftclib.geometry.Rotation2d
import com.qualcomm.hardware.sparkfun.SparkFunOTOS
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.hardware.wrapper.SparkFunOTOSCorrected

class OTOS(val sensor: SparkFunOTOSCorrected) : ISubsystem {
    val calibrationProgress: Int
        get() = sensor.imuCalibrationProgress

    val isDoneCalibration: Boolean
        get() = calibrationProgress < 1

    var pose = SparkFunOTOS.Pose2D()

    fun getPose(): Pose2d {
        return Pose2d(
            pose.x, pose.y, Rotation2d.fromDegrees(pose.h)
        )
    }

    fun getDegrees() = pose.h

    fun setPosition(x: Double, y: Double) {
        sensor.position = SparkFunOTOS.Pose2D(x, y, pose.h)
    }

    override fun reset() {
        sensor.linearUnit = DistanceUnit.INCH
        sensor.angularUnit = AngleUnit.DEGREES

        // 2 * Math.PI - Math.PI / 2
        sensor.offset = SparkFunOTOS.Pose2D(0.0, 0.0, 0.0)
        sensor.setLinearScalar(1.0)
        sensor.setAngularScalar(1.0)

        sensor.calibrateImu(255, true)

        sensor.resetTracking()
    }

    override fun read() { pose = sensor.position }

    override fun update() { }

    override fun write() { }

    fun resetPose() {
        sensor.position = SparkFunOTOS.Pose2D(0.0, 0.0, 0.0)
    }
}