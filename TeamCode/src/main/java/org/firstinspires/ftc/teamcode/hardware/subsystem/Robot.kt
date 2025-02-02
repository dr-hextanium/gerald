package org.firstinspires.ftc.teamcode.hardware.subsystem

import dev.frozenmilk.dairy.cachinghardware.CachingDcMotor

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.command.CommandScheduler
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.VoltageSensor
import com.qualcomm.robotcore.util.ElapsedTime
import dev.frozenmilk.dairy.cachinghardware.CachingServo
import org.firstinspires.ftc.robotcore.external.Telemetry

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
        fun all(): List<ISubsystem> = listOf()
    }

    object Motors {
        fun all() = listOf<CachingDcMotor>()
    }

    object Servos {
        fun all() = listOf<CachingServo>()
    }

    fun init(hw: HardwareMap, telemetry: Telemetry, gamepad1: Gamepad, gamepad2: Gamepad) {
        this.telemetry = MultipleTelemetry(FtcDashboard.getInstance().telemetry, telemetry)
        this.hw = hw

        this.hubs = hw.getAll(LynxModule::class.java)

        this.hubs.forEach { it.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL }

        this.voltageSensor = hw.voltageSensor.iterator()
        this.voltageTimer.reset()

        this.gamepad1 = GamepadEx(gamepad1)
        this.gamepad2 = GamepadEx(gamepad2)

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