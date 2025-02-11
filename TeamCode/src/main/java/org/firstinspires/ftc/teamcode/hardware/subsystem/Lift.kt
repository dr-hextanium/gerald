package org.firstinspires.ftc.teamcode.hardware.subsystem

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.utility.controller.VCPIDFController

class Lift(
    val left: CachingDcMotorEx,
    val right: CachingDcMotorEx,
) : ISubsystem {
    val controller = VCPIDFController(kP, kI, kD, kF, 13.0)
    val motors = listOf(left, right)

    var position = 0.0
    var target = 0.0

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
        position = (left.currentPosition) * inchesPerTick
    }

    override fun update() {
        controller.updateCoefficients(kP, kI, kD, kF)
    }

    override fun write() {
        val response = controller.calculate(position, target)

        right.power = response
        left.power = response
    }

    companion object {
        var kP = 0.05
        var kI = 0.0
        var kD = 0.0005
        var kF = 0.0

        const val ticksPerInch = 1.0
        const val inchesPerTick = 1.0 / ticksPerInch

        const val MAX_POWER = 0.8
        const val THRESHOLD = 0.5
    }
}