package org.firstinspires.ftc.teamcode.utility.controller

import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.Double.Companion.NaN
import kotlin.math.abs
import kotlin.math.min

open class PIDFController(
    var kP: Double,
    var kI: Double,
    var kD: Double,
    var kF: Double,
    var alpha: Double = 0.8
) {
    private var elapsedTime = ElapsedTime()

    private var currentError = 0.0
    private var lastError = 0.0
    private var integralSum = 0.0
    private var lastTimeStamp = 0.0
    private var lastD = 0.0

    var maxOutput = NaN
    var minOutput = NaN
    var threshold = 0.0

    private val maxIntegral = 10.0

    fun clamp(minOutput: Double, maxOutput: Double): PIDFController {
        this.minOutput = minOutput
        this.maxOutput = maxOutput
        return this
    }

    fun threshold(threshold: Double): PIDFController {
        this.threshold = threshold
        return this
    }

    fun updateCoefficients(p: Double, i: Double, d: Double, f: Double, a: Double = 0.8) {
        kP = p
        kI = i
        kD = d
        kF = f
        alpha = a
    }

    fun atSetPoint() = abs(currentError) <= threshold

    open fun calculate(currentPosition: Double, targetPosition: Double): Double {
        val currentTimeStamp = elapsedTime.milliseconds()
        val timeStep = if (lastTimeStamp > 0.0) (currentTimeStamp - lastTimeStamp) / 1000.0 else 0.0

        currentError = targetPosition - currentPosition

        if (atSetPoint()) {
            integralSum = 0.0
            return kF
        }

        val p = kP * currentError

        if (timeStep > 0) integralSum += currentError * timeStep
        integralSum = integralSum.coerceIn(-maxIntegral, maxIntegral)

        val i = kI * integralSum

        val rawD = if (timeStep > 0) (currentError - lastError) / timeStep else 0.0
        val d = kD * (alpha * rawD + (1.0 - alpha) * lastD)

        val f = kF

        var output = p + i + d + f

        if (!minOutput.isNaN() && !maxOutput.isNaN()) {
            output = output.coerceIn(minOutput, maxOutput)

            if ((output == maxOutput && currentError > 0) || (output == minOutput && currentError < 0)) {
                integralSum *= 0.9
            }
        }

        lastError = currentError
        lastTimeStamp = currentTimeStamp
        lastD = rawD

        return output
    }
}
