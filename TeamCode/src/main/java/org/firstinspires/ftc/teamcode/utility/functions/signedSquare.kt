package org.firstinspires.ftc.teamcode.utility.functions

import kotlin.math.abs

val Double.signedSquare
	get() = abs(this) * this

val Int.signedSquare
	get() = abs(this) * this