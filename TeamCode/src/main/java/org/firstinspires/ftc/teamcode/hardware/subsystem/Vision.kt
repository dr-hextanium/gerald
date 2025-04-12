package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import org.firstinspires.ftc.teamcode.hardware.Robot
import kotlin.math.pow

class Vision(val limelight: Limelight3A) : ISubsystem {
	val result: LLResult?
		get() = limelight.latestResult
	val detections
		get() = result?.detectorResults
	val python
		get() = result?.pythonOutput

	var distance = 0.0
	var angle = 0.0
	var corners: List<List<Double?>?>? = null
//	var minX = 0.0

	fun findDistAngle() : Array<Double> {
		limelight.pipelineSwitch(0)

		var minDist = 30.0
		var minCorners: List<List<Double?>?>? = null

		var minX = 0.0
		var minY = 0.0

		if (detections != null && detections!!.size != 0) {
			for (detection in detections!!) {
				val className = detection.className // What was detected
				val x = detection.targetXDegrees // Where it is (left-right)
				val y = detection.targetYDegrees // Where it is (up-down)
				val corners = detection.targetCorners

				if (className == "red") {
					val dist = yA * y.pow(2.0) + yB * y + yC
					if (dist < minDist) {
						minDist = dist
						minCorners = corners
					}
				}
			}
		}

		if (minDist < 24.0) {
			distance = minDist
		}

		limelight.pipelineSwitch(2)
		val inputs = DoubleArray(8)

		if (minCorners != null) {
			for (i in 0..2 step 2) {
				for (j in minCorners[i]!!.indices) {
					inputs[i + j] = minCorners[i]!![j]!!
				}
			}

			limelight.updatePythonInputs(inputs)

			if (python != null) {
				angle = python!![4]
			}

			corners = minCorners
		}

		limelight.pipelineSwitch(0)

		return arrayOf(distance, angle)
	}

	override fun reset() {
		limelight.start()
		limelight.pipelineSwitch(0)
	}

	override fun read() {
		var minDist = 18.0
		var minCorners: List<List<Double?>?>? = null

		var minY = 0.0

		if (detections != null) {
			for (detection in detections!!) {
				val className = detection.className // What was detected
				val x = detection.targetXDegrees // Where it is (left-right)
				val y = detection.targetYDegrees // Where it is (up-down)
				val corners = detection.targetCorners

				if (className == "red") {
					val dist = yA * y.pow(2.0) + yB * y + yC
					if (dist < minDist) {
						minDist = dist
						minCorners = corners
//						minX = xA * x.pow(2.0) + xB * x + xC
					}
				}
			}
		}

		if (minDist < 18.0) {
			distance = minDist
		}

//		limelight.pipelineSwitch(1)
//
//		val inputs = DoubleArray(8)
//		if (minCorners != null) {
//			for (i in minCorners.indices) {
//				for (j in minCorners[i]!!.indices) {
//					inputs[i * 2 + j] = minCorners[i]!![j]!!
//				}
//			}
//
//			corners = minCorners
//		}
//
//		limelight.updatePythonInputs(inputs)
//
//		if (python != null) {
//			angle = python!![2]
//		}
		limelight.pipelineSwitch(2)
		val inputs = DoubleArray(8)

		if (minCorners != null) {
			for (i in 0..2 step 2) {
				for (j in minCorners[i]!!.indices) {
					inputs[i + j] = minCorners[i]!![j]!!
				}
			}

			limelight.updatePythonInputs(inputs)

			if (python != null) {
				angle = python!![4]
			}

			corners = minCorners
		}

		limelight.pipelineSwitch(0)
	}

	override fun update() {

//		Robot.telemetry.addData("detections size", detections?.)
	}

	override fun write() {
	}

	companion object {
		const val yA = 0.0243753
		const val yB = -0.563117
		const val yC = 11.26297

		const val xA = 0.00137683
		const val xB = -0.332742
		const val xC = 0.449384
		const val minDist = 10000.0
	}
}