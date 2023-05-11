package cs5004.animator.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import cs5004.animator.model.Animation;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.Shape;
import cs5004.animator.view.AnimatorView;
import cs5004.animator.view.AnimatorViewFactory;

/**
 * This class contains the implementation of the AnimatorController interface used to connect the
 * animator model and view to produce an animation. The animator controller is passed the user's
 * command line arguments from the main program in order to get the animation txt input file,
 * the output of the animation, the animator view type to be used, and the animation
 * ticks per second for the animation.
 * @author Zachary Jacobs
 *
 */
public class AnimatorControllerImpl implements AnimatorController, KeyListener {

  private AnimatorModel model;
  private AnimatorView view = null;
  private String viewName = "";
  private String inputName = "";
  private String outputName = "System.out"; //default
  private Readable input = null;
  private Appendable output = System.out;
  private int tickSpeed = 10; //default
  
  private boolean pause = false; //used in playback to pause animation 
  
  private String[] viewTypes = {"text", "svg", "visual", "playback"};
  
  /**
   * Instantiates a new AnimatorControllerImpl object with given AnimatorModel and string array
   * containing the command line arguments passed to main. The command line arguments are used by
   * the readCmdArgs method to determine the user specified behavior of the Easy Animator
   * application and initializes the view type and behavior. A input file and view type must be
   * given when running the application and if output and speed are not given they will default to
   * System.out and 10 ticks per second respectively.
   * @param model the AnimatorModel containing the shapes and animations of the animation
   * @param cmdArgs the command line arguments given to main containing the user desired 
   *     application behavior
   * @throws IllegalArgumentException if the readCmdArgs method is passed invalid command line
   *     arguments during instantiation
   */
  public AnimatorControllerImpl(AnimatorModel model, String[] cmdArgs)
      throws IllegalArgumentException {
    this.model = model;
    this.readCmdArgs(cmdArgs);
  }
  
  @Override
  public void readCmdArgs(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      //Input file
      if (args[i].equals("-in")) {
        if (args[i + 1].endsWith(".txt")) { 
          try {
            this.input = new FileReader(args[i + 1]);
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Input file not found",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Input file not found");
          }
        }
        else {
          JOptionPane.showMessageDialog(null, "Input must be a .txt file",
              "ERROR", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Input must be a .txt file");
        }
        this.inputName = args[i + 1];
      }
      
      //View type
      else if (args[i].equals("-view")) {
        if (!Arrays.stream(this.viewTypes).anyMatch(args[i + 1]::equals)) {
          JOptionPane.showMessageDialog(null, "View must be text, svg, or visual",
              "ERROR", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("View must be text, svg, or visual");
        }
        this.viewName = args[i + 1];
      }
      
      //Output file
      else if (args[i].equals("-out")) {
        try {
          this.output = new FileWriter(args[i + 1]);
        } 
        catch (IOException e) {
          JOptionPane.showMessageDialog(null, "Could not output to " + args[i + 1],
              "ERROR", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Could not output to " + args[i + 1]);
        }
        this.outputName = args[i + 1];
      }
      
      //tick speed
      else if (args[i].equals("-speed")) {
        try {
          this.tickSpeed = Integer.parseInt(args[i + 1]);
        }
        catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, "Speed must be an integer",
              "ERROR", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Speed must be an integer");
        }
        if (this.tickSpeed <= 0) {
          JOptionPane.showMessageDialog(null, "Speed must be greater than zero",
              "ERROR", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Speed must be greater than zero");
        }
      }
    }
    //Check that an input file has been provided
    if (this.input == null) {
      JOptionPane.showMessageDialog(null, "Input file must be provided",
          "ERROR", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Input file must be provided");
    }
    
    //Check that a view type has been provided
    if (this.viewName.equals("")) {
      JOptionPane.showMessageDialog(null, "View type must be provided",
          "ERROR", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("View type must be provided");
    }
    
    //Check if an output file has been provided
    if (!this.outputName.equals("System.out")) {
      
      //If text view check that the output file ends with .txt
      if (this.viewName.equals("text") && !this.outputName.endsWith(".txt")) {
        JOptionPane.showMessageDialog(null, "text view output file must be a .txt file",
            "ERROR", JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("text view output file must be a .txt file");
      }
      //If svg view check that the output file ends with .svg
      if (this.viewName.equals("svg") && !this.outputName.endsWith(".svg")) {
        JOptionPane.showMessageDialog(null, "svg view output file must be a .svg file",
            "ERROR", JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("svg view output file must be a .svg file");
      }
    }
    
  }
  
  @Override
  public void runAnimation() {
    if (this.viewName.equals("text") || this.viewName.equals("svg")) {
      view.display(model.getShapes(), model.getAnimations());
    }
    else {
      //Send the animation tick by tick to be displayed
      while (model.getCurrentTick() <= model.getLargestTick()) {
        
        //Pause utilized by playback view. Toggle between true for paused and false for play
        if (!pause) {
        
          //Get shapes that are currently visible to be displayed
          ArrayList<Shape> displayedShapes = new ArrayList<Shape>();
          for (Shape s: model.getShapes()) {
            if (s.getAppearTick() <= model.getCurrentTick()
                && model.getCurrentTick() <= s.getDisappearTick() ) {
              displayedShapes.add(s);
            }
          }
          
          //Send shapes to the view to display
          view.display(displayedShapes, null);
          
          //Advance the model tick by 1
          model.nextTick();
          
          //Perform the animations that are taking place at the current model tick
          for (Animation a: model.getAnimations()) {
            if (a.getStartTick() <= model.getCurrentTick()
                && a.getEndTick() >= model.getCurrentTick()) {
              a.animateShape(model.getCurrentTick());
            }
          }
         
          //Wait for the user specified tick speed
          try {
            Thread.sleep(1000 / this.tickSpeed);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    
  }

  @Override
  public Readable getInputFile() {
    return this.input;
  }

  @Override
  public void initializeView() {
    this.view = AnimatorViewFactory.createView(this.viewName, this.output,
        model.getCanvasDimensions(), this);
  }
  
  /**
   * Defines the string representation of an AnimatorControllerImpl as a string of its input name,
   * view name, its output name and its tick speed. Used for testing purposes only.
   */
  @Override
  public String toString() {
    return this.inputName + " " + this.viewName + " " + this.outputName + " " + this.tickSpeed;
  }

  /**
   * Reacts to keyboard input from the user when the animation is in playback view.
   * Pressing space pauses the animation, pressing z slows down the animation and
   * pressing x speeds up the animation.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == ' ') {
      if (pause) {
        pause = false;
      }
      else {
        pause = true;
      }
    }
    else if (e.getKeyChar() == 'z') {
      if (this.tickSpeed - 5 > 1) {
        this.tickSpeed -= 5;
      }
    }
    else if (e.getKeyChar() == 'x') {
      this.tickSpeed += 5;
    }
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    //no action
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //no action
  }
}
