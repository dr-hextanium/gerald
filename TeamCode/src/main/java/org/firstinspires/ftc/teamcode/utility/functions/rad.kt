package org.firstinspires.ftc.teamcode.utility.functions

val Int.rad: Double
	get() = Math.toDegrees(this.toDouble())

val Double.rad: Double
	get() = Math.toDegrees(this)