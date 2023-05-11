package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * This class represents the text view of the Easy Animator application. The text view of the
 * animation consists of a summary of the shapes and their attributes followed by the animations
 * that occur in chronological order as a .txt file.
 * @author Zachary Jacobs
 *
 */
public class AnimatorTextView implements AnimatorView {

  private Appendable output;
  
  /**
   * Instantiates a new AnimatorTextView object capable of outputting the Easy Animator
   * animation as a text summary of the the animation to the passed Appendable object output.
   * @param output the output the text view is appended to
   */
  public AnimatorTextView(Appendable output) {
    this.output = output;
  }
  
  @Override
  public void display(Collection<Shape> shapes, Collection<Animation> animations) {
    String animationText = "Shapes:\n";
    
    //Display list of shapes
    for (Shape s:shapes) {
      animationText += s.toString() + "\n";
    }
    
    //Display list of animations
    for (Animation a:animations) {
      animationText += a.toString();
    }
    
    if (animationText.equals("Shapes: \n\n")) {
      try {
        output.append("Animation is empty");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        output.append(animationText);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    if (output instanceof FileWriter) {
      try {
        ((FileWriter) output).close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
