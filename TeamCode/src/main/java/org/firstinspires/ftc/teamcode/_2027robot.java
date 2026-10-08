package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp(name ="_2027robot (Blocks to Java)")
public class _2027robot extends LinearOpMode {
private DcMotor FrontLeftMotor;
    private DcMotor FrontRightMotor;
    private DcMotor BackLeftMotor;
    private DcMotor BackRightMotor;
    private DcMotor bobTheIntake;

    double RightstickY;
    double RightstickX;
    double LeftstickX;
    double LeftstickY;

    @Override
    public void runOpMode() {
        FrontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        FrontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        BackLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        BackRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        bobTheIntake = hardwareMap.dcMotor.get("intake");

        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();


        if (opModeIsActive()){
            while(opModeIsActive()){
                LeftstickY = gamepad1.left_stick_y;
                RightstickY = gamepad1.right_stick_y;
                LeftstickX = gamepad1.left_stick_x;
                RightstickX = gamepad1.right_stick_x;
//                NWDRIVE();
//                arcadedrive();
//                cArcade();
                ifButtonPressed();
                mecanum();
            }
        }
    }
    public void NWDRIVE() {
        //NWDRIVE is short for Normal Wheel Drive, this means the code is meant for normal tires instead of mechanum wheels or something like that.
        double LeftStick = gamepad1.left_stick_y;
        double RightStick = gamepad1.right_stick_y;

        FrontLeftMotor.setPower(LeftStick);
        FrontRightMotor.setPower(RightStick);
        BackLeftMotor.setPower(LeftStick);
        BackRightMotor.setPower(RightStick);
    }

    public void arcadedrive(){
                double RightStickX = gamepad1.right_stick_x;
                double RightStickY = gamepad1.right_stick_y;
                double left;
                double right;
                if(RightStickY > 0){
                    left = RightStickY + RightStickX;
                    right = RightStickX - RightStickY;
                }

                else if(RightStickY <= 0){
                    left = RightStickY - RightStickX;
                    right = RightStickX + RightStickY;
                }
                else{
                    left = 0;
                    right = 0;
                }

                FrontLeftMotor.setPower(left);
                BackLeftMotor.setPower(left);
                FrontRightMotor.setPower(right);
                BackRightMotor.setPower(right);
        }
        public void cArcade(){
            double turn = gamepad1.right_stick_x;
            double drive = -gamepad1.right_stick_y;
            double left = drive + turn;
            double right = drive - turn;


            FrontLeftMotor.setPower(left);
            FrontRightMotor.setPower(right);
            BackLeftMotor.setPower(left);
            BackRightMotor.setPower(right);
        }
        public void  ifButtonPressed(){
            if(gamepad1.bWasPressed()) {
                bobTheIntake.setPower(-0.8);
            }
            else if(gamepad1.bWasReleased()) {
                bobTheIntake.setPower(0);
            }
        }
        public void mecanum(){
            double frontleft = RightstickY - RightstickX - LeftstickX; // + +
            double backleft = RightstickY + RightstickX - LeftstickX; // - +
            double backright = RightstickY - RightstickX + LeftstickX; // - -
            double frontright = RightstickY + RightstickX + LeftstickX; //+ -

            FrontLeftMotor.setPower(frontleft);
            FrontRightMotor.setPower(frontright);
            BackLeftMotor.setPower(backleft);
            BackRightMotor.setPower(backright);
        }
}
