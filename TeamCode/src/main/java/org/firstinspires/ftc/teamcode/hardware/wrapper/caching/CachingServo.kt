package dev.frozenmilk.dairy.cachinghardware

import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoController
import kotlin.math.abs

open class CachingServo
/**
 * @param servo the servo to encapsulate in the caching control
 * @param cachingTolerance the position delta threshold at which a position write will occur.
 */
@JvmOverloads
constructor(
	open val servo: Servo,
	open var cachingTolerance: Double = 0.001
) : Servo by servo {
	private var cachedPosition = Double.NaN
	/**
	 * Checks if the change in [position] since last write exceeds [cachingTolerance], if so, does a hardware write (actually sets the position)
	 *
	 * @param position the position to which the servo should move, a value in the range [0.0, 1.0]
	 * @see com.qualcomm.robotcore.hardware.Servo.setPosition
	 * @see ServoController.pwmEnable
	 */
	override fun setPosition(position: Double) {
		val corrected = position.coerceIn(0.0..1.0)
		// will accept the input if it is full position in any direction, or if it has changed a sufficient amount
		synchronized(this) {
			if (abs(corrected - cachedPosition) >= cachingTolerance || (corrected <= 0.0 && !(cachedPosition <= 0.0)) || (corrected >= 1.0 && !(cachedPosition >= 1.0)) || cachedPosition.isNaN()) {
				cachedPosition = corrected
				servo.position = corrected
			}
		}
	}

	/**
	 * Checks if the change in [position] since last write exceeds [cachingTolerance], if so, does a hardware write (actually sets the position)
	 *
	 * @see setPosition
	 * @see com.qualcomm.robotcore.hardware.Servo.setPosition
	 * @param position the position to which the servo should move, a value in the range [0.0, 1.0]
	 * @return if a hardware write to update the output to the servo was executed
	 */
	open fun setPositionResult(position: Double): Boolean {
		val corrected = position.coerceIn(0.0..1.0)
		// will accept the input if it is full position in any direction, or if it has changed a sufficient amount
		synchronized(this) {
			if (abs(corrected - cachedPosition) >= cachingTolerance || (corrected <= 0.0 && !(cachedPosition <= 0.0)) || (corrected >= 1.0 && !(cachedPosition >= 1.0)) || cachedPosition.isNaN()) {
				cachedPosition = corrected
				servo.position = corrected
				return true
			}
		}
		return false
	}

	/**
	 * Sets [cachingTolerance] to 0 temporarily, then performs and returns [setPositionResult]
	 */
	open fun setPositionRaw(position: Double): Boolean {
		synchronized(this) {
			val cachingTolerance = this.cachingTolerance
			this.cachingTolerance = 0.0
			val res = setPositionResult(position)
			this.cachingTolerance = cachingTolerance
			return res
		}
	}
}
