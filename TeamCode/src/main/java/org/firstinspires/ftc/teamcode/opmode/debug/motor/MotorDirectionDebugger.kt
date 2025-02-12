package org.firstinspires.ftc.teamcode.opmode.debug.motor

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.curve

@TeleOp(group = "Debug")
class MotorDirectionDebugger : BaseTemplate() {
    var power = 0.0

    val motor by lazy { Robot.Motors.all().first() }

    override fun initialize() { motor.power = 0.0 }

    override fun cycle() {
        val left = curve(gamepad1.left_trigger)
        val right = curve(gamepad1.right_trigger)

        power = right - left

        motor.power = power

        telemetry.addData("power", power)
    }
}