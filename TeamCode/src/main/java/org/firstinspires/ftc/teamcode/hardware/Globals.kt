package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D

object Globals {
    var AUTO = false
    var TELEOP_AUTO = false
    var HEADING_LOCK = false

    var target = Pose2D(0.0, 0.0, 0.0)
    var da = 0.0
    var dx = 0.0
    var dy = 0.0
}