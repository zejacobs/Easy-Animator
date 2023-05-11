package cs5004.animator.model;

/**
 * This class represents a ellipse (oval) shape in the Easy Animator Model. An ellipse has (x,y)
 * coordinates of it's reference point which is it's center. The x-axis radius and y-axis radius
 * of the ellipse are represented by it's x-axis and y-axis dimensions. An ellipse also
 * has the time tick it appears in the animation and its color as an RGB color value.
 * 
 * @author Zachary Jacobs
 *
 */
public class Ellipse extends AbstractShape {

  public Ellipse(String name, double xRef, double yRef, double xDim, double yDim,
      double r, double g, double b, int appear, int disappear) {
    super(name, xRef, yRef, xDim, yDim, r, g, b, appear, disappear);
  }
  
  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
        + "Type: oval\n"
        + "Center: (" + String.format("%.1f", this.xRef) + ","
        + String.format("%.1f", this.yRef) + "), "
        + "X radius: " + String.format("%.1f", this.xDim) + ", " + "Y radius: "
        + String.format("%.1f", this.yDim) + ", "
        + "Color: (" + String.format("%.1f", this.color[0]) + ","
        + String.format("%.1f", this.color[1]) + "," + String.format("%.1f", this.color[2]) + ")\n"
        + "Appears at t=" + this.appearTick + "\n"
        + "Disappears at t=" + this.disappearTick + "\n";
  }
}
