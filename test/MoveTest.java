import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cs5004.animator.model.Move;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Ellipse;

/**
 * A JUnit test class for the Move class.
 * @author Zachary Jacobs
 *
 */
public class MoveTest {

  private Move m;
  private Rectangle r;
  private Ellipse e;
  
  /**
   * Test for the constructor of the Move class.
   */
  @Test
  public void testConstructor() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    m = new Move(r, 1, 10, 0, 0, 1, 1);
    
    String expected = "Shape rect moves from (0.0,0.0) to (1.0,1.0) from t=1 to t=10\n";
    assertEquals(expected, m.toString());
    
    m = new Move(e, 1, 10, 0, 0, 1, 1);
    
    expected = "Shape ellip moves from (0.0,0.0) to (1.0,1.0) from t=1 to t=10\n";
    assertEquals(expected, m.toString());
  }
  
  /**
   * Test for the animateShape method.
   */
  @Test
  public void testAnimateShape() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 20, 1, 1, 0, 0, 0, 1, 1);
    
    //Animate Rectangle
    m = new Move(r, 1, 11, 0, 0, 20, 20);
    
    assertEquals(0.0, r.getReferenceX(), 0.001);
    assertEquals(0.0, r.getReferenceY(), 0.001);
    
    m.animateShape(6);
    assertEquals(10.0, r.getReferenceX(), 0.001);
    assertEquals(10.0, r.getReferenceY(), 0.001);
    
    m.animateShape(11);
    assertEquals(20.0, r.getReferenceX(), 0.001);
    assertEquals(20.0, r.getReferenceY(), 0.001);
    
    //Animate Ellipse
    m = new Move(e, 1, 11, 0, 20, 20, 40);
    
    assertEquals(0.0, e.getReferenceX(), 0.001);
    assertEquals(20.0, e.getReferenceY(), 0.001);
    
    m.animateShape(6);
    assertEquals(10.0, e.getReferenceX(), 0.001);
    assertEquals(30.0, e.getReferenceY(), 0.001);
    
    m.animateShape(11);
    assertEquals(20.0, e.getReferenceX(), 0.001);
    assertEquals(40.0, e.getReferenceY(), 0.001);
  }
  
  /**
   * Test for the toString method.
   */
  @Test
  public void testToString() {
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    m = new Move(r, 1, 20, 55.5, 6.89, 44.89, 66.6);
    
    String expected = "Shape rect moves from (55.5,6.9) to (44.9,66.6) from t=1 to t=20\n";
    assertEquals(expected, m.toString());
    
    m = new Move(e, 48, 60, 445.62, 46.9, 88.41, 0.6);
    
    expected = "Shape ellip moves from (445.6,46.9) to (88.4,0.6) from t=48 to t=60\n";
    assertEquals(expected, m.toString());
  }
  
}
