package org.firstinspires.ftc.teamcode.opmode.debug.tuner

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import kotlin.math.abs
import kotlin.math.pow

@TeleOp(name = "Lift PID Tuner", group = "Tuning")
class LiftTuner : BaseTemplate() {
    val lift by lazy { Robot.Subsystems.lift }

    var testing = false
    var overshoot = false

    var stepHeight = 5.0 // inches

    var start = 0.0
    var previousTime = 0.0

    var previousError = 0.0

    override fun initialize() { }

    override fun cycle() {
        if (gamepad1.cross) { lift.target = 0.0 }

        if (gamepad1.triangle && !testing) {
            overshoot = false
            testing = true

            start = time
            previousTime = time

            lift.target = lift.position + stepHeight
        }

        val error = lift.target - lift.position

        if (error < 0) overshoot = true // bc like if it shoots up that's bad frfr

        val now = time
        val dt = now - previousTime

        val errorRate = if (dt > 0) (error - previousError) / dt else 0.0

        if (testing && abs(error) < Lift.THRESHOLD) {
            val responseTime = time - start
            tune(responseTime, overshoot, errorRate)

            testing = false

            telemetry.addData("new kP", Lift.kP)
            telemetry.addData("new kD", Lift.kD)
            telemetry.addData("response time", responseTime)
            telemetry.addData("overshoot", overshoot)
            telemetry.update()
        }

        previousError = error
        previousTime = now
    }

    private fun tune(responseTime: Double, overshoot: Boolean, errorRate: Double) {
        val desiredOmegaN = 4.0 // frequency of the oscillations (you can tune this value)

        // Calculate kP based on the desired omegaN^2
        val desiredKP = desiredOmegaN.pow(2)

        // Gradually adjust kP based on the response time
        if (responseTime > 1.0) {
            Lift.kP = desiredKP * 0.9 // slow response → decrease kP slightly
        } else if (responseTime < 0.5) {
            Lift.kP = desiredKP * 1.1 // fast response → increase kP slightly
        } else {
            Lift.kP = desiredKP // keep kP tied to omegaN^2
        }

        // Modify kD based on overshoot and error rate
        val desiredKD = 2 * desiredOmegaN
        if (overshoot) {
            Lift.kD *= 1.2 // if overshooting, add more damping
        }

        if (errorRate > 0) {
            Lift.kD *= 1.05 // de/dt too fast, increase kD
        } else if (errorRate < 0) {
            Lift.kD *= 0.95 // de/dt too slow, decrease kD
        }

        // Apply a safety limit to kD to prevent runaway values
        Lift.kD = desiredKD * Lift.kD.coerceIn(0.001..0.5) // Keeps kD within a reasonable range
    }

    override fun stop() {
        lift.target = lift.position
        lift.write()
    }
}
