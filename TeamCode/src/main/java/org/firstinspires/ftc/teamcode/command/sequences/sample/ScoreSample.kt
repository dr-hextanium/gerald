package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit

class ScoreSample : SequentialCommandGroup(
	OpenDeposit(),
	WaitCommand(150),
	PrepTransfer()
)