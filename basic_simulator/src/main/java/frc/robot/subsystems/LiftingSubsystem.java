package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SmartDashboardNames;

public class LiftingSubsystem extends SubsystemBase {
    private final SpeedController m_liftMotor;

    public LiftingSubsystem() {
        m_liftMotor = new Talon(Constants.SC_LIFT_MOTOR);
    }

    @Override
    public void periodic() {
        // TODO fill out
        SmartDashboard.putNumber(SmartDashboardNames.LIFT_MOTOR_PERCENTAGE, 0);
    }
}
