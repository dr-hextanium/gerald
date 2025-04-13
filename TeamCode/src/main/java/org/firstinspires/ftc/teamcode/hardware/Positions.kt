package org.firstinspires.ftc.teamcode.hardware

import org.firstinspires.ftc.teamcode.utility.functions.deg

object Positions {
    object Intake {
        object Claw {
            val OPEN = 5.0 // degrees
            val CLOSED = 6.0 //5.94 // degrees

            val INTERMEDIATE_PITCH = 325.deg
            val INTAKE_PITCH = 300.deg

            val ASIDE_PITCH = 250.deg
            val ASIDE_TWIST = (-45).deg
            val TUCK_PITCH = 355.0.deg

//            val TRANSFER_INTERMEDIATE_PITCH = 90.deg
//
//            val TRANSFER_SAMPLE_PITCH = 300.deg

            val TRANSFER_INTERMEDIATE_PITCH = 90.deg

            val TRANSFER_SAMPLE_PITCH = 220.deg
        }

        object Arm {
            val GRAB_ANGLE = 40.deg

            val INTERMEDIATE_ANGLE = 65.deg
            val ASIDE = 75.deg
            val TUCK = 115.deg

            //            val TRANSFER_SAMPLE = 155.deg
//            val TRANSFER_INTERMEDIATE = 40.deg
//            val TRANSFER_INTERMEDIATE_SQUARED = ((TRANSFER_SAMPLE + TRANSFER_INTERMEDIATE) / 2.0) + 10.deg
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
            val SCORE_SPECIMEN = 0.deg

//            val TRANSFER_INTERMEDIATE = 70.deg
//            val TRANSFER_SAMPLE = 20.deg
//            val SCORE_SAMPLE = 135.deg

            val EXTENDED_GRAB = 290.deg

            val TRANSFER_SAMPLE = 7.deg//45.deg
            val SCORE_SAMPLE = 110.deg//150.deg
        }

        object Pivot {
            val GRAB_SPECIMEN = 355.deg
            val SCORE_SPECIMEN = 330.deg// 335.deg

//            val SCORE_SAMPLE = 170.deg
//            val TRANSFER_SAMPLE = 355.deg

            val EXTENDED_GRAB = 325.deg

            val SCORE_SAMPLE = 140.deg
            val TRANSFER_SAMPLE = 290.deg//290.deg
        }

        object Claw {
            val OPEN = 2.75 // degrees
            val CLOSED =  0.15 // degrees
            val CLOSED_SAMPLE = 1.65 // degrees
        }

        object Extension {
            val RETRACTED = 180.deg
            val EXTENDED = 90.deg
            val TRANSFER_EXTENSION = 114.deg
        }
    }

    object Lift {
        const val GRAB_SPECIMEN = 0.7 // 2.0 inches
        const val HIGH_CHAMBER = 16.5 // inches //16.0 inches
        const val HIGH_BASKET = 20.0 // inches, TODO
    }

    object Extension {
        const val MAX = 18.0 // inches, actually 18.5 but for safety!!
        const val HALF = MAX / 2.0
        const val ZERO = 0.0
    }
}