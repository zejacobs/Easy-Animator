package cs5004.animator.model;

/**
 * This class represents a rectangle shape in the Easy Animator Model. A rectangle has (x,y)
 * coordinates of it's reference point which is it's minimum (bottom left) corner. The width and
 * height of the rectangle are represented by it's x-axis and y-axis dimensions. A rectangle also
 * has the time tick it appears in the animation and its color as an RGB color value.
 * 
 * @author Zachary Jacobs
 *
 */
public class Rectangle extends AbstractShape {

  public Rectangle(String name, double xRef, double yRef, double xDim, double yDim,
      double r, double g, double b, int appear, int disappear) {
    super(name, xRef, yRef, xDim, yDim, r, g, b, appear, disappear);
  }
  
  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
        + "Type: rectangle\n"
        + "Min corner: (" + String.format("%.1f", this.xRef) + ","
        + String.format("%.1f", this.yRef) + "), "
        + "Width: " + String.format("%.1f", this.xDim) + ", " + "Height: "
        + String.format("%.1f", this.yDim) + ", "
        + "Color: (" + String.format("%.1f", this.color[0]) + ","
        + String.format("%.1f", this.color[1]) + "," + String.format("%.1f", this.color[2]) + ")\n"
        + "Appears at t=" + this.appearTick + "\n"
        + "Disappears at t=" + this.disappearTick + "\n";
  }
}
