package org.firstinspires.ftc.teamcode.utility.controller

import org.firstinspires.ftc.teamcode.hardware.Robot
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

open class VCPIDFController(
    kP: Double,
    kI: Double,
    kD: Double,
    kF: Double,
    val tunedAt: Double
) : PIDFController(kP, kI, kD, kF) {
    override fun calculate(currentPosition: Double, targetPosition: Double): Double {
        val output = super.calculate(currentPosition, targetPosition)
        return (tunedAt / Robot.voltage) * sqrt(abs(output)) * output.sign
    }
}