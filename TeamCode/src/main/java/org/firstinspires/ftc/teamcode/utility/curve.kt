package org.firstinspires.ftc.teamcode.utility

import kotlin.math.pow

fun curve(x: Double) = (x.pow(3) + x) / 2.0
fun curve(x: Float) = curve(x.toDouble())
