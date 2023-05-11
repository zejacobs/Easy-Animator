import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cs5004.animator.model.Ellipse;

/**
 * A JUnit test class for the Ellipse class.
 * @author Zachary Jacobs
 *
 */
public class EllipseTest {

  private Ellipse e;
  
  /**
   * Test for the constructor of the Ellipse class.
   */
  @Test
  public void testConstructor() {
    String name = "ellip";
    double xRef = 0;
    double yRef = 0;
    double xDim = 1;
    double yDim = 1;
    double[] color = {0, 0, 0};
    int appear = 1;
    int disappear = 1;
        
    e = new Ellipse(name, xRef, yRef, xDim, yDim,
        color[0], color[1], color [2], appear, disappear);
    
    assertEquals(name, e.getName());
    assertEquals(xRef, e.getReferenceX(), 0.001);
    assertEquals(yRef, e.getReferenceY(), 0.001);
    assertEquals(xDim, e.getDimensionX(), 0.001);
    assertEquals(yDim, e.getDimensionY(), 0.001);
    assertEquals(color[0], e.getColor()[0], 0.001);
    assertEquals(color[1], e.getColor()[1], 0.001);
    assertEquals(color[2], e.getColor()[2], 0.001);
    assertEquals(appear, e.getAppearTick());
    assertEquals(disappear, e.getDisappearTick());
    
    String expected = "Name: ellip\n"
                    + "Type: oval\n"
                    + "Center: (0.0,0.0), X radius: 1.0, Y radius: 1.0, Color: (0.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=1\n";
    
    assertEquals(expected, e.toString());
  }
  
  /**
   * Test the exception handling of the Ellipse constructor when passed invalid dimensions.
   */
  @Test
  public void testInvalidDimensions() {
    //Invalid x-axis dimension
    try {
      e = new Ellipse("ellip", 0, 0, 0, 1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, -1, 1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid y-axis dimension
    try {
      e = new Ellipse("ellip", 0, 0, 1, 0, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, -1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Both invalid dimensions
    try {
      e = new Ellipse("ellip", 0, 0, 0, 0, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, -1, -1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
  }
  
  /**
   * Test the exception handling of the Ellipse constructor when passed invalid tick times.
   */
  @Test
  public void testInvalidTicks() {
    //Invalid appear tick
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, 0, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, -1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid disappear tick
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, 1, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, 1, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Both invalid ticks
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, 0, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, 1, -1, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
  }
  
  /**
   * Test the exception handling of the Ellipse constructor when passed invalid RGB values.
   */
  @Test
  public void testInvalidColorValues() {
    //Invalid red value
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, -1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 256, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid blue value
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, -1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 256, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid green value
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, -1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      e = new Ellipse("ellip", 0, 0, 1, 1, 1, 1, -1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
  }
  
  /**
   * Test for the getter and setter methods.
   */
  @Test
  public void testGettersSetters() {
    e = new Ellipse("Bob", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    //get initialized values
    assertEquals("Bob", e.getName());
    assertEquals(0, e.getReferenceX(), 0.001);
    assertEquals(0, e.getReferenceY(), 0.001);
    assertEquals(1, e.getDimensionX(), 0.001);
    assertEquals(1, e.getDimensionY(), 0.001);
    assertEquals(0, e.getColor()[0], 0.001);
    assertEquals(0, e.getColor()[1], 0.001);
    assertEquals(0, e.getColor()[2], 0.001);
    assertEquals(1, e.getAppearTick(), 0.001);
    assertEquals(1, e.getDisappearTick(), 0.001);
    
    //Set new values
    e.setReference(2, 3);
    e.setDimensionX(4);
    e.setDimensionY(5);
    e.setColor(6, 7, 8);
    e.setAppearTick(9);
    e.setDisappearTick(10);
    
    //get new values
    assertEquals(2, e.getReferenceX(), 0.001);
    assertEquals(3, e.getReferenceY(), 0.001);
    assertEquals(4, e.getDimensionX(), 0.001);
    assertEquals(5, e.getDimensionY(), 0.001);
    assertEquals(6, e.getColor()[0], 0.001);
    assertEquals(7, e.getColor()[1], 0.001);
    assertEquals(8, e.getColor()[2], 0.001);
    assertEquals(9, e.getAppearTick());
    assertEquals(10, e.getDisappearTick());
    
    //set new values
    e.setReference(22.2, 33.3);
    e.setDimensionX(44.4);
    e.setDimensionY(55.5);
    e.setColor(66.6, 77.7, 88.8);
    e.setAppearTick(99);
    e.setDisappearTick(100);
    
    //get new values
    assertEquals(22.2, e.getReferenceX(), 0.001);
    assertEquals(33.3, e.getReferenceY(), 0.001);
    assertEquals(44.4, e.getDimensionX(), 0.001);
    assertEquals(55.5, e.getDimensionY(), 0.001);
    assertEquals(66.6, e.getColor()[0], 0.001);
    assertEquals(77.7, e.getColor()[1], 0.001);
    assertEquals(88.8, e.getColor()[2], 0.001);
    assertEquals(99, e.getAppearTick());
    assertEquals(100, e.getDisappearTick());  
  }
   
}
