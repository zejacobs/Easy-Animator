package cs5004.animator.model;

/**
 * This class represents a changing size animation of a shape in the Easy Animator Model.
 * A Resize animation has information regarding the shape this animation is associated
 * with, the ticks it takes place, the x-axis and y-axis dimension values for the shape starts
 * at, and the x-axis and y-axis dimension values the shape will resize to.
 * @author Zachary Jacobs
 *
 */
public class Resize extends AbstractAnimation {

  private double fromX;
  private double fromY;
  private double toX;
  private double toY;
  
  /**
   * Initializes a new Resize animation object with the passed values for the shape this
   * animation is associated with, the ticks this animation takes place, the x-axis and
   * y-axis dimensions for the shape before the animation starts, and the x-axis and y-axis
   * dimensions for the shape after the animation finishes.
   * @param shape the shape associated with this animation
   * @param start the tick this animation starts
   * @param end the tick this animation ends
   * @param fromX the starting x-axis dimension
   * @param fromY the starting y-axis dimension
   * @param toX the finishing x-axis dimension
   * @param toY the finishing y-axis dimension
   */
  public Resize(Shape shape, int start, int end, double fromX, double fromY,
      double toX, double toY) {
    super(shape, start, end);
    this.type = "Resize";
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
  }
  
  @Override
  public void animateShape(int currentTick) {
    if (fromX != toX) {
      double tweenX = tweeningValue(this.fromX, this.toX,
          this.startTick, this.endTick, currentTick);
      this.shape.setDimensionX(tweenX);
    }
    if (fromY != toY) {
      double tweenY = tweeningValue(this.fromY, this.toY,
          this.startTick, this.endTick, currentTick);
      this.shape.setDimensionY(tweenY);
    }
  }
  
  @Override
  public String toString() {
    String s = "";
    if (this.shape instanceof Rectangle) {
      s = "Shape " + this.shape.getName() + " scales from Width: "
        + String.format("%.1f", this.fromX) + ", Height: " + String.format("%.1f", this.fromY)
        + " to Width: " + String.format("%.1f", this.toX) + ", Height: "
        + String.format("%.1f", this.toY)
        + " from t=" + this.startTick + " to t=" + this.endTick + "\n";
    }
    else if (this.shape instanceof Ellipse) {
      s = "Shape " + this.shape.getName() + " scales from X radius: "
        + String.format("%.1f", this.fromX) + ", Y radius: " + String.format("%.1f", this.fromY)
        + " to X radius: " + String.format("%.1f", this.toX) + ", Y radius: "
        + String.format("%.1f", this.toY)
        + " from t=" + this.startTick + " to t=" + this.endTick + "\n";
    }
    return s;
  }
  
}
