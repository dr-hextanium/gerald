package org.firstinspires.ftc.teamcode.pedro.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.Names;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = Names.Motors.Drive.fl;
        FollowerConstants.leftRearMotorName = Names.Motors.Drive.bl;
        FollowerConstants.rightFrontMotorName = Names.Motors.Drive.fr;
        FollowerConstants.rightRearMotorName = Names.Motors.Drive.br;

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 10.0;

        FollowerConstants.xMovement = 78.0; // positive
        FollowerConstants.yMovement = 57.8; // positive

        FollowerConstants.forwardZeroPowerAcceleration = -39.58; // negative
        FollowerConstants.lateralZeroPowerAcceleration = -78.62; // negative

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0);
//        FollowerConstants.useSecondaryTranslationalPID = false;
//        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(2,0,0.1,0);
//        FollowerConstants.useSecondaryHeadingPID = false;
//        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.007,0,0,0.6,0);
//        FollowerConstants.useSecondaryDrivePID = false;
//        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 2;
        FollowerConstants.centripetalScaling = 0.00005;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
