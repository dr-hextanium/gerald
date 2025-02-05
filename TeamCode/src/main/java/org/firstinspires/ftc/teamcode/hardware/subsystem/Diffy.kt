package org.firstinspires.ftc.teamcode.hardware.subsystem

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServo
import org.firstinspires.ftc.teamcode.hardware.subsystem.Diffy.Kinematics.State
import org.firstinspires.ftc.teamcode.utility.deg

class Diffy(val left: UsefulServo, val right: UsefulServo) : ISubsystem {
    var state = State(0.deg, 0.deg)

    override fun reset() {
        left.bound()
        right.bound()
    }

    override fun read() = Unit

    override fun update() = Unit

    override fun write() {
        val target = Kinematics.inverse(state)

        left.position = target.left
        right.position = target.right
    }

    fun pitch(omega: Double) = to(omega, state.horizontal)
    fun yaw(theta: Double) = to(state.vertical, theta)

    fun to(state: State) = to(state.vertical, state.horizontal)

    fun to(vertical: Double, horizontal: Double) { state = State(vertical, horizontal) }

    object Kinematics {
        data class State(val vertical: Double, val horizontal: Double) {
            operator fun plus(x: State) = State(x.vertical + vertical, x.horizontal + horizontal)
        }

        data class Offset(val left: Double, val right: Double) {
            operator fun plus(x: Offset) = Offset(left + x.left, right + x.right)
        }

        fun vertical(angle: Double) = angle.let { Offset(it, it) }
        fun horizontal(angle: Double) = angle.let { Offset(-it, it) }

        fun inverse(state: State) = vertical(state.vertical) + horizontal(state.horizontal)
    }
}