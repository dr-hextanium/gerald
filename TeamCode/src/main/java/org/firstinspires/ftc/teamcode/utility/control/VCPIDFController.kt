package org.firstinspires.ftc.teamcode.utility.controller

import org.firstinspires.ftc.teamcode.hardware.Robot

open class VCPIDFController(
    kP: Double,
    kI: Double,
    kD: Double,
    kF: Double,
    val tunedAt: Double
) : PIDFController(kP, kI, kD, kF) {
    override fun calculate(currentPosition: Double, targetPosition: Double): Double {
        return (tunedAt / Robot.voltage) * super.calculate(currentPosition, targetPosition)
    }
}