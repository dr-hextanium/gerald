package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController

@Config
class Lift(
    val left: CachingDcMotorEx,
    val right: CachingDcMotorEx
) : ISubsystem {
    val controller = VCPIDFController(kP, kI, kD, kF, 13.0)
    val motors = listOf(left, right)

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
        controller.updateCoefficients(kP, kI, kD, kF)
        power = controller.calculate(position, target)
    }

    override fun write() {
        right.power = power
        left.power = power
    }

    fun busy() = !controller.atSetPoint()

    companion object {
        @JvmField
        var kP = 0.01
        @JvmField
        var kI = 0.0
        @JvmField
        var kD = 0.000
        @JvmField
        var kF = 0.1

        const val ticksPerInch = 1150.0 / 35.0
        const val inchesPerTick = 1.0 / ticksPerInch

        const val MAX_POWER = 0.8
        const val THRESHOLD = 0.005
    }
}