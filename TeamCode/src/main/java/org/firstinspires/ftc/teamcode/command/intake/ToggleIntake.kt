package org.firstinspires.ftc.teamcode.command.intake

import com.arcrobotics.ftclib.command.ConditionalCommand
import org.firstinspires.ftc.teamcode.hardware.Robot

class ToggleIntake : ConditionalCommand(
	OpenIntake(),
	CloseIntake(),
	{ Robot.Subsystems.intake.claw.isClosed() }
)