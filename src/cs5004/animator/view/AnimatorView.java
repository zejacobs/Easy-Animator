package cs5004.animator.view;

import java.util.Collection;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * This interface contains the methods that all view types for the Easy Animator application
 * should implement.
 * @author Zachary Jacobs
 *
 */
public interface AnimatorView {
  
  /**
   * Displays the appropriate view type depending on what the user specifies
   * (text, SVG, animated view).
   * @param shapes the shapes to be displayed
   * @param animations the to be displayed
   */
  public void display(Collection<Shape> shapes, Collection<Animation> animations);

}
