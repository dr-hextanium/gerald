package dev.frozenmilk.dairy.cachinghardware

import com.qualcomm.robotcore.hardware.CRServo
import kotlin.math.abs

open class CachingCRServo
/**
 * @param crServo the continuous rotation servo to encapsulate in the caching control
 * @param cachingTolerance the power delta threshold at which a power write will occur.
 */
@JvmOverloads
constructor(
	open val crServo: CRServo,
	open var cachingTolerance: Double = 0.005
) : CRServo by crServo {
	private var cachedPower = Double.NaN
	/**
	 * Checks if the change in [power] since last write exceeds [cachingTolerance], if so, does a hardware write (actually sets the power)
	 *
	 * @see com.qualcomm.robotcore.hardware.CRServo.setPower
	 * @param power the new power level of the servo, a value in the interval [-1.0, 1.0]
	 */
	override fun setPower(power: Double) {
		val corrected = power.coerceIn(-1.0..1.0)
		// will accept the input if it is targeting 0, or full power in any direction, or if it has changed a sufficient amount
		synchronized(this) {
			if (abs(corrected - cachedPower) >= cachingTolerance || (corrected == 0.0 && cachedPower != 0.0) || (corrected >= 1.0 && !(cachedPower >= 1.0)) || (corrected <= -1.0 && !(cachedPower <= -1.0)) || cachedPower.isNaN()) {
				cachedPower = corrected
				crServo.power = corrected
			}
		}
	}

	/**
	 * Checks if the change in [power] since last write exceeds [cachingTolerance], if so, does a hardware write (actually sets the power)
	 * @see setPower
	 * @see com.qualcomm.robotcore.hardware.CRServo.setPower
	 * @param power the new power level of the servo, a value in the interval [-1.0, 1.0]
	 * @return if a hardware write to update the output to the servo was executed
	 */
	open fun setPowerResult(power: Double): Boolean {
		val corrected = power.coerceIn(-1.0..1.0)
		// will accept the input if it is targeting 0, or full power in any direction, or if it has changed a sufficient amount
		synchronized(this) {
			if (abs(corrected - cachedPower) >= cachingTolerance || (corrected == 0.0 && cachedPower != 0.0) || (corrected >= 1.0 && !(cachedPower >= 1.0)) || (corrected <= -1.0 && !(cachedPower <= -1.0)) || cachedPower.isNaN()) {
				cachedPower = corrected
				crServo.power = corrected
				return true
			}
		}
		return false
	}

	/**
	 * Sets [cachingTolerance] to 0 temporarily, then performs and returns [setPowerResult]
	 */
	open fun setPowerRaw(power: Double): Boolean {
		synchronized(this) {
			val cachingTolerance = this.cachingTolerance
			this.cachingTolerance = 0.0
			val res = setPowerResult(power)
			this.cachingTolerance = cachingTolerance
			return res
		}
	}
}
