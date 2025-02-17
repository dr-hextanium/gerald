package org.firstinspires.ftc.teamcode.utility.functions

import kotlin.math.floor

// double-point mod x/y, wrapping equally around both
infix fun Double.posmod(other: Double) = this - floor(this / other) * other