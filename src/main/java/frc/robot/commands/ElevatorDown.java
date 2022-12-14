// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.PneumaticsSub;

public class ElevatorDown extends CommandBase {
  /** Creates a new ElevatorCMD. */
  private final ElevatorSub elevatorSub;
  private final PneumaticsSub pneumSub;
  public ElevatorDown(ElevatorSub elevatorSubsystem, PneumaticsSub pneumaticsSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    elevatorSub = elevatorSubsystem;
    pneumSub = pneumaticsSubsystem;
    addRequirements(elevatorSub);
    addRequirements(pneumSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    pneumSub.climbLockChange(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevatorSub.elevatorDown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevatorSub.stopElevator();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // || or - ! not - && and
    if(!elevatorSub.getUpLimitSwitch()){ //It's pressed
      pneumSub.climbLockChange(false);
      return(true);
    }
    else{
      return false;
    }
  }
}
