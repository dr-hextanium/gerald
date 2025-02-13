package org.firstinspires.ftc.teamcode.hardware

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.command.CommandScheduler
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.VoltageSensor
import com.qualcomm.robotcore.util.ElapsedTime
import dev.frozenmilk.dairy.cachinghardware.CachingCRServo
import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.hardware.subsystem.Arm
import org.firstinspires.ftc.teamcode.hardware.subsystem.Claw
import org.firstinspires.ftc.teamcode.hardware.subsystem.Deposit
import org.firstinspires.ftc.teamcode.hardware.subsystem.Diffy
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem
import org.firstinspires.ftc.teamcode.hardware.subsystem.Intake
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift
import org.firstinspires.ftc.teamcode.hardware.subsystem.Turret
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.utility.deg

/**
 * Not proud of this, but it does honestly make things easier in the long run.
 *
 * The idea is you have one source to get all of your hardware from, which
 * translates to having less dependency hell with passing down a `HardwareMap`
 * to all receivers.
 */
object Robot : ISubsystem {
    val scheduler: CommandScheduler
        get() = CommandScheduler.getInstance()

    lateinit var hubs: List<LynxModule>

    lateinit var telemetry: MultipleTelemetry
    lateinit var hw: HardwareMap

    lateinit var gamepad1: GamepadEx
    lateinit var gamepad2: GamepadEx

    lateinit var voltageSensor: Iterator<VoltageSensor>
    var voltageTimer = ElapsedTime()
    var voltage: Double = 0.0

    object Subsystems {
        lateinit var lift: Lift
        lateinit var intake: Intake
        lateinit var deposit: Deposit

        fun all() = listOf(lift, intake, deposit)
    }

    object Motors {
        lateinit var extension: CachingDcMotorEx

        object Lift {
            lateinit var left: CachingDcMotorEx
            lateinit var right: CachingDcMotorEx
            lateinit var encoder: CachingDcMotorEx
        }

        object Drive {
            lateinit var frontRight: CachingDcMotorEx
            lateinit var frontLeft: CachingDcMotorEx
            lateinit var backRight: CachingDcMotorEx
            lateinit var backLeft: CachingDcMotorEx
        }

        fun all() = listOf(
            extension,
            Lift.left,
            Lift.right,
            Lift.encoder,
            Drive.frontRight,
            Drive.frontLeft,
            Drive.backRight,
            Drive.backLeft
        )
    }

    object Servos {
        lateinit var turret: UsefulServo

        object Intake {
            lateinit var left: UsefulServo
            lateinit var right: UsefulServo
        }

        object Diffy {
            lateinit var left: UsefulServo
            lateinit var right: UsefulServo
            lateinit var claw: UsefulServo
        }

        object Deposit {
            lateinit var left: UsefulServo
            lateinit var right: UsefulServo
            lateinit var pivot: UsefulServo
            lateinit var claw: UsefulServo
        }

        object Hang {
            lateinit var left: CachingCRServo
            lateinit var right: CachingCRServo
        }

        fun all() = listOf(
            turret,
            Intake.left,
            Intake.right,
            Diffy.left,
            Diffy.right,
            Diffy.claw,
            Deposit.left,
            Deposit.right,
            Deposit.pivot,
            Deposit.claw,
            Hang.left,
            Hang.right
        )
    }

    fun init(hw: HardwareMap, telemetry: Telemetry, gamepad1: Gamepad, gamepad2: Gamepad) {
        Robot.telemetry = MultipleTelemetry(FtcDashboard.getInstance().telemetry, telemetry)
        Robot.hw = hw

        hubs = hw.getAll(LynxModule::class.java)
        hubs.forEach { it.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL }

        voltageSensor = hw.voltageSensor.iterator()
        voltageTimer.reset()

        Robot.gamepad1 = GamepadEx(gamepad1)
        Robot.gamepad2 = GamepadEx(gamepad2)

        // These `run` call exist so I can collapse these sections.
        run {
            Servos.turret =
                UsefulServo(hw[Names.Servos.turret] as Servo, Bounds.turret, reversed = false)

            Servos.Intake.left =
                UsefulServo(hw[Names.Servos.Arm.left] as Servo, Bounds.Arm.left, reversed = false)
            Servos.Intake.right =
                UsefulServo(hw[Names.Servos.Arm.right] as Servo, Bounds.Arm.right, reversed = true)

            Servos.Diffy.left = UsefulServo(
                hw[Names.Servos.Diffy.left] as Servo,
                Bounds.Diffy.left,
                reversed = true
            )

            Servos.Diffy.right = UsefulServo(
                hw[Names.Servos.Diffy.right] as Servo,
                Bounds.Diffy.right,
                reversed = false
            )

            Servos.Diffy.claw = UsefulServo(
                hw[Names.Servos.Diffy.claw] as Servo,
                Bounds.Diffy.claw,
                reversed = false
            )

            Servos.Deposit.left = UsefulServo(
                hw[Names.Servos.Deposit.left] as Servo,
                Bounds.Deposit.left,
                reversed = true
            )

            Servos.Deposit.right = UsefulServo(
                hw[Names.Servos.Deposit.right] as Servo,
                Bounds.Deposit.right,
                reversed = false
            )

            Servos.Deposit.pivot = UsefulServo(
                hw[Names.Servos.Deposit.pivot] as Servo,
                Bounds.Deposit.pivot,
                reversed = false
            )

            Servos.Deposit.claw = UsefulServo(
                hw[Names.Servos.Deposit.claw] as Servo,
                Bounds.Deposit.claw,
                reversed = false
            )

            Servos.Hang.left = CachingCRServo(
                hw[Names.Servos.Hang.left] as CRServo
            )

            Servos.Hang.right = CachingCRServo(
                hw[Names.Servos.Hang.right] as CRServo
            )
        }

        // Same deal here.
        run {
            Motors.extension = CachingDcMotorEx(hw[Names.Motors.extension] as DcMotorEx)
            Motors.Lift.left = CachingDcMotorEx(hw[Names.Motors.Lift.left] as DcMotorEx)
            Motors.Lift.right = CachingDcMotorEx(hw[Names.Motors.Lift.right] as DcMotorEx)
            Motors.Drive.frontRight = CachingDcMotorEx(hw[Names.Motors.Drive.fr] as DcMotorEx)
            Motors.Drive.frontLeft = CachingDcMotorEx(hw[Names.Motors.Drive.fl] as DcMotorEx)
            Motors.Drive.backRight = CachingDcMotorEx(hw[Names.Motors.Drive.br] as DcMotorEx)
            Motors.Drive.backLeft = CachingDcMotorEx(hw[Names.Motors.Drive.bl] as DcMotorEx)

            // Set reversals in here as well.
            // The ones listed are probably the ones you need to either reverse or unreverse.

            Motors.Lift.left.direction = DcMotorSimple.Direction.REVERSE
            // The drive motors don't need to be reversed here because the class will handle that.
        }

        Subsystems.lift = Lift(Motors.Lift.left, Motors.Lift.right)

        val turret = Turret(Servos.turret, 79.deg)
        val diffy = Diffy(Servos.Diffy.left, Servos.Diffy.right)
        val frontArm = Arm(Servos.Intake.left, Servos.Intake.right)
        val frontClaw = Claw(Servos.Diffy.claw, 0.deg, 0.deg)

        Subsystems.intake = Intake(turret, frontArm, diffy, frontClaw)

        val pivot = Servos.Deposit.pivot
        val claw = Claw(Servos.Deposit.claw, 0.deg, 0.deg)
        val arm = Arm(Servos.Deposit.left, Servos.Deposit.right)

        Subsystems.deposit = Deposit(pivot, arm, claw)

        scheduler.registerSubsystem(*Subsystems.all().toTypedArray())

        reset()
    }

    override fun reset() {
        scheduler.reset()
        Subsystems.all().forEach { it.reset() }
    }

    override fun read() {
        if (voltageTimer.milliseconds() > 500.0 && voltageSensor.hasNext()) {
            voltage = voltageSensor.next().voltage
        }

        Subsystems.all().forEach { it.read() }
    }

    override fun update() {
        scheduler.run()
        Subsystems.all().forEach { it.update() }
    }

    override fun write() {
        Subsystems.all().forEach { it.write() }
    }
}