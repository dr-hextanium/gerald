package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.utility.functions.deg

object Positions {
    object Intake {
        object Claw {
            val OPEN = 5.0 // degrees
            val CLOSED = 6.0 // degrees

            val INTERMEDIATE_PITCH = 305.deg
            val INTAKE_PITCH = 292.deg
            val ASIDE_PITCH = 250.deg
            val ASIDE_TWIST = (-45).deg
        }

        object Arm {
            val INTERMEDIATE_ANGLE = 60.deg
            val GRAB_ANGLE = 44.deg
            val ASIDE = 75.deg
        }

        object Turret {
            val ASIDE = 215.deg
            val CENTER = 80.deg
        }
    }

    object Deposit {
        object Arm {
            val TRANSFER_SAMPLE = 48.deg
            val GRAB_SPECIMEN = 355.deg
            val SCORE_SPECIMEN = 120.deg
            val SCORE_SAMPLE = 0.deg // TODO
        }

        object Pivot {
            val GRAB_SPECIMEN = 7.deg
            val SCORE_SPECIMEN = 35.deg
            val SCORE_SAMPLE = 0.deg // TODO
        }

        object Claw {
            val OPEN = 1.5 // degrees
            val CLOSED = 0.0 // degrees
        }
    }

    object Lift {
        const val GRAB_SPECIMEN = 2.5 // inches
        const val CLEARANCE = 7.0 // inches
        const val HIGH_CHAMBER = 16.0 // inches
        const val HIGH_BASKET = 0.0 // inches, TODO
    }

    object Extension {
        const val MAX = 18.0 // inches, actually 18.5 but for safety!!
        const val HALF = MAX / 2.0
        const val ZERO = 0.0
    }
}