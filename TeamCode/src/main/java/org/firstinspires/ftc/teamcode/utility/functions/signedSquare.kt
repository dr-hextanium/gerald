package org.firstinspires.ftc.teamcode.utility.functions

import kotlin.math.abs

val Double.signedSquare
	get() = abs(this) * this

val Double.halfLinearHalfQuadratic
	get() = (this + this.signedSquare) / 2.0

val Int.signedSquare
	get() = abs(this) * this