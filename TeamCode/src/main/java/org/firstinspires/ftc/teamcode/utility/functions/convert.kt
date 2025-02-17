package org.firstinspires.ftc.teamcode.utility.functions

fun ClosedRange<Int>.convert(number: Int, target: ClosedRange<Int>): Int {
    val ratio = number.toDouble() / (endInclusive - start)
    val delta = (target.endInclusive - target.start)

    return (ratio * delta).toInt()
}

fun ClosedRange<Double>.convert(number: Double, target: ClosedRange<Double>): Double {
    val ratio = number / (endInclusive - start)
    val delta = (target.endInclusive - target.start)

    return ratio * delta
}