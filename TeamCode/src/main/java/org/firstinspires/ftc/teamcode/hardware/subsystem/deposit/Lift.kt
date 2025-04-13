package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.utility.controller.PIDFController
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController
import kotlin.math.abs
import kotlin.math.sign

@Config
class Lift(
    val left: CachingDcMotorEx,
    val right: CachingDcMotorEx
) : ISubsystem {
    val controller: PIDFController
    val motors = listOf(left, right)

    init {
    	controller = if (Globals.AUTO) {
            VCPIDFController(Auto.kP, Auto.kI, Auto.kD, Auto.kF, 13.0)
        } else {
            VCPIDFController(kP, kI, kD, kF, 13.0)
        }
    }

    var position = 0.0
    var target = 0.0
    var power = 0.0

    override fun reset() {
        motors.forEach {
            it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            it.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
            it.power = 0.0
            it.setCurrentAlert(3.5, CurrentUnit.AMPS)
        }

        position = 0.0
        target = 0.0

        left.direction = DcMotorSimple.Direction.REVERSE

        controller.clamp(-MAX_POWER, MAX_POWER)
        controller.threshold(THRESHOLD)
    }

    override fun read() {
        position = (right.currentPosition) * inchesPerTick
    }

    override fun update() {
        if (Globals.AUTO) {
            controller.updateCoefficients(Auto.kP, Auto.kI, Auto.kD, Auto.kF, alpha)
        } else {
            controller.updateCoefficients(kP, kI, kD, kF, alpha)
        }

        val output = controller.calculate(position, target)

        power = output
    }

    override fun write() {
        right.power = power
        left.power = power
    }

    fun nudge(amount: Double) { target += amount }

    fun busy() = !controller.atSetPoint()

    fun within(tolerance: Double) = abs(target - position) <= tolerance

    companion object {
        object Auto {
            var kP = 0.051
            var kI = 0.00
            var kD = 0.000
            var kF = 0.05
            var alpha = 0.8
        }

        @JvmField
        var kP = 0.1 //0.08
        @JvmField
        var kI = 0.00
        @JvmField
        var kD = 0.000
        @JvmField
        var kF = 0.05
        @JvmField
        var alpha = 0.8

        const val ticksPerInch = 1150.0 / 35.0
        const val inchesPerTick = 1.0 / ticksPerInch

        const val MAX_POWER = 0.8
        const val THRESHOLD = 0.01
    }
}