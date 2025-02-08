package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.Bound
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServoRange
import org.firstinspires.ftc.teamcode.utility.deg

object Bounds {
    val turret = UsefulServoRange(
        Bound(0.0, 1.0),
        Bound(0.deg, 270.deg),
    )
}