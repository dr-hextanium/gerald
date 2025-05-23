package org.firstinspires.ftc.teamcode.hardware

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.command.CommandScheduler
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.pedropathing.follower.Follower
import com.qualcomm.hardware.limelightvision.Limelight3A
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
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem
import org.firstinspires.ftc.teamcode.hardware.subsystem.Lift
import org.firstinspires.ftc.teamcode.hardware.subsystem.Vision
import org.firstinspires.ftc.teamcode.hardware.subsystem.deposit.Deposit
import org.firstinspires.ftc.teamcode.hardware.subsystem.intake.Diffy
import org.firstinspires.ftc.teamcode.hardware.subsystem.intake.Extension
import org.firstinspires.ftc.teamcode.hardware.subsystem.intake.Intake
import org.firstinspires.ftc.teamcode.hardware.subsystem.intake.Turret
import org.firstinspires.ftc.teamcode.hardware.subsystem.miscellaneous.Hang
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.pedro.constants.FConstants
import org.firstinspires.ftc.teamcode.pedro.constants.LConstants
import org.firstinspires.ftc.teamcode.utility.functions.deg

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

	lateinit var follower: Follower

	val pose
		get() = follower.pose

	object Subsystems {
		lateinit var lift: Lift
		lateinit var extension: Extension
		lateinit var intake: Intake
		lateinit var deposit: Deposit
		lateinit var hang: Hang
		lateinit var vision: Vision

		fun all() = listOf(lift, intake, hang, deposit, extension, vision)
	}

	object Motors {
		object Extension {
			lateinit var motor: CachingDcMotorEx
			lateinit var encoder: CachingDcMotorEx
		}

		object Lift {
			lateinit var left: CachingDcMotorEx
			lateinit var right: CachingDcMotorEx
			lateinit var encoder: CachingDcMotorEx
		}

		fun all() = listOf(
			Extension.motor,
			Extension.encoder,
			Lift.left,
			Lift.right,
			Lift.encoder,
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
			lateinit var extension: UsefulServo
		}

		object Hang {
			lateinit var left1: CachingCRServo
			lateinit var left2: CachingCRServo
			lateinit var right1: CachingCRServo
			lateinit var right2: CachingCRServo
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
			Hang.left1,
			Hang.left2,
			Hang.right1,
			Hang.right2
		)
	}

	fun init(hw: HardwareMap, telemetry: Telemetry, gamepad1: Gamepad, gamepad2: Gamepad) {
		Robot.telemetry = MultipleTelemetry(FtcDashboard.getInstance().telemetry, telemetry)
		Robot.hw = hw

		Robot.telemetry.msTransmissionInterval = 11


		hubs = hw.getAll(LynxModule::class.java)
		hubs.forEach { it.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL }

		voltageSensor = hw.voltageSensor.iterator()
		voltageTimer.reset()

		Robot.gamepad1 = GamepadEx(gamepad1)
		Robot.gamepad2 = GamepadEx(gamepad2)

		// These `run` call exist so I can collapse these sections.
		run {
			Servos.turret = UsefulServo(
				hw[Names.Servos.turret] as Servo,
				Bounds.turret,
				reversed = false
			)

			Servos.Intake.left = UsefulServo(
				hw[Names.Servos.Arm.left] as Servo,
				Bounds.Arm.left,
				reversed = false
			)

			Servos.Intake.right = UsefulServo(
				hw[Names.Servos.Arm.right] as Servo,
				Bounds.Arm.right,
				reversed = true
			)

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
				hw[Names.Servos.Deposit.arm] as Servo,
				Bounds.Deposit.left,
				reversed = true
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

			Servos.Deposit.extension = UsefulServo(
				hw[Names.Servos.Deposit.extension] as Servo,
				Bounds.Deposit.extension,
				reversed = false
			)

			Servos.Hang.left1 = CachingCRServo(
				hw[Names.Servos.Hang.left1] as CRServo
			)

			Servos.Hang.left2 = CachingCRServo(
				hw[Names.Servos.Hang.left2] as CRServo
			)

			Servos.Hang.right1 = CachingCRServo(
				hw[Names.Servos.Hang.right1] as CRServo
			)

			Servos.Hang.right2 = CachingCRServo(
				hw[Names.Servos.Hang.right2] as CRServo
			)
		}

		// Same deal here.
		run {
			Motors.Extension.motor = CachingDcMotorEx(hw[Names.Motors.Extension.motor] as DcMotorEx)
			Motors.Extension.encoder = CachingDcMotorEx(hw[Names.Motors.Extension.encoder] as DcMotorEx)
			Motors.Lift.left = CachingDcMotorEx(hw[Names.Motors.Lift.left] as DcMotorEx)
			Motors.Lift.right = CachingDcMotorEx(hw[Names.Motors.Lift.right] as DcMotorEx)

			// Set reversals in here as well.
			// The ones listed are probably the ones you need to either reverse or unreverse.

			Motors.Lift.left.direction = DcMotorSimple.Direction.REVERSE
			Motors.Extension.motor.direction = DcMotorSimple.Direction.REVERSE
			Motors.Extension.encoder.direction = DcMotorSimple.Direction.REVERSE
			// The drive motors don't need to be reversed here because the class will handle that.
		}

		follower = Follower(hw, FConstants::class.java, LConstants::class.java)

		val limelight = hw["limelight"] as Limelight3A

		Subsystems.vision = Vision(limelight)
		Subsystems.lift = Lift(Motors.Lift.left, Motors.Lift.right)
		Subsystems.extension = Extension(Motors.Extension.motor, Motors.Extension.encoder)

		val turret = Turret(Servos.turret, 79.deg)
		val diffy = Diffy(Servos.Diffy.left, Servos.Diffy.right)
		val frontArm = Arm(Servos.Intake.left, Servos.Intake.right)
		val frontClaw = Claw(Servos.Diffy.claw, Positions.Intake.Claw.OPEN, Positions.Intake.Claw.CLOSED)

		Subsystems.intake = Intake(turret, frontArm, diffy, frontClaw)

		val pivot = Servos.Deposit.pivot
		val claw = Claw(Servos.Deposit.claw, Positions.Deposit.Claw.OPEN, Positions.Deposit.Claw.CLOSED)
		val arm = Arm(Servos.Deposit.left, null)
		val extensionServo = Servos.Deposit.extension

		Subsystems.deposit = Deposit(pivot, arm, claw, extensionServo)
		Subsystems.hang = Hang(Servos.Hang.left1, Servos.Hang.left2, Servos.Hang.right1, Servos.Hang.right2)

		scheduler.registerSubsystem(*Subsystems.all().toTypedArray())

		reset()
	}

	override fun reset() {
		scheduler.reset()
		Subsystems.all().forEach { it.reset() }
	}

	override fun read() {
		follower.update()

		if (voltageTimer.milliseconds() > 100.0 && voltageSensor.hasNext()) {
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