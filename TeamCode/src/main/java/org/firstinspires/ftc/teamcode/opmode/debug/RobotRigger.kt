package org.firstinspires.ftc.teamcode.opmode.debug

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.functions.deg

@Config
@TeleOp(group = "Debug")
class RobotRigger : BaseTemplate() {
    val lift by lazy { Robot.Subsystems.lift }
    val extension by lazy { Robot.Subsystems.extension }

    override fun initialize() {}

    override fun cycle() {
        lift.target = liftTarget
        extension.target = extensionTarget

        Robot.Subsystems.intake.claw.manual(intakeClawAngle)
        Robot.Subsystems.intake.twist(intakeClawTwist.deg)
        Robot.Subsystems.intake.pitch(intakeClawPitch.deg)
        Robot.Subsystems.intake.dropTo(intakeArmAngle.deg)
        Robot.Subsystems.intake.turnTo(turretAngle.deg)

        Robot.Subsystems.deposit.raiseTo(depositArmAngle.deg)
        Robot.Subsystems.deposit.pitchTo(depositPivotAngle.deg)
        Robot.Subsystems.deposit.claw.manual(backClawAngle)
        Robot.Subsystems.deposit.extension.position = extensionAngle.deg

        telemetry.addData("lift power", lift.power)
        telemetry.addData("lift position", lift.position)
        telemetry.addData("lift target", lift.target)

        telemetry.addData("extension power", extension.power)
        telemetry.addData("extension position", extension.position)
        telemetry.addData("extension target", extension.target)
    }

    companion object {
        @JvmField
        var liftTarget = 0.0

        @JvmField
        var extensionTarget = 0.0

        @JvmField
        var intakeClawPitch = 295.0

        @JvmField
        var intakeArmAngle = 48.0

        @JvmField
        var turretAngle = 79

        @JvmField
        var depositArmAngle = 355.0

        @JvmField
        var depositPivotAngle = 7.0

        @JvmField
        var extensionAngle = 0.0

        @JvmField
        var intakeClawTwist = 0.0

        @JvmField
        var intakeClawAngle = 4.5

        @JvmField
        var backClawAngle = 1.5
    }
}
