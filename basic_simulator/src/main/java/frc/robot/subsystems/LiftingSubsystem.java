package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SmartDashboardNames;

public class LiftingSubsystem extends SubsystemBase {
    public static final double ALLOWABLE_POSITION_ERROR = .25;
    private static final double KP = .2;

    private final CANSparkMax m_liftMotor;
    private final CANEncoder m_liftEncoder;

    public LiftingSubsystem() {
        m_liftMotor = new CANSparkMax(Constants.CAN_LIFT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushed);
        m_liftEncoder = m_liftMotor.getEncoder();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber(SmartDashboardNames.LIFTING_MOTOR_SPEED, m_liftMotor.get());
        SmartDashboard.putNumber(SmartDashboardNames.LIFTING_HEIGHT, getHeight());
    }

    public boolean goToPosition(double position) {
        double currentPosition = getHeight();
        double error = position - currentPosition;

        double speed = error * KP;

        setSpeed(speed);

        return Math.abs(error) < ALLOWABLE_POSITION_ERROR;
    }

    public void setSpeed(double speed) {
        m_liftMotor.set(speed);
    }

    public double getHeight() {
        return m_liftEncoder.getPosition();
    }
}
