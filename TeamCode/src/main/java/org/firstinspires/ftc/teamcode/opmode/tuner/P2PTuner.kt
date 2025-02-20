package org.firstinspires.ftc.teamcode.opmode.tuner

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.button.GamepadButton
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.*
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.command.auto.GoToPoint
import org.firstinspires.ftc.teamcode.hardware.Globals
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.Robot.Subsystems
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate

@TeleOp
class P2PTuner : BaseTemplate() {
    override fun initialize() {
        GamepadButton(primary, DPAD_LEFT)
            .whenPressed(GoToPoint(-0.0, 30.0, 0.0))

        GamepadButton(primary, DPAD_RIGHT)
            .whenPressed(GoToPoint(0.0, 0.0, 0.0))

        GamepadButton(primary, DPAD_UP)
            .whenPressed(GoToPoint(0.0, 24.0, 0.0))

        GamepadButton(primary, DPAD_DOWN)
            .whenPressed(GoToPoint(0.0, -24.0, 0.0))

        GamepadButton(primary, LEFT_BUMPER)
            .whenPressed(GoToPoint(0.0, 0.0, 0.0))

        GamepadButton(primary, RIGHT_BUMPER)
            .whenPressed(GoToPoint(0.0, 0.0, 180.0))

        GamepadButton(primary, GamepadKeys.Button.LEFT_STICK_BUTTON)
            .whenPressed(InstantCommand({ Subsystems.odometry.resetPose() }))
    }

    override fun cycle() {
        telemetry.addData("dx", Globals.dx)
        telemetry.addData("dy", Globals.dy)
        telemetry.addData("da", Globals.da)

        telemetry.addData("target", Globals.target)
        telemetry.addData("pose", Robot.pose)
    }
}