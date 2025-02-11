package org.firstinspires.ftc.teamcode.utility.controller

import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.Double.Companion.NaN
import kotlin.math.abs
import kotlin.math.min

open class PIDFController(
    var kP: Double,
    var kI: Double,
    var kD: Double,
    var kF: Double
) {
    private var currentError = 0.0
    private var lastError = 0.0
    private var integralSum = 0.0

    private var lastTimeStamp = 0.0
    private var elapsedTime = ElapsedTime()

    var maxOutput: Double = NaN
    var minOutput: Double = NaN

    var threshold = 0.0

    constructor(
        kP: Double,
        kI: Double,
        kD: Double,
        kF: Double,
        maxOutput: Double,
        minOutput: Double
    ) : this(kP, kI, kD, kF) {
        this.maxOutput = maxOutput
        this.minOutput = minOutput
    }

    constructor(
        kP: Double,
        kI: Double,
        kD: Double,
        kF: Double,
        maxOutput: Double,
        minOutput: Double,
        threshold: Double,
    ) : this(kP, kI, kD, kF) {
        this.maxOutput = maxOutput
        this.minOutput = minOutput
        this.threshold = threshold
    }

    constructor(
        kP: Double,
        kI: Double,
        kD: Double,
        kF: Double,
        maxOutput: Double
    ) : this(kP, kI, kD, kF, maxOutput, -maxOutput)

    fun clamp(minOutput: Double, maxOutput: Double) {
        this.minOutput = minOutput
        this.maxOutput = maxOutput
    }

    fun threshold(threshold: Double) {
        this.threshold = threshold
    }

    fun updateCoefficients(p: Double, i: Double, d: Double, f: Double) {
        kP = p
        kI = i
        kD = d
        kF = f
    }

    open fun calculate(currentPosition: Double, targetPosition: Double): Double {
        val currentTimeStamp = elapsedTime.milliseconds()
        val timeStep = if (lastTimeStamp > 0.0) (currentTimeStamp - lastTimeStamp) / 1000.0 else 0.0

        currentError = targetPosition - currentPosition

        if (abs(currentError) <= threshold) return 0.0

        val p = kP * currentError

        if (timeStep > 0) integralSum += currentError * timeStep
        val i = kI * integralSum

        val d = if (timeStep > 0) kD * (currentError - lastError) / timeStep else 0.0

        val f = kF

        var output = p + i + d + f

        if (!minOutput.isNaN() && !maxOutput.isNaN()) {
            output = output.coerceIn(minOutput, maxOutput)
        }

        lastError = currentError
        lastTimeStamp = currentTimeStamp

        return output
    }
}