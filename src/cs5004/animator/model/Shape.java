package cs5004.animator.model;

/**
 * This interface defines all of the methods that a shape in the Easy Animator application
 * should support. A shape is capable of setting and getting its attributes.
 * @author Zachary Jacobs
 *
 */
public interface Shape {

  /**
   * Sets this shape's reference x, y coordinates to the passed x and y values.
   * @param x the value to set this shape's x coordinate to 
   * @param y the value to set this shape's y coordinate to
   */
  public void setReference(double x, double y);
  
  /**
   * Sets this shape's color to the color defined by the passed RGB values.
   * @param r the first digit of this shape's RGB color value to change to
   * @param g the second digit of this shape's RGB color value to change to
   * @param b the third digit of this shape's RGB color value to change to
   */
  public void setColor(double r, double g, double b);
  
  /**
   * Sets this shape's x-axis dimension to the passed value.
   * @param xValue the value to change this shape's x-axis dimension to
   */
  public void setDimensionX(double xValue);
  
  /**
   * Sets this shape's y-axis dimension to the passed value.
   * @param yValue the value to change this shape's y-axis dimension to
   */
  public void setDimensionY(double yValue);
  
  /**
   * Sets this shape's appear tick to the passed value.
   */
  public void setAppearTick(int tick);
  
  /**
   * Sets this shape's disappear tick to the passed value.
   */
  public void setDisappearTick(int tick);
  
  /**
   * Gets the x value of the (x,y) coordinate for the reference point of this shape.
   * @return the x value of the (x,y) reference coordinate of this shape
   */
  public double getReferenceX();
  
  /**
   * Gets the y value of the (x,y) coordinate for the reference point of this shape.
   * @return the y value of the (x,y) reference coordinate of this shape
   */
  public double getReferenceY();
  
  /**
   * Gets the value of the x-axis dimension of this shape.
   * @return the x-axis dimension of this shape
   */
  public double getDimensionX();
  
  /**
   * Gets the value of the y-axis dimension of this shape.
   * @return the y-axis dimension of this shape
   */
  public double getDimensionY();
  
  /**
   * Gets the RGB color value of this shape and returns as an array of doubles.
   * @return the RGB color value of this shape
   */
  public double[] getColor();
  
  /**
   * Get the appear tick of this shape.
   * @return the appear tick of this shape
   */
  public int getAppearTick();
  
  /**
   * Get the disappear tick of this shape.
   */
  public int getDisappearTick();
  
  /**
   * Gets the name of this shape.
   * @return the name of this shape
   */
  public String getName();
  
  /**
   * Defines the string representation of a rectangle as a text summary of its attributes.
   * @return the string summary of this shape's attributes
   */
  public String toString();
  
}
