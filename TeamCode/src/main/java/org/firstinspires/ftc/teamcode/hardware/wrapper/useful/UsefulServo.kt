package org.firstinspires.ftc.teamcode.hardware.wrapper.useful

import com.qualcomm.robotcore.hardware.Servo
import dev.frozenmilk.dairy.cachinghardware.CachingServo
import org.firstinspires.ftc.teamcode.utility.convert

class UsefulServo(servo: Servo, val range: UsefulServoRange) : CachingServo(servo) {
    val positions = range.positions.range
    val radians = range.radians.range

    private fun convert(position: Double): Double = radians.convert(position, positions)

    fun bound() { servo.scaleRange(range.positions.lower, range.positions.upper) }

    override fun setPosition(position: Double) = super.setPosition(convert(position))
    override fun setPositionResult(position: Double) = super.setPositionResult(convert(position))
    override fun setPositionRaw(position: Double) = super.setPositionRaw(convert(position))
}

data class Bound(val lower: Double, val upper: Double, val range: ClosedRange<Double> = lower..upper)

data class UsefulServoRange(val positions: Bound, val radians: Bound) {
    constructor(
        startPosition: Double,
        endPosition: Double,
        startRadian: Double,
        endRadian: Double
    ) : this(
        Bound(startPosition, endPosition),
        Bound(startRadian, endRadian),
    )

    init {
        require(positions.lower <= positions.upper)
        require(radians.lower <= radians.upper)
    }
}