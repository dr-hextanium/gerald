package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import org.firstinspires.ftc.teamcode.hardware.Robot
import kotlin.math.pow
import kotlin.math.sqrt

class Vision(val limelight: Limelight3A) : ISubsystem {
	val result: LLResult?
		get() = limelight.latestResult
	val detections
		get() = result?.detectorResults

	var distance = 0.0

	override fun reset() {
		limelight.start()
		limelight.pipelineSwitch(0)
	}

	override fun read() {
		var minDist = 10000.0
		var minCorners: List<List<Double?>?>? = null

		var minX = 0.0
		var minY = 0.0

		if (detections != null) {
			for (detection in detections!!) {
				val className = detection.className // What was detected
				val x = detection.targetXDegrees // Where it is (left-right)
				val y = detection.targetYDegrees // Where it is (up-down)
				val corners = detection.targetCorners

				if (className == "red") {
					val dist = sqrt(x.pow(2.0) + y.pow(2.0))
					if (dist < minDist) {
						minDist = dist
						minCorners = corners
						minX = x
						minY = y
					}
				}
			}
		}

		distance = yA * minY.pow(2.0) + yB * minY + yC

//		limelight.pipelineSwitch(1)
//
//		val inputs = DoubleArray(8)
//		if (minCorners != null) {
//			for (i in minCorners.indices) {
//				for (j in minCorners[i]!!.indices) {
//					inputs[i * 2 + j] = minCorners[i]!![j]!!
//				}
//			}
//		}
//
//		limelight.updatePythonInputs(inputs)
	}

	override fun update() {
		Robot.telemetry.addData("running?", limelight.isRunning)
		Robot.telemetry.addData("distance", distance)
		Robot.telemetry.addData("detections size", detections?.size)
		Robot.telemetry.addData("ty", result?.ty)
//		Robot.telemetry.addData("detections size", detections?.)
	}

	override fun write() {
	}

	companion object {
		const val yA = 0.0243753
		const val yB = -0.563117
		const val yC = 11.26297
		const val minDist = 10000.0
	}
}