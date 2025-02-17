package org.firstinspires.ftc.teamcode.utility.functions

fun angularDifference(current: Double, target: Double): Double {
    return ((target - current + 180.0) posmod 360.0) - 180.0
}