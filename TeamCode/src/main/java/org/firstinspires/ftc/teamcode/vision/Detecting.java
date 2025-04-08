package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.DetectorResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.extension.ExtensionTo;

import java.util.List;
import java.util.Objects;

@TeleOp
public class Detecting extends LinearOpMode {
    private Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.
         */
        limelight.start();

        waitForStart();
        while (opModeIsActive()) {
            double yA = 0.0243753;
            double yB = -0.563117;
            double yC = 11.26297;

            LLResult result = limelight.getLatestResult();

            List<DetectorResult> detections = result.getDetectorResults();

            double minDist = 10000;
            List<List<Double>> minCorners = null;

            double minX = 0;
            double minY = 0;
            for (DetectorResult detection : detections) {
                String className = detection.getClassName(); // What was detected
                double x = detection.getTargetXDegrees(); // Where it is (left-right)
                double y = detection.getTargetYDegrees(); // Where it is (up-down)
                List<List<Double>> corners = detection.getTargetCorners();

                if (Objects.equals(className, "red")) {
                    double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                    if (dist < minDist) {
                        minDist = dist;
                        minCorners = corners;
                        minX = x;
                        minY = y;
                    }
                }
            }

            double distance = yA * Math.pow(minY, 2) + yB * minY + yC;
            telemetry.addData("distance", distance);



            limelight.pipelineSwitch(1);

            double[] inputs = new double[8];
            if (minCorners != null) {
                for (int i = 0; i < minCorners.size(); i++) {
                    for (int j = 0; j < minCorners.get(i).size(); j++) {
                        inputs[i * 2 + j] = minCorners.get(i).get(j);
                    }
                }
            }

            limelight.updatePythonInputs(inputs);
            result = limelight.getLatestResult();



            telemetry.update();

            return;
        }

    }
}