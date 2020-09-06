/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.AutonFactory;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.LiftingSubsystem;
import frc.robot.subsystems.PunchSubsystem;
import frc.robot.subsystems.SpinnyWheelSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
@SuppressWarnings("PMD.SingularField")
public class RobotContainer {
    // Subsystems
    private final ChassisSubsystem m_chassisSubsystem;
    private final LiftingSubsystem m_liftingSubsystem;
    private final PunchSubsystem m_punchSubsystem;
    private final SpinnyWheelSubsystem m_spinnyWheelSubsystem;

    private final AutonFactory m_autonFactory;

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        m_chassisSubsystem = new ChassisSubsystem();
        m_liftingSubsystem = new LiftingSubsystem();
        m_punchSubsystem = new PunchSubsystem();
        m_spinnyWheelSubsystem = new SpinnyWheelSubsystem();

        new OI(m_chassisSubsystem, m_liftingSubsystem, m_punchSubsystem, m_spinnyWheelSubsystem);

        m_autonFactory = new AutonFactory();

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return m_autonFactory.getAutonMode();
    }

    public LiftingSubsystem getLift() {
        return m_liftingSubsystem;
    }

    public ChassisSubsystem getChassis() {
        return m_chassisSubsystem;
    }
}
