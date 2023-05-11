import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cs5004.animator.view.AnimatorPlaybackView;
import cs5004.animator.view.AnimatorSvgView;
import cs5004.animator.view.AnimatorTextView;
import cs5004.animator.view.AnimatorView;
import cs5004.animator.view.AnimatorViewFactory;
import cs5004.animator.view.AnimatorVisualView;

/**
 * A JUnit test class for the AnimatorViewFactory class.
 * @author Zachary Jacobs
 *
 */
public class AnimatorViewFactoryTest {

  /**
   * Test for the static method createView.
   */
  @Test
  public void testCreateView() {
    AnimatorView v;
    Appendable out = System.out;
    int[] canvas = new int[4];
    
    v = AnimatorViewFactory.createView("text", out, canvas, null);
    assertTrue(v instanceof AnimatorTextView);
    
    v = AnimatorViewFactory.createView("svg", out, canvas, null);
    assertTrue(v instanceof AnimatorSvgView);
    
    v = AnimatorViewFactory.createView("visual", out, canvas, null);
    assertTrue(v instanceof AnimatorVisualView);
    
    v = AnimatorViewFactory.createView("playback", out, canvas, null);
    assertTrue(v instanceof AnimatorPlaybackView);
  }
  
  /**
   * Test for the exception handling of the createView method when passed an invalid view type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewType() {
    Appendable out = System.out;
    int[] canvas = new int[4];
    
    AnimatorViewFactory.createView("screen", out, canvas, null);
  }
}
