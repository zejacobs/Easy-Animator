package cs5004.animator.view;

import cs5004.animator.controller.AnimatorControllerImpl;

/**
 * This class contains a single static method to determine which AnimatorView implementation should
 * be instantiated and used by the Easy Animator application.
 * @author Zachary Jacobs
 *
 */
public class AnimatorViewFactory {
  /**
   * Instantiates an AnimatorView implementation corresponding to the passed string indicating
   * view type ("text", "svg", or "visual"). 
   * @param viewType the name of the type of AnimationView to use
   * @param output the output of the AnimatorView
   * @canvas the dimensions of the animation canvas
   * @return the specified AnimationView object
   */
  public static AnimatorView createView(String viewType, Appendable output, int[] canvas,
      AnimatorControllerImpl controller) {
    if (viewType.equalsIgnoreCase("text")) {
      return new AnimatorTextView(output);
    }
    else if (viewType.equalsIgnoreCase("svg")) {
      return new AnimatorSvgView(output, canvas);
    }
    else if (viewType.equalsIgnoreCase("visual")) {
      return new AnimatorVisualView(canvas);
    }
    else if (viewType.equals("playback")) {
      return new AnimatorPlaybackView(canvas, controller);
    }
    else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }

}
