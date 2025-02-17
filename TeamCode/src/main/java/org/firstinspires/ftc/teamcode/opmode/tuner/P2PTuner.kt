package org.firstinspires.ftc.teamcode.opmode.tuner

import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.*
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import org.firstinspires.ftc.teamcode.command.auto.GoToPoint
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate

class P2PTuner : BaseTemplate() {
    override fun initialize() {
        GamepadButton(primary, DPAD_LEFT)
            .whenPressed(GoToPoint(-30.0, 0.0, 0.0))

        GamepadButton(primary, DPAD_RIGHT)
            .whenPressed(GoToPoint(30.0, 0.0, 0.0))

        GamepadButton(primary, DPAD_UP)
            .whenPressed(GoToPoint(0.0, 30.0, 0.0))

        GamepadButton(primary, DPAD_DOWN)
            .whenPressed(GoToPoint(0.0, -30.0, 0.0))

        GamepadButton(primary, LEFT_STICK_BUTTON)
            .whenPressed(GoToPoint(0.0, 0.0, 0.0))

        GamepadButton(primary, RIGHT_STICK_BUTTON)
            .whenPressed(GoToPoint(0.0, 0.0, 180.0))
    }

    override fun cycle() {
        telemetry.addData("pose", Robot.pose)
    }
}