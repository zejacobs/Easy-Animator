import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cs5004.animator.model.Rectangle;

/**
 * A JUnit test class for the Rectangle class.
 * @author Zachary Jacobs
 *
 */
public class RectangleTest {

  private Rectangle r;
  
  /**
   * Test for the constructor of the Rectangle class.
   */
  @Test
  public void testConstructor() {
    String name = "rect";
    double xRef = 0;
    double yRef = 0;
    double xDim = 1;
    double yDim = 1;
    double[] color = {0, 0, 0};
    int appear = 1;
    int disappear = 1;
        
    r = new Rectangle(name, xRef, yRef, xDim, yDim,
        color[0], color[1], color [2], appear, disappear);
    
    assertEquals(name, r.getName());
    assertEquals(xRef, r.getReferenceX(), 0.001);
    assertEquals(yRef, r.getReferenceY(), 0.001);
    assertEquals(xDim, r.getDimensionX(), 0.001);
    assertEquals(yDim, r.getDimensionY(), 0.001);
    assertEquals(color[0], r.getColor()[0], 0.001);
    assertEquals(color[1], r.getColor()[1], 0.001);
    assertEquals(color[2], r.getColor()[2], 0.001);
    assertEquals(appear, r.getAppearTick());
    assertEquals(disappear, r.getDisappearTick());
    
    String expected = "Name: rect\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=1\n";
    
    assertEquals(expected, r.toString());
  }
  
  /**
   * Test the exception handling of the Rectangle constructor when passed invalid dimensions.
   */
  @Test
  public void testInvalidDimensions() {
    //Invalid x-axis dimension
    try {
      r = new Rectangle("rect", 0, 0, 0, 1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, -1, 1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    
    //Invalid y-axis dimension
    try {
      r = new Rectangle("rect", 0, 0, 1, 0, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, -1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    
    //Both invalid dimensions
    try {
      r = new Rectangle("rect", 0, 0, 0, 0, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, -1, -1, 1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
  }
  
  /**
   * Test the exception handling of the Rectangle constructor when passed invalid tick times.
   */
  @Test
  public void testInvalidTicks() {
    //Invalid appear tick
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, 0, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, -1, 1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    
    //Invalid disappear tick
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, 1, 0);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, 1, -1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    
    //Both invalid ticks
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, 0, 0);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, 1, -1, -1);
      fail();
    }
    catch (IllegalArgumentException r) {
      //pass
    }
  }
  
  /**
   * Test the exception handling of the Rectangle constructor when passed invalid RGB values.
   */
  @Test
  public void testInvalidColorValues() {
    //Invalid red value
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, -1, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 256, 1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid blue value
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, -1, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 256, 1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid green value
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, -1, 1, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //pass
    }
    try {
      r = new Rectangle("rect", 0, 0, 1, 1, 1, 1, -1, 1, 1);
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
    r = new Rectangle("Bob", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    
    //get initialized values
    assertEquals("Bob", r.getName());
    assertEquals(0, r.getReferenceX(), 0.001);
    assertEquals(0, r.getReferenceY(), 0.001);
    assertEquals(1, r.getDimensionX(), 0.001);
    assertEquals(1, r.getDimensionY(), 0.001);
    assertEquals(0, r.getColor()[0], 0.001);
    assertEquals(0, r.getColor()[1], 0.001);
    assertEquals(0, r.getColor()[2], 0.001);
    assertEquals(1, r.getAppearTick(), 0.001);
    assertEquals(1, r.getDisappearTick(), 0.001);
    
    //Set new values
    r.setReference(2, 3);
    r.setDimensionX(4);
    r.setDimensionY(5);
    r.setColor(6, 7, 8);
    r.setAppearTick(9);
    r.setDisappearTick(10);
    
    //get new values
    assertEquals(2, r.getReferenceX(), 0.001);
    assertEquals(3, r.getReferenceY(), 0.001);
    assertEquals(4, r.getDimensionX(), 0.001);
    assertEquals(5, r.getDimensionY(), 0.001);
    assertEquals(6, r.getColor()[0], 0.001);
    assertEquals(7, r.getColor()[1], 0.001);
    assertEquals(8, r.getColor()[2], 0.001);
    assertEquals(9, r.getAppearTick());
    assertEquals(10, r.getDisappearTick());
    
    //set new values
    r.setReference(22.2, 33.3);
    r.setDimensionX(44.4);
    r.setDimensionY(55.5);
    r.setColor(66.6, 77.7, 88.8);
    r.setAppearTick(99);
    r.setDisappearTick(100);
    
    //get new values
    assertEquals(22.2, r.getReferenceX(), 0.001);
    assertEquals(33.3, r.getReferenceY(), 0.001);
    assertEquals(44.4, r.getDimensionX(), 0.001);
    assertEquals(55.5, r.getDimensionY(), 0.001);
    assertEquals(66.6, r.getColor()[0], 0.001);
    assertEquals(77.7, r.getColor()[1], 0.001);
    assertEquals(88.8, r.getColor()[2], 0.001);
    assertEquals(99, r.getAppearTick());
    assertEquals(100, r.getDisappearTick()); 
  }

}
