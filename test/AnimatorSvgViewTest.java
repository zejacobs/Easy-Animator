import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

import org.junit.Test;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimatorSvgView;

/**
 * A JUnit test class for the AnitmatorSvgView class.
 * @author Zachary Jacobs
 *
 */
public class AnimatorSvgViewTest {

  /**
   * Test for the display method.
   */
  @Test
  public void testDisplay() {
    //Simple Example
    AnimationBuilder<AnimatorModel> builder = new AnimatorModelImpl.BuilderImpl();
    AnimatorModel model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("SimpleViewDisplayTest.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    StringWriter testOut = new StringWriter();
    
    AnimatorSvgView v = new AnimatorSvgView(testOut, model.getCanvasDimensions());
    v.display(model.getShapes(), model.getAnimations());
    
    String expected;
    expected = 
        "<svg width=\"500.0\" height=\"500.0\" version=\"1.1\" "
      + "xmlns=\"http://www.w3.org/2000/svg\" >\n<rect>\n"
      + "<animate id=\"base\" begin=\"0;base.end\" dur=\"100000ms\" attributeName=\"visibility\""
      + " from=\"hide\" to=\"hide\"/>\n</rect>\n"
      + "<rect id=\"bob\" x=\"400.0\" y=\"100.0\" width=\"100.0\" height=\"100.0\" "
      + "fill=\"rgb(0.0,255.0,0.0)\" visibility=\"hidden\" >\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+100.0ms\" dur=\"1.0ms\" "
      + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"fill\" from=\"rgb(0.0,255.0,0.0)\" to=\"rgb(255.0,0.0,0.0)\" "
      + "fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+2100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"x\" from=\"400.0\" to=\"100.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+2100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"y\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+4100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"width\" from=\"100.0\" to=\"20.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+4100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"height\" from=\"100.0\" to=\"20.0\" fill=\"freeze\" />\n"
      + "</rect>\n</svg>";
      
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
    
    v = new AnimatorSvgView(testOut, model.getCanvasDimensions());
    v.display(model.getShapes(), model.getAnimations());
    
    expected =
        "<svg width=\"560.0\" height=\"430.0\" version=\"1.1\" " 
      + "xmlns=\"http://www.w3.org/2000/svg\" >\n<rect>\n"
      + "<animate id=\"base\" begin=\"0;base.end\" dur=\"100000ms\" attributeName=\"visibility\""
      + " from=\"hide\" to=\"hide\"/>\n</rect>\n"
      + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
      + "fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+100.0ms\" dur=\"1.0ms\" "
      + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" dur=\"4000.0ms\" "
      + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" dur=\"4000.0ms\" "
      + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+5100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+5100.0ms\" dur=\"1900.0ms\" "
      + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+7000.0ms\" dur=\"3000.0ms\" "
      + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+7000.0ms\" dur=\"3000.0ms\" "
      + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n</rect>\n"
      + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
      + "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+600.0ms\" dur=\"1.0ms\" "
      + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+2000.0ms\" dur=\"3000.0ms\" "
      + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+2000.0ms\" dur=\"3000.0ms\" "
      + "attributeName=\"cy\" from=\"100.0\" to=\"280.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"2000.0ms\" "
      + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"2000.0ms\" "
      + "attributeName=\"cy\" from=\"280.0\" to=\"400.0\" fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"2000.0ms\" "
      + "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" to=\"rgb(0.0,170.0,85.0)\" "
      + "fill=\"freeze\" />\n"
      + "<animate attributeType=\"xml\" begin=\"base.begin+7000.0ms\" dur=\"1000.0ms\" "
      + "attributeName=\"fill\" from=\"rgb(0.0,170.0,85.0)\" to=\"rgb(0.0,255.0,0.0)\" "
      + "fill=\"freeze\" />\n"
      + "</ellipse>\n</svg>";
    
    assertEquals(expected, testOut.toString());
  }

}
