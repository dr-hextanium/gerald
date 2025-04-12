package org.firstinspires.ftc.teamcode.hardware.wrapper

import com.arcrobotics.ftclib.command.button.Trigger
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys


class GamepadTrigger(private val m_gamepad: GamepadEx, val threshold: Double = 0.3, vararg triggers: GamepadKeys.Trigger) : Trigger() {
	private val m_triggers: Array<GamepadKeys.Trigger> = triggers as Array<GamepadKeys.Trigger>

	override fun get(): Boolean {
		var res = true
		for (trigger in m_triggers) res = res && m_gamepad.getTrigger(trigger) > threshold
		return res
	}
}