package cs5004.animator.model;

import java.util.ArrayList;

/**
 * This interface defines the methods that can be performed by the model of the Easy Animator
 * application. The model handles the creation and management of the shapes in the animations and
 * their various motions.
 * @author Zachary Jacobs
 *
 */
public interface AnimatorModel {

  /**
   * Adds a new shape object to the animator model and stores it in an ArrayList.
   * @param s the new shape to add to the animator model
   */
  public void addShape(Shape s);
  
  /**
   * Adds a new animation object to the animator model and stores it in an ArrayList.
   * @param a the new animation to add to the animator model
   */
  public void addAnimation(Animation a);
  
  /**
   * Gets the list of the shapes present in the animator model.
   * @return an ArrayList of the the shapes in the model
   */
  public ArrayList<Shape> getShapes();
  
  /**
   * Gets the list of the animations present in the animator model.
   * @return an ArrayList of the shapes in the model
   */
  public ArrayList<Animation> getAnimations();
  
  /**
   * Gets the dimensions of the animation window as an array of 4 integers. The dimensions are in
   * the order of x coordinate of top left corner, y coordinate of top left corner, width of
   * window, and height of window.
   * @return the animation window's dimensions as an array of integers
   */
  public int[] getCanvasDimensions();
  
  /**
   * Gets the current tick of model.
   * @return the current tick of the model
   */
  public int getCurrentTick();
  
  /**
   * Increments the current tick of the model by 1.
   */
  public void nextTick();
  
  /**
   * Gets the largest tick that is present amongst the animator model's animations to be used
   * to determine the end tick time of the whole animation.
   * @return the largest tick of this model's animations
   */
  public int getLargestTick();
  
  /**
   * Defines the string representation of the Easy Animator model as all the attribute information
   * of the shapes in the animation at creation followed by the animation moves that occur.
   * @return
   */
  public String toString();
  
}
