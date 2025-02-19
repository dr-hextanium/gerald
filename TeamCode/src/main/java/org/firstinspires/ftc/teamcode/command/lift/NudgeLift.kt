package org.firstinspires.ftc.teamcode.command.lift

import com.arcrobotics.ftclib.command.InstantCommand
import org.firstinspires.ftc.teamcode.hardware.Robot

class NudgeLift(amount: Double) : InstantCommand({ Robot.Subsystems.lift.nudge(amount) })
class AltNudgeLift(amount: Double) : InstantCommand({ Robot.Subsystems.altLift.nudge(amount) })
