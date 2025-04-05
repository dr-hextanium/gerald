package org.firstinspires.ftc.teamcode.command.sequences.sample

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import com.qualcomm.hardware.ams.AMSColorSensor.Wait
import org.firstinspires.ftc.teamcode.command.deposit.CloseDeposit
import org.firstinspires.ftc.teamcode.command.deposit.OpenDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.OpenIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.TRANSFER_INTERMEDIATE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.TRANSFER_INTERMEDIATE_PITCH
import org.firstinspires.ftc.teamcode.utility.functions.deg

class BringToTransfer(safe: Boolean = true) : SequentialCommandGroup(
	if (safe) {
		SequentialCommandGroup(
			SwingIntake(TRANSFER_INTERMEDIATE + 50.deg),
			PitchIntake(TRANSFER_INTERMEDIATE_PITCH),
			WaitCommand(250),
			SwingIntake(TRANSFER_INTERMEDIATE),
		)
	} else {
		InstantCommand()
	},
	ExtensionToUntil(-5.0, time = 600),
	OpenDeposit(),
)