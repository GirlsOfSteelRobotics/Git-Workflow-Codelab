package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveChassisWithJoystickCommand;
import frc.robot.commands.LiftToPositionCommand;
import frc.robot.commands.LiftWithJoystickCommand;
import frc.robot.commands.MovePunchCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.LiftingSubsystem;
import frc.robot.subsystems.PunchSubsystem;
import frc.robot.subsystems.SpinnyWheelSubsystem;

public class OI {

    private final XboxController m_driverJoystick;
    private final XboxController m_operatorJoystick;

    @SuppressWarnings("PMD.UnusedFormalParameter")
    public OI(ChassisSubsystem chassis, LiftingSubsystem lift, PunchSubsystem punch, SpinnyWheelSubsystem spinnyWheel) {
        m_driverJoystick = new XboxController(0);
        m_operatorJoystick = new XboxController(1);

        // Chassis
        chassis.setDefaultCommand(new DriveChassisWithJoystickCommand(chassis, this));

        // Punch
        new JoystickButton(m_operatorJoystick, XboxController.Button.kA.value).whileHeld(new MovePunchCommand(punch, true));

        // Elevator
        lift.setDefaultCommand(new LiftWithJoystickCommand(lift, this));
        new JoystickButton(m_operatorJoystick, XboxController.Button.kB.value).whileHeld(new LiftToPositionCommand(lift, 0));
        new JoystickButton(m_operatorJoystick, XboxController.Button.kY.value).whileHeld(new LiftToPositionCommand(lift, 40));
        new JoystickButton(m_operatorJoystick, XboxController.Button.kX.value).whileHeld(new LiftToPositionCommand(lift, 60));
    }

    public double getDriverThrottle() {
        // Y is negated so that pushing the joystick forward results in positive values
        return -m_driverJoystick.getY(GenericHID.Hand.kLeft);
    }

    public double getDriverSpin() {
        return m_driverJoystick.getX(GenericHID.Hand.kRight);
    }

    public double getElevatorJoystick() {
        // Y is negated so that pushing the joystick forward results in positive values
        return -m_operatorJoystick.getY(GenericHID.Hand.kRight);
    }

}
