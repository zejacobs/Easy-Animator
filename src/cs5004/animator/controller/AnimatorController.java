package cs5004.animator.controller;

/**
 * This interface defines the methods that should be implemented by the controller of the
 * Easy Animator Application. The controller works to pass data to and from the model and view
 * to produce the user specified animation.
 * @author Zachary Jacobs
 *
 */
public interface AnimatorController {

  /**
   * Runs the Easy Animator program using the animator model the controller was initialized with
   * and user specified view type.
   * to produce an animation.
   */
  public void runAnimation();
  
  /**
   * Reads the command line arguments that are passed to it from the main program to set the input
   * file, view type, output file and tick speed of the AnimatorController. An input file and view
   * type must provided for the Easy Animator application to run. Output will default to System.out
   * and tick speed will default to 10 tick per second if arguments for these fields are not
   * provided in the command line.
   * @param args the command line arguments passed from the main program
   * @throws IllegalArgumentException if an input and/or view type are not specified in the command
   *     line arguments, if the passed input file cannot be found or is not a .txt file, if the
   *     passed view type is invalid, if the passed output file is not a .txt file and the passed
   *     view type is text, if the passed output file is not a .svg file and the passed view type
   *     is svg, and if the passed tick speed is not an integer greater than zero
   */
  public void readCmdArgs(String[] args) throws IllegalArgumentException;
  
  /**
   * Gets the input file for the animation passed in by the user. Used to give the AnimationReader
   * the file to read.
   * @return the input file for the animation
   */
  public Readable getInputFile();
  
  /**
   * Initializes a new animator view for the controller to pass animation model information to use.
   */
  public void initializeView();

}
