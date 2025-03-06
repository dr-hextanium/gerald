package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.CloseDepositSample
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake

class Transfer : SequentialCommandGroup(
	PrepTransfer(),
	WaitCommand(200),
	ExtensionToUntil(0.0),
	CloseDepositSample(),
	WaitCommand(100),
	OpenIntake()
)