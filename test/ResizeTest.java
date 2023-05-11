import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Resize;

/**
 * A JUnit test class for the Resize class.
 * @author Zachary Jacobs
 *
 */
public class ResizeTest {

  private Resize s;
  private Rectangle r;
  private Ellipse e;
  
  /**
   * Test for the constructor of the ChangeColor class.
   */
  @Test
  public void testConstructor() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    s = new Resize(r, 1, 10, 1, 1, 2, 2);
    
    String expected = "Shape rect scales from Width: 1.0, Height: 1.0 to Width: 2.0, Height: 2.0 "
                    + "from t=1 to t=10\n";
    assertEquals(expected, s.toString());
    
    s = new Resize(e, 1, 10, 1, 1, 2, 2);
    
    expected = "Shape ellip scales from X radius: 1.0, Y radius: 1.0 to X radius: 2.0, "
             + "Y radius: 2.0 from t=1 to t=10\n";
    assertEquals(expected, s.toString());
  }
  
  /**
   * Test for the animateShape method.
   */
  @Test
  public void testAnimateShape() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 21, 0, 0, 0, 1, 1);
    
    //Animate Rectangle
    s = new Resize(r, 1, 11, 1, 1, 21, 21);
    
    assertEquals(1.0, r.getDimensionX(), 0.001);
    assertEquals(1.0, r.getDimensionY(), 0.001);
    
    s.animateShape(6);
    assertEquals(11.0, r.getDimensionX(), 0.001);
    assertEquals(11.0, r.getDimensionY(), 0.001);
    
    s.animateShape(11);
    assertEquals(21.0, r.getDimensionX(), 0.001);
    assertEquals(21.0, r.getDimensionY(), 0.001);
    
    //Animate Ellipse
    s = new Resize(e, 1, 11, 1, 21, 21, 41);
    
    assertEquals(1.0, e.getDimensionX(), 0.001);
    assertEquals(21.0, e.getDimensionY(), 0.001);
    
    s.animateShape(6);
    assertEquals(11.0, e.getDimensionX(), 0.001);
    assertEquals(31.0, e.getDimensionY(), 0.001);
    
    s.animateShape(11);
    assertEquals(21.0, e.getDimensionX(), 0.001);
    assertEquals(41.0, e.getDimensionY(), 0.001);
  }
  
  /**
   * Test for the toString method.
   */
  @Test
  public void testToString() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    s = new Resize(r, 1, 20, 55, 1.2333, 44.89, 66.6);
    
    String expected = "Shape rect scales from Width: 55.0, Height: 1.2 to Width: 44.9, "
        + "Height: 66.6 from t=1 to t=20\n";
    assertEquals(expected, s.toString());
    
    s = new Resize(e, 44, 198, 10.44, 42.253, 4, 27.63);
    
    expected = "Shape ellip scales from X radius: 10.4, Y radius: 42.3 to X radius: 4.0, "
        + "Y radius: 27.6 from t=44 to t=198\n";
    assertEquals(expected, s.toString());
  }
 
}
