// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.DriveConstants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoBalancePID extends ProfiledPIDCommand {
  /** Creates a new AutoBalancePID. */
  public AutoBalancePID(DriveSubsystem drive) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            DriveConstants.kBalanceP,
            DriveConstants.kBalanceI,
            DriveConstants.kBalanceD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(0, 0)),
        // This should return the measurement
        drive::getRoll,
        // This should return the goal (can also be a constant)
        DriveConstants.kLevel,
        // This uses the output
        (output, setpoint) -> drive.arcadeDrive(-output, 0),
        drive
        );
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  getController()
      .setTolerance(DriveConstants.kBalanceToleranceDeg, DriveConstants.kTurnRateToleranceDegPerS);
  }
  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
