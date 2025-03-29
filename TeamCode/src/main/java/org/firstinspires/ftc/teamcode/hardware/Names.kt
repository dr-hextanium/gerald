package org.firstinspires.ftc.teamcode.hardware

object Names {
    object I2C {
        const val otos = "otos"
    }

    object Servos {
        const val turret = "t"

        object Arm {
            const val left = "la"
            const val right = "ra"
        }

        object Diffy {
            const val left = "ldif"
            const val right = "rdif"
            const val claw = "cdif"
        }

        object Deposit {
            const val arm = "adep"
            const val pivot = "pdep"
            const val claw = "cdep"
            const val extension = "edep"
        }

        object Hang {
            const val left1 = "lhang1"
            const val left2 = "lhang2"
            const val right1 = "rhang1"
            const val right2 = "rhang2"
        }
    }

    object Motors {
        object Extension {
            const val motor = "e"
            const val encoder = "e enc"
        }

        object Lift {
            const val left = "ll"
            const val right = "rl"
        }

        object Drive {
            const val fr = "fr"
            const val fl = "fl"
            const val br = "br"
            const val bl = "bl"
        }
    }
}