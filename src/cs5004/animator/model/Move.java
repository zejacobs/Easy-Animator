package cs5004.animator.model;

/**
 * This class represents a move animation of a shape in the Easy Animator Model.
 * A Move animation has information regarding the shape this animation is associated
 * with, the ticks it takes place, the (x,y) coordinates that the shape moves from, and
 * the (x,y) coordinates that the shape moves to.
 * @author Zachary Jacobs
 *
 */
public class Move extends AbstractAnimation {

  private double fromX;
  private double fromY;
  private double toX;
  private double toY;
  
  /**
   * Initializes a new Move animation object with the passed values for the shape this
   * animation is associated with, the ticks this animation takes place, the (x,y) coordinates
   * for the shape before the animation starts, and the (x,y) coordinates for the shape after the
   * animation finishes.
   * @param shape the shape associated with this animation
   * @param start the tick this animation starts
   * @param end the tick this animation ends
   * @param fromX the starting x coordinate
   * @param fromY the starting y coordinate
   * @param toX the finishing x coordinate
   * @param toY the finishing y coordinate
   */
  public Move(Shape shape, int start, int end, double fromX, double fromY,
      double toX, double toY) {
    super(shape, start, end);
    this.type = "Move";
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
    
  }
  
  @Override
  public void animateShape(int currentTick) {
    double tweenX = tweeningValue(this.fromX, this.toX, this.startTick, this.endTick, currentTick);
    double tweenY = tweeningValue(this.fromY, this.toY, this.startTick, this.endTick, currentTick);
    this.shape.setReference(tweenX, tweenY);
  }
  
  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " moves from (" + String.format("%.1f", this.fromX)
        + "," + String.format("%.1f", this.fromY) + ") to (" + String.format("%.1f", this.toX)
        + "," + String.format("%.1f", this.toY) + ") from t="
        + this.startTick + " to t=" + this.endTick + "\n";
  }
  
}
