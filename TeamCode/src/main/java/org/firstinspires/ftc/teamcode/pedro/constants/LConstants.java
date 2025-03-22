package org.firstinspires.ftc.teamcode.pedro.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        PinpointConstants.distanceUnit = DistanceUnit.INCH;
        PinpointConstants.hardwareMapName = "pp";
        PinpointConstants.forwardY = -(1.0 + (1.0 / 2.0));
        PinpointConstants.strafeX = -(5.0 + 0.0);
        PinpointConstants.yawScalar = 1.0;

        PinpointConstants.encoderResolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    }
}




