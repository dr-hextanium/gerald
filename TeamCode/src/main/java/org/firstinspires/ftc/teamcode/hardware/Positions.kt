package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.utility.functions.deg

object Positions {
    object Intake {
        object Claw {
            val OPEN = 5.0 // degrees
            val CLOSED = 6.0 // degrees

            val INTERMEDIATE_PITCH = 325.deg
            val INTAKE_PITCH = 300.deg

            val ASIDE_PITCH = 250.deg
            val ASIDE_TWIST = (-45).deg
            val TUCK_PITCH = 355.0.deg

            val TRANSFER_INTERMEDIATE_PITCH = 90.deg

            val TRANSFER_SAMPLE_PITCH = 225.deg
        }

        object Arm {
            val GRAB_ANGLE = 40.deg

            val INTERMEDIATE_ANGLE = 65.deg
            val ASIDE = 75.deg
            val TUCK = 115.deg

            val TRANSFER_SAMPLE = 140.deg
            val TRANSFER_INTERMEDIATE = 40.deg
            val TRANSFER_INTERMEDIATE_SQUARED = ((TRANSFER_SAMPLE + TRANSFER_INTERMEDIATE) / 2.0) + 10.deg
        }

        object Turret {
            val ASIDE = 215.deg
            val CENTER = 115.deg
            val TUCK = 200.deg
        }
    }

    object Deposit {
        object Arm {
            val GRAB_SPECIMEN = 355.deg
            val SCORE_SPECIMEN = 10.deg

            val TRANSFER_SAMPLE = 45.deg
            val SCORE_SAMPLE = 150.deg
        }

        object Pivot {
            val GRAB_SPECIMEN = 355.deg
            val SCORE_SPECIMEN = 300.deg// 335.deg

            val SCORE_SAMPLE = 190.deg
            val TRANSFER_SAMPLE = 305.deg
        }

        object Claw {
            val OPEN = 2.75 // degrees
            val CLOSED =  0.15 // degrees
            val CLOSED_SAMPLE = 1.65 // degrees
        }

        object Extension {
            val RETRACTED = 180.deg
            val EXTENDED = 90.deg
        }
    }

    object Lift {
        const val GRAB_SPECIMEN = 1.5 // 2.0 inches
        const val HIGH_CHAMBER = 16.0 // inches //16.0 inches
        const val HIGH_BASKET = 22.0 // inches, TODO
    }

    object Extension {
        const val MAX = 18.0 // inches, actually 18.5 but for safety!!
        const val HALF = MAX / 2.0
        const val ZERO = 0.0
    }
}