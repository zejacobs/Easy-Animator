import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

import org.junit.Test;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimatorTextView;

/**
 * A JUnit test class for the AnimatorTextView class.
 * @author Zachary Jacobs
 *
 */
public class AnimatorTextViewTest {
  
  /**
   * Test for the display method.
   */
  @Test
  public void testDisplay() {
    //Simple example
    AnimationBuilder<AnimatorModel> builder = new AnimatorModelImpl.BuilderImpl();
    AnimatorModel model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("SimpleViewDisplayTest.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    StringWriter testOut = new StringWriter();
    
    AnimatorTextView v = new AnimatorTextView(testOut);
    v.display(model.getShapes(), model.getAnimations());
    
    String expected =
        "Shapes:\nName: bob\nType: rectangle\nMin corner: (400.0,100.0), "
        + "Width: 100.0, Height: 100.0, Color: (0.0,255.0,0.0)\n" 
        + "Appears at t=1\nDisappears at t=60\n\n"
        + "Shape bob changes color from (0.0,255.0,0.0) to (255.0,0.0,0.0) from t=1 to t=20\n"
        + "Shape bob moves from (400.0,100.0) to (100.0,400.0) from t=21 to t=40\n"
        + "Shape bob scales from Width: 100.0, Height: 100.0 to Width: 20.0, Height: 20.0 "
        + "from t=41 to t=60\n";
      
    assertEquals(expected, testOut.toString());
    
    //Larger example
    builder = new AnimatorModelImpl.BuilderImpl();
    model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    testOut = new StringWriter();
    
    v = new AnimatorTextView(testOut);
    v.display(model.getShapes(), model.getAnimations());
    
    expected = 
        "Shapes:\nName: R\nType: rectangle\nMin corner: (200.0,200.0), "
      + "Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n" 
      + "Appears at t=1\nDisappears at t=100\n\n"
      + "Name: C\n"
      + "Type: oval\nCenter: (440.0,70.0), X radius: 120.0, Y radius: 60.0, Color: "
      + "(0.0,0.0,255.0)\n"
      + "Appears at t=6\nDisappears at t=100\n\n"
      + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
      + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
      + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
      + "Shape C changes color from (0.0,0.0,255.0) to (0.0,170.0,85.0) from t=50 to t=70\n"
      + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
      + "from t=51 to t=70\n"
      + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
      + "Shape C changes color from (0.0,170.0,85.0) to (0.0,255.0,0.0) from t=70 to t=80\n";

    assertEquals(expected, testOut.toString());
  }

}
