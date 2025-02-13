package org.firstinspires.ftc.teamcode.opmode.debug.tuner

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.opmode.template.BaseTemplate
import org.firstinspires.ftc.teamcode.utility.deg

@Config
@TeleOp(name = "Lift PID Tuner", group = "Tuning")
class LiftTuner : BaseTemplate() {
    val lift by lazy { Robot.Subsystems.lift }

    override fun initialize() {
    }

    override fun cycle() {
        lift.target = target

        Robot.Subsystems.intake.claw.manual(frontClawAngle)
        Robot.Subsystems.deposit.claw.manual(backClawAngle)
        Robot.Subsystems.intake.twist(intakeTwist.deg)
        Robot.Subsystems.intake.pitch(intakePitch.deg)
        Robot.Subsystems.intake.dropTo(intakeAngle.deg)
        Robot.Subsystems.deposit.raiseTo(grabSpot.deg)
        Robot.Subsystems.deposit.pitchTo(depositAngle.deg)

        telemetry.addData("power", lift.power)
        telemetry.addData("position", lift.position)
        telemetry.addData("target", lift.target)
    }

    companion object {
        @JvmField
        var target = 2.5

        @JvmField
        var intakePitch = 295.0

        @JvmField
        var intakeAngle = 48.0

        @JvmField
        var grabSpot = 355.0

        @JvmField
        var depositAngle = 7.0

        @JvmField
        var intakeTwist = 0.0

        @JvmField
        var frontClawAngle = 4.5

        @JvmField
        var backClawAngle = 1.5
    }
}
