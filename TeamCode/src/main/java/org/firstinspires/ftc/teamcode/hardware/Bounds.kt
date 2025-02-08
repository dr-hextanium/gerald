package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.Bound
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServoRange
import org.firstinspires.ftc.teamcode.utility.deg

object Bounds {
    val turret = UsefulServoRange(
        Bound(0.0, 1.0),
        Bound(0.deg, 270.deg),
    )

    object Arm {
        val left = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )

        val right = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )
    }

    object Diffy {
        val left = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )

        val right = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )
    }

    object Deposit {
        val left = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )

        val right = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )

        val pivot = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )
    }
}