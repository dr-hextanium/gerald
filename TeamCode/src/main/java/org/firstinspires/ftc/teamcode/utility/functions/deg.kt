package org.firstinspires.ftc.teamcode.utility.functions

val Int.deg: Double
    get() = Math.toRadians(this.toDouble())

val Double.deg: Double
    get() = Math.toRadians(this)