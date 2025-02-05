package org.firstinspires.ftc.teamcode.utility

val Int.deg: Double
    get() = Math.toRadians(this.toDouble())

val Double.deg: Double
    get() = Math.toRadians(this)