package org.firstinspires.ftc.teamcode.opmode.debug

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import dev.frozenmilk.dairy.cachinghardware.CachingCRServo
import org.firstinspires.ftc.teamcode.hardware.Names

@TeleOp
class HangTest : OpMode() {
	val left1 by lazy { CachingCRServo(hardwareMap[Names.Servos.Hang.left1] as CRServo) }
	val left2 by lazy { CachingCRServo(hardwareMap[Names.Servos.Hang.left2] as CRServo) }
	val right1 by lazy { CachingCRServo(hardwareMap[Names.Servos.Hang.right1] as CRServo) }
	val right2 by lazy { CachingCRServo(hardwareMap[Names.Servos.Hang.right2] as CRServo) }

	override fun init() { }

	override fun loop() {
		if(gamepad1.dpad_left) {
			left1.power = 1.0
			left2.power = 1.0
			right1.power = -1.0
			right2.power = -1.0
		}
		else if(gamepad1.dpad_right) {
			left1.power = -1.0
			left2.power = -1.0
			right1.power = 1.0
			right2.power = 1.0
		}
		else {
			left1.power = 0.0
			left2.power = 0.0
			right1.power = 0.0
			right2.power = 0.0
		}
	}
}