import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.model.AnimatorModelImpl;

/**
 * A JUnit test class for the AnimatorControllerImpl class.
 * @author Zachary Jacobs
 *
 */
public class AnimatorControllerImplTest {

  AnimatorControllerImpl c;

  /**
   * Test for the AnimatorControllerImpl class constructor and the readCmdArgs method.
   * This readCmdArgs method is called during an AnimationControllerImpl object's construction.
   * 
   */
  @Test
  public void testConstructorReadCmdArgs() {
    AnimatorModelImpl m = new AnimatorModelImpl();
    
    //Text view no output no speed
    String[] cmdArgs = {"-in", "smalldemo.txt", "-view", "text"};
    c = new AnimatorControllerImpl(m, cmdArgs);
    String expected = "smalldemo.txt text System.out 10";
    assertEquals(expected, c.toString());
    
    //Text view no speed
    String[] cmdArgs2 = {"-in", "buildings.txt", "-view", "text", "-out", "controlTest.txt"};
    c = new AnimatorControllerImpl(m, cmdArgs2);
    expected = "buildings.txt text controlTest.txt 10";
    assertEquals(expected, c.toString());
    
    //Text view all args
    String[] cmdArgs3 = {"-in", "toh-3.txt", "-view", "text", "-out", "controlTest.txt",
        "-speed", "30"};
    c = new AnimatorControllerImpl(m, cmdArgs3);
    expected = "toh-3.txt text controlTest.txt 30";
    assertEquals(expected, c.toString());
    
    //SVG view no output no speed
    String[] cmdArgs4 = {"-in", "buildings.txt", "-view", "svg"};
    c = new AnimatorControllerImpl(m, cmdArgs4);
    expected = "buildings.txt svg System.out 10";
    assertEquals(expected, c.toString());
    
    //SVG view no speed
    String[] cmdArgs5 = {"-in", "toh-3.txt", "-view", "svg", "-out", "controlTest.svg"};
    c = new AnimatorControllerImpl(m, cmdArgs5);
    expected = "toh-3.txt svg controlTest.svg 10";
    assertEquals(expected, c.toString());
    
    //SVG view all args
    String[] cmdArgs6 = {"-in", "smalldemo.txt", "-view", "svg", "-out", "controlTest.svg",
        "-speed", "30"};
    c = new AnimatorControllerImpl(m, cmdArgs6);
    expected = "smalldemo.txt svg controlTest.svg 30";
    assertEquals(expected, c.toString());
    
    //Visual view no speed
    String[] cmdArgs7 = {"-in", "smalldemo.txt", "-view", "visual"};
    c = new AnimatorControllerImpl(m, cmdArgs7);
    expected = "smalldemo.txt visual System.out 10";
    assertEquals(expected, c.toString());
    
    //Visual view all args
    String[] cmdArgs8 = {"-in", "buildings.txt", "-view", "visual", "-speed", "100"};
    c = new AnimatorControllerImpl(m, cmdArgs8);
    expected = "buildings.txt visual System.out 100";
    assertEquals(expected, c.toString());
    
    //Args in different order
    String[] cmdArgs9 = {"-speed", "40", "-out", "controlTest.txt", "-in", "smalldemo.txt",
        "-view", "text"};
    c = new AnimatorControllerImpl(m, cmdArgs9);
    expected = "smalldemo.txt text controlTest.txt 40";
    assertEquals(expected, c.toString());
    
    String[] cmdArgs10 = {"-view", "svg", "-in", "toh-3.txt", "-speed", "88",
        "-out", "controlTest.svg"};
    c = new AnimatorControllerImpl(m, cmdArgs10);
    expected = "toh-3.txt svg controlTest.svg 88";
    assertEquals(expected, c.toString());
  }
  
  /**
   * Test for the readCmdArgs method exception handling for bad input from the command line.
   */
  @Test
  public void testReadCmdArgsException() {
    AnimatorModelImpl m = new AnimatorModelImpl();
    //No input or view arguments
    String[] cmdArgs = {};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Input but no view argument
    String[] cmdArgs2 = {"-in", "toh-3.txt"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs2);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //View but no input argument
    String[] cmdArgs3 = {"-view", "text"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs3);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Zero speed
    String[] cmdArgs4 = {"-in", "smalldemo.txt", "-view", "visual", "-speed", "0"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs4);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Negative speed
    String[] cmdArgs5 = {"-in", "smalldemo.txt", "-view", "visual", "-speed", "-1"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs5);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Text view not .txt output
    String[] cmdArgs6 = {"-in", "smalldemo.txt", "-view", "text", "-out", "testOut"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs6);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //SVG view not .svg output
    String[] cmdArgs7 = {"-in", "smalldemo.txt", "-view", "svg", "-out", "testOut.txt"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs7);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Input is not a .txt file
    String[] cmdArgs8 = {"-in", "smalldemo.svg", "-view", "text"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs8);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Invalid view type
    String[] cmdArgs9 = {"-in", "smalldemo.txt", "-view", "screen"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs9);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    }
    
    //Input .txt file that doesn't exist
    String[] cmdArgs10 = {"-in", "fakeFile.txt", "-view", "screen"};
    try {
      c = new AnimatorControllerImpl(m, cmdArgs10);
      fail();
    } catch (IllegalArgumentException e) {
      //pass
    } 
  }
  
  /**
   * Test for the toString method.
   */
  @Test
  public void testToString() {
    AnimatorModelImpl m = new AnimatorModelImpl();
    String[] cmdArgs = {"-in", "smalldemo.txt", "-view", "text"};
    c = new AnimatorControllerImpl(m, cmdArgs);
    
    String expected = "smalldemo.txt text System.out 10";
    assertEquals(expected, c.toString());
    
    String[] cmdArgs2 = {"-in", "buildings.txt", "-view", "svg", "-out", "controllerTest.svg"};
    c = new AnimatorControllerImpl(m, cmdArgs2);
    
    expected = "buildings.txt svg controllerTest.svg 10";
    assertEquals(expected, c.toString());
    
    
    String[] cmdArgs3 = {"-in", "toh-3.txt", "-view", "visual", "-speed", "50"};
    c = new AnimatorControllerImpl(m, cmdArgs3);
    
    expected = "toh-3.txt visual System.out 50";
    assertEquals(expected, c.toString());
  }

}
