package cs5004.animator.model;

/**
 * This class represents a changing color animation of a shape in the Easy Animator Model.
 * A ChangeColor animation has information regarding the shape this animation is associated
 * with, the ticks it takes place, the RGB color values to change from, and the RGB color values
 * to change to.
 * @author Zachary Jacobs
 *
 */
public class ChangeColor extends AbstractAnimation {

  private double fromR;
  private double fromG;
  private double fromB;
  private double toR;
  private double toG;
  private double toB;
  
  /**
   * Initializes a new ChangeColor animation object with the passed values for the shape this
   * animation is associated with, the ticks this animation takes place, the RGB color values
   * for the shape before the animation starts, and the RGB color values for the shape after
   * this animation finishes.
   * @param shape the shape associated with this animation
   * @param startTick the tick this animations starts
   * @param endTick the tick this animation ends
   * @param fromR The starting red RGB color value
   * @param fromG The starting green RGB color value
   * @param fromB The starting blue RGB color value
   * @param toR The finishing red RGB color value
   * @param toG The finishing green RGB color value
   * @param toB The finishing blue RGB color value
   */
  public ChangeColor(Shape shape, int startTick, int endTick,
      double fromR, double fromG, double fromB, double toR, double toG, double toB) {
    super(shape, startTick, endTick);
    this.type = "ChangeColor";
    this.fromR = fromR;
    this.fromG = fromG;
    this.fromB = fromB;
    this.toR = toR;
    this.toG = toG;
    this.toB = toB;
  }
  
  @Override
  public void animateShape(int currentTick) {
    double tweenR = tweeningValue(this.fromR, this.toR, this.startTick, this.endTick, currentTick);
    double tweenG = tweeningValue(this.fromG, this.toG, this.startTick, this.endTick, currentTick);
    double tweenB = tweeningValue(this.fromB, this.toB, this.startTick, this.endTick, currentTick);
    
    this.shape.setColor(tweenR, tweenG, tweenB);
  }

  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " changes color from ("
        + String.format("%.1f", this.fromR) + "," + String.format("%.1f", this.fromG) + ","
        + String.format("%.1f", this.fromB) + ") to (" + String.format("%.1f", this.toR)
        + "," + String.format("%.1f", this.toG) + "," + String.format("%.1f", toB)
        + ") from t=" + this.startTick + " to t=" + this.endTick + "\n";
  }
}
