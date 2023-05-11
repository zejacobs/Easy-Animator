import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;

/**
 * A JUnit test class for the ChangeColor class.
 * @author Zachary Jacobs
 *
 */
public class ChangeColorTest {

  private ChangeColor c;
  private Rectangle r;
  private Ellipse e;
  
  /**
   * Test for the constructor of the ChangeColor class.
   */
  @Test
  public void testConstructor() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    c = new ChangeColor(r, 1, 10, 0, 0, 0, 1, 1, 1);
    
    String expected = "Shape rect changes color from (0.0,0.0,0.0) to (1.0,1.0,1.0) " 
                    + "from t=1 to t=10\n";
    assertEquals(expected, c.toString());
    
    c = new ChangeColor(e, 1, 10, 0, 0, 0, 1, 1, 1);
    
    expected = "Shape ellip changes color from (0.0,0.0,0.0) to (1.0,1.0,1.0) " 
             + "from t=1 to t=10\n";
    assertEquals(expected, c.toString());
  }

  /**
   * Test for the animateShape method.
   */
  @Test
  public void testAnimateShape() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    //Animate Rectangle
    c = new ChangeColor(r, 1, 11, 0, 0, 0, 20, 20, 20);
    
    assertEquals(0.0, r.getColor()[0], 0.001);
    assertEquals(0.0, r.getColor()[1], 0.001);
    assertEquals(0.0, r.getColor()[2], 0.001);
    
    c.animateShape(6);
    double expected = 10.0;
    assertEquals(expected, r.getColor()[0], 0.001);
    assertEquals(expected, r.getColor()[1], 0.001);
    assertEquals(expected, r.getColor()[2], 0.001);
    
    c.animateShape(11);
    expected = 20.0;
    assertEquals(expected, r.getColor()[0], 0.001);
    assertEquals(expected, r.getColor()[1], 0.001);
    assertEquals(expected, r.getColor()[2], 0.001);
    
    //Animate Ellipse
    c = new ChangeColor(e, 1, 11, 0, 0, 0, 20, 40, 60);
    
    assertEquals(0.0, e.getColor()[0], 0.001);
    assertEquals(0.0, e.getColor()[1], 0.001);
    assertEquals(0.0, e.getColor()[2], 0.001);
    
    c.animateShape(6);
    assertEquals(10.0, e.getColor()[0], 0.001);
    assertEquals(20.0, e.getColor()[1], 0.001);
    assertEquals(30.0, e.getColor()[2], 0.001);
    
    c.animateShape(11);
    assertEquals(20.0, e.getColor()[0], 0.001);
    assertEquals(40.0, e.getColor()[1], 0.001);
    assertEquals(60.0, e.getColor()[2], 0.001); 
  }
  
  /**
   * Test for the toString method.
   */
  @Test
  public void testToString() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    c = new ChangeColor(r, 1, 55, 1.2333, 56.5, 44.89, 20, 66.6, 155.98);
    
    String expected = "Shape rect changes color from (1.2,56.5,44.9) to (20.0,66.6,156.0) " 
        + "from t=1 to t=55\n";
    assertEquals(expected, c.toString());
    
    c = new ChangeColor(e, 44, 198, 10.44, 42.25, 4, 27.63, 99.99, 2.28);
    
    expected = "Shape ellip changes color from (10.4,42.3,4.0) to (27.6,100.0,2.3) " 
        + "from t=44 to t=198\n";
    assertEquals(expected, c.toString());
  }
  
}
