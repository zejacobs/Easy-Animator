package cs5004.animator.model;

/**
 * This interface defines the methods that an Animation object that is part of the
 * Easy Animator application will implement.
 * @author Zachary Jacobs
 *
 */
public interface Animation {

  /**
   * Perform animation on the shape associated with this animation by adjusting the appropriate
   * shape attributes to the tweening value for the current model tick.
   * ((x,y) for Move, x-axis/y-axis dimensions for Resize, and RGB color values for ChangeColor)
   * @param currentTick the current tick of the model
   */
  public void animateShape(int currentTick);
  
  /**
   * Gets the type of animation that this animation is ("Move", "ChangeColor" "Resize").
   * @return the string of the animation type
   */
  public String getType();
  
  /**
   * Gets the name of the shape that is associated with this animation.
   * @return the name of the shape associated with this animation
   */
  public String getShapeName();
  
  /**
   * Gets the tick that this animation starts on.
   * @return the start tick of the this animation
   */
  public int getStartTick();
  
  /**
   * Gets the tick that this animation finishes on.
   * @return the end tick of this animation
   */
  public int getEndTick();
  
  /**
   * Defines the string representation of this animation as a summary of what animation type is
   * being done to which shape and what attributes are changing.
   * @return the string representation of this animation 
   */
  public String toString();
}
