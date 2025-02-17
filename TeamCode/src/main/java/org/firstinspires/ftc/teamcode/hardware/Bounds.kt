package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.Bound
import org.firstinspires.ftc.teamcode.hardware.wrapper.useful.UsefulServoRange
import org.firstinspires.ftc.teamcode.utility.functions.deg

/*
 * Unlike our system last robot, these bounds probably won't ever need to actually change.
 * They just need to reflect the actual range of the servo in the hardware. This allows us to skip
 * past the bound configuring and trying to find extremes and what have you. After specifying them
 * here, you should just be able to put in real angles for every single value.
 */
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

        val claw = UsefulServoRange(
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

        val claw = UsefulServoRange(
            Bound(0.0, 1.0),
            Bound(0.deg, 355.deg),
        )
    }
}