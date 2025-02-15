package org.firstinspires.ftc.teamcode.command.deposit

import com.arcrobotics.ftclib.command.ConditionalCommand
import org.firstinspires.ftc.teamcode.hardware.Robot

class ToggleDeposit : ConditionalCommand(
	OpenDeposit(),
	CloseDeposit(),
	{ Robot.Subsystems.deposit.claw.position == Robot.Subsystems.deposit.claw.closed }
)