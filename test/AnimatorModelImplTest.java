import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Move;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Resize;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;

/**
 * A JUnit test class for the AnimatorModelImpl class and the nested static BuilderImpl class.
 * @author Zachary Jacobs
 *
 */
public class AnimatorModelImplTest {

  private AnimatorModelImpl m;
  private Rectangle r;
  private Ellipse e;
  private Move move;
  private Resize size;
  private ChangeColor color;
  
  /**
   * Test for the constructor of the AnimatorModelImpl class.
   */
  @Test
  public void testConstructor() {
    m = new AnimatorModelImpl();
    
    assertEquals(1, m.getCurrentTick());
    assertEquals("Model is empty", m.toString());
    assertTrue(m.getShapes().isEmpty());
    assertTrue(m.getAnimations().isEmpty());
    int[] canvas = m.getCanvasDimensions();
    for (int i = 0; i < 4; i++) {
      assertEquals(0, canvas[i]);
    }
  }
  
  /**
   * Test for the addShape and getShapes methods.
   */
  @Test
  public void testAddGetShapes() {
    m = new AnimatorModelImpl();
    assertTrue(m.getShapes().isEmpty());
    
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    m.addShape(r);
    assertTrue(m.getShapes().contains(r));
    
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    m.addShape(e);
    assertTrue(m.getShapes().contains(e));
    assertTrue(m.getShapes().contains(r));
    
  }
  
  /**
   * Test for the addAnimation and getAnimation methods.
   */
  @Test
  public void testAddGetAnimations() {
    m = new AnimatorModelImpl();
    assertTrue(m.getAnimations().isEmpty());
    
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    move = new Move(r, 0, 0, 1, 1, 1, 1);
    m.addAnimation(move);
    assertTrue(m.getAnimations().contains(move));
    
    size = new Resize(r, 1, 1, 2, 2, 1, 1);
    m.addAnimation(size);
    assertTrue(m.getAnimations().contains(size));
    assertTrue(m.getAnimations().contains(move));
    
    color = new ChangeColor(r, 0, 0, 0, 1, 1, 1, 1, 1);
    m.addAnimation(color);
    assertTrue(m.getAnimations().contains(color));
    assertTrue(m.getAnimations().contains(size));
    assertTrue(m.getAnimations().contains(move));
  }

  /**
   * Test for the getCurrentTick and nextTick methods.
   */
  @Test
  public void testModelTick() {
    m = new AnimatorModelImpl();
    
    assertEquals(1, m.getCurrentTick());
    m.nextTick();
    assertEquals(2, m.getCurrentTick());
    m.nextTick();
    assertEquals(3, m.getCurrentTick());
    m.nextTick();
    m.nextTick();
    assertEquals(5, m.getCurrentTick());
    m.nextTick();
    m.nextTick();
    m.nextTick();
    assertEquals(8, m.getCurrentTick());
  }
  
  /**
   * Test for the getLargestTick method.
   */
  @Test
  public void testGetLargestTick() {
    m = new AnimatorModelImpl();
    assertEquals(1, m.getLargestTick());
    
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    move = new Move(r, 1, 10, 1, 1, 1, 1);
    m.addAnimation(move);
    assertEquals(10, m.getLargestTick());
    
    size = new Resize(r, 1, 5, 2, 2, 1, 1);
    m.addAnimation(size);
    assertEquals(10, m.getLargestTick());
    
    color = new ChangeColor(r, 1, 20, 0, 1, 1, 1, 1, 1);
    m.addAnimation(color);
    assertEquals(20, m.getLargestTick());
  }
  
  /**
   * Test for the getCanvasDimensions method of the AnimatorModelImpl class and the 
   * setBounds method of the nested BuilderImpl static class.
   */
  @Test
  public void testGetCanvasDimensions() {
    AnimationBuilder<AnimatorModel> builder = new AnimatorModelImpl.BuilderImpl();
    AnimatorModel model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    assertEquals(200, model.getCanvasDimensions()[0]);
    assertEquals(70, model.getCanvasDimensions()[1]);
    assertEquals(360, model.getCanvasDimensions()[2]);
    assertEquals(360, model.getCanvasDimensions()[3]);
   
    builder = new AnimatorModelImpl.BuilderImpl();
    model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("buildings.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    assertEquals(0, model.getCanvasDimensions()[0]);
    assertEquals(0, model.getCanvasDimensions()[1]);
    assertEquals(800, model.getCanvasDimensions()[2]);
    assertEquals(800, model.getCanvasDimensions()[3]);
  }
  
  /**
   * Test for the addMotion method of the nested static BuilderImpl class.
   */
  @Test
  public void testAddMotion() {
    AnimationBuilder<AnimatorModel> builder = new AnimatorModelImpl.BuilderImpl();
    AnimatorModel model = builder.build();
    try {
      AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    assertEquals(2, model.getShapes().size());
    assertEquals("R", model.getShapes().get(0).getName());
    assertEquals("C", model.getShapes().get(1).getName());
    
    //Check first few animations
    assertEquals(7, model.getAnimations().size());
    assertEquals("Move", model.getAnimations().get(0).getType());
    assertEquals("R", model.getAnimations().get(0).getShapeName());
    assertEquals(10, model.getAnimations().get(0).getStartTick());
    assertEquals(50, model.getAnimations().get(0).getEndTick());
    
    assertEquals("Move", model.getAnimations().get(1).getType());
    assertEquals("C", model.getAnimations().get(1).getShapeName());
    assertEquals(20, model.getAnimations().get(1).getStartTick());
    assertEquals(50, model.getAnimations().get(1).getEndTick());
    
    assertEquals("Move", model.getAnimations().get(2).getType());
    assertEquals("C", model.getAnimations().get(2).getShapeName());
    assertEquals(50, model.getAnimations().get(2).getStartTick());
    assertEquals(70, model.getAnimations().get(2).getEndTick());
    
    assertEquals("ChangeColor", model.getAnimations().get(3).getType());
    assertEquals("C", model.getAnimations().get(3).getShapeName());
    assertEquals(50, model.getAnimations().get(3).getStartTick());
    assertEquals(70, model.getAnimations().get(3).getEndTick());
  }
  
  /**
   * Test for the toString method.
   */
  @Test
  public void testToString() {
    String expected;
    
    m = new AnimatorModelImpl();
    assertEquals("Model is empty", m.toString());
    
    r = new Rectangle("rect", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    m.addShape(r);
    expected = "Rectangle rect\n\n";
    assertEquals(expected, m.toString());
    
    move = new Move(r, 0, 0, 1, 1, 1, 1);
    m.addAnimation(move);
    expected = "Rectangle rect\n\n"
             + "Move rect\n";
    assertEquals(expected, m.toString());
    
    e = new Ellipse("ellip", 0, 0, 1, 1, 0, 0, 0, 1, 1);
    m.addShape(e);
    expected = "Rectangle rect\n"
             + "Oval ellip\n\n"
             + "Move rect\n";
    assertEquals(expected, m.toString());
    
    size = new Resize(e, 1, 1, 2, 2, 1, 1);
    m.addAnimation(size);
    expected = "Rectangle rect\n"
        + "Oval ellip\n\n"
        + "Move rect\n"
        + "Resize ellip\n";
    assertEquals(expected, m.toString());
    
    color = new ChangeColor(r, 0, 0, 0, 1, 1, 1, 1, 1);
    m.addAnimation(color);
    expected = "Rectangle rect\n"
        + "Oval ellip\n\n"
        + "Move rect\n"
        + "Resize ellip\n"
        + "ChangeColor rect\n";
    assertEquals(expected, m.toString());
  }
   
}
