package org.firstinspires.ftc.teamcode.utility.controller

import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

class SquIDController(
    kP: Double,
    kF: Double,
    tunedAt: Double
) : VCPIDFController(kP, 0.0, 0.0, kF, tunedAt) {
    override fun calculate(currentPosition: Double, targetPosition: Double): Double {
        val actual = super.calculate(currentPosition, targetPosition)
        return sqrt(abs(actual)) * actual.sign
    }
}