package org.firstinspires.ftc.teamcode.command

import com.arcrobotics.ftclib.command.Command
import com.arcrobotics.ftclib.command.CommandBase
import com.arcrobotics.ftclib.command.Subsystem
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem

abstract class CommandTemplate(vararg val requirements: ISubsystem) : CommandBase(), Command {
    override fun getRequirements(): MutableSet<Subsystem> = requirements.toMutableSet()

    abstract override fun initialize()
    abstract override fun execute()
    abstract override fun isFinished(): Boolean
}