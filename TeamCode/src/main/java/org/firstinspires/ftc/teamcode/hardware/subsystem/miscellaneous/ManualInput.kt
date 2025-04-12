package org.firstinspires.ftc.teamcode.hardware.subsystem.miscellaneous

import com.pedropathing.pathgen.PathChain
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.subsystem.ISubsystem

class ManualInput : ISubsystem {
    var x = 0.0
    var y = 0.0
    var angle = 0.0

    override fun reset() = Unit
    override fun read() = Unit
    override fun update() = Unit



    fun path(): Unit {
        val dx = x - (AGAINST_THE_WALL_X + Positions.Extension.MAX)
//        val dy = (y).coer
    }

    override fun write() {
        Robot.telemetry.addData("Sample Position", "{ x: $x, y: $y, angle: $angle }")
    }

    companion object {
        const val AGAINST_THE_WALL_X = 43.25 // inches
        const val SUB_LEFT_Y_BOUNDARY = 86.5 // inches
        const val SUB_RIGHT_Y_BOUNDARY = 57.5 // inches
    }
}