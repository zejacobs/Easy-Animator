package cs5004.animator.model;

/**
 * This abstract class implements the Animation Interface methods that have the same behavior
 * amongst all of the specific Animation subclasses (Move, ChangeColor, and Resize).
 * @author Zachary Jacobs
 *
 */
public abstract class AbstractAnimation implements Animation {

  protected String type; //The type of animation this is
  protected Shape shape; //The shape associated with this animation
  protected int startTick; //The tick this animation starts
  protected int endTick; //The tick this animation ends
  
  /**
   * Super constructor used by animation subclasses to instantiate an animation with information
   * about the shape object associated with it, the tick it starts, and the tick it ends.
   * @param shape the shape associated with this animation
   * @param start the tick this animation starts
   * @param end the tick this animation ends
   */
  public AbstractAnimation(Shape shape, int start, int end) {
    this.shape = shape;
    this.startTick = start;
    this.endTick = end;
  }
  
  /**
   * Calculates the tweening value for a shape attribute at a given tick.
   */
  protected static double tweeningValue(double startValue, double endValue,
      int startTick, int endTick, int tweenTick) {
    return startValue * (((double)endTick - tweenTick) / (endTick - startTick))
        + endValue * (((double)tweenTick - startTick) / (endTick - startTick));
  }
  
  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public String getShapeName() {
    return this.shape.getName();
  }

  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

}
