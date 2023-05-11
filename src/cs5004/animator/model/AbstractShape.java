package cs5004.animator.model;

/**
 * This abstract class implements the Shape Interface methods that have the same behavior amongst
 * all specific Shape subclasses (Rectangle and Ellipse). This class implements setters and getters
 * for the shape's attributes.
 * @author Zachary Jacobs
 *
 */
public abstract class AbstractShape implements Shape {
  
  protected final String name; // name of the shape
  protected double xRef; // x coordinate of the shape's reference point
  protected double yRef; // y coordinate of the shape's reference point
  protected double xDim; // x-axis dimension of this shape
  protected double yDim; // y-axis dimension of this shape
  protected double[] color; // array of this shapes RGB color values
  protected int appearTick; // time tick this shape appears
  protected int disappearTick; // time tick this shape disappears
  
  /**
   * Super constructor used by shape subclasses to instantiate a shape with all of the
   * information relative to its name, reference point, x-axis and y-axis dimensions, color, and
   * appear and disappear tick times.
   * @name the unique name of this shape
   * @param xRef the x coordinate of the reference point of this shape
   * @param yRef the y coordinate of the reference point of this shape
   * @param xDim the x-axis dimension of this shape
   * @param yDim the y-axis dimension of this shape
   * @param r the red value of this shape's RGB color value
   * @param g the green value of this shape's RGB color value
   * @param b the blue value of this shape's RGB color value
   * @param appearTick the tick this shape appears at
   * @param disappearTick the tick this shape disappears at
   * @throws IllegalArgumentException if the passed x-axis dimension or y-axis dimension are
   *     less than 1, if the passed appear tick or disappear tick are less than 1, if appear tick
   *     is greater than disappear tick or if the any of the passed RBG values are less than 0 or
   *     greater than 255
   */
  public AbstractShape(String name, double xRef, double yRef, double xDim, double yDim,
      double r, double g, double b, int appearTick, int disappearTick)
          throws IllegalArgumentException {
    if (xDim < 1 || yDim < 1) {
      throw new IllegalArgumentException("Shape dimentions must be greater than zero");
    }
    if (appearTick < 1 || disappearTick < 1) {
      throw new IllegalArgumentException("Tick times must be greater than zero");
    }
    if (appearTick > disappearTick) {
      throw new IllegalArgumentException("Appear tick cannot be greater than disappear tick");
    }
    if (r < 0 || r > 255 || g < 0 | g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB values must be between 0-255");
    }
    this.name = name;
    this.xRef = xRef;
    this.yRef = yRef;
    this.xDim = xDim;
    this.yDim = yDim;
    this.color = new double[] {r, g, b};
    this.appearTick = appearTick;
    this.disappearTick = disappearTick;
  }
  
  @Override
  public void setReference(double x, double y) {
    this.xRef = x;
    this.yRef = y;
  }

  @Override
  public void setColor(double r, double g, double b) {
    this.color[0] = r;
    this.color[1] = g;
    this.color[2] = b;
  }

  @Override
  public void setDimensionX(double xValue) {
    this.xDim = xValue;
  }

  @Override
  public void setDimensionY(double yValue) {
    this.yDim = yValue;
  }

  @Override
  public void setAppearTick(int tick) {
    this.appearTick = tick;
  }
  
  @Override
  public void setDisappearTick(int tick) {
    this.disappearTick = tick;
  }
  
  @Override
  public double getReferenceX() {
    return this.xRef;
  }

  @Override
  public double getReferenceY() {
    return this.yRef;
  }

  @Override
  public double getDimensionX() {
    return this.xDim;
  }

  @Override
  public double getDimensionY() {
    return this.yDim;
  }
  
  @Override
  public double[] getColor() {
    return this.color;
  }

  @Override
  public int getAppearTick() {
    return this.appearTick;
  }
  
  @Override
  public int getDisappearTick() {
    return this.disappearTick;
  }
  
  @Override
  public String getName() {
    return this.name;
  }
}
