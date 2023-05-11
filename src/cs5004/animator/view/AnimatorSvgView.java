package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This class represents the SVG view of the Easy Animator Application. The SVG view of the
 * animation consists of the animated shapes and their animations being translated into the
 * SVG language in a .svg file to generate 2 dimensional graphics in XML when opened in a
 * web browser. The reference point and dimensions of the Ellipse objects must be translated
 * to refer to the center and radius of the ellipse when writing the SVG animation comands.
 * @author Zachary Jacobs
 *
 */
public class AnimatorSvgView implements AnimatorView {

  private Appendable output;
  private int[] canvasSize;
  
  /**
   * Instantiates a new AnimatorSvgView object capable of outputting the Easy Animator animation
   * translated into the SVG language to the passed Appendable object output.
   * @param output the output the svg view is appended to
   * @param canvas the animation canvas dimensions
   */
  public AnimatorSvgView(Appendable output, int[] canvas) {
    this.output = output;
    this.canvasSize = canvas;
  }
  
  @Override
  public void display(Collection<Shape> shapes, Collection<Animation> animations) {
    
    double xShift = this.canvasSize[0] + this.canvasSize[2];
    double yShift = this.canvasSize[1] + this.canvasSize[3];
    
        
    String svgText = 
        "<svg width=\"" + xShift + "\" height=\"" + yShift
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" >\n"
        + "<rect>\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"100000ms\" "
        + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n";
    
    
    double currX; 
    double currY;
    double currDimX;
    double currDimY;
    double currR;
    double currG;
    double currB;
    double newX;
    double newY; 
    double newDimX; 
    double newDimY;
    double newR;
    double newG;
    double newB;
    String animateStringStart;
    
    for (Shape s: shapes) {
      currX = s.getReferenceX();
      currY = s.getReferenceY();
      currDimX = s.getDimensionX();
      currDimY = s.getDimensionY();
      currR = s.getColor()[0];
      currG = s.getColor()[1];
      currB = s.getColor()[2];
      
      double speed = 100;
      
      if (s instanceof Rectangle) {
        svgText += 
            "<rect id=\""  + s.getName() + "\" x=\"" + currX
          + "\" y=\"" + currY + "\" width=\"" + currDimX + "\" height=\"" + currDimY
          + "\" fill=\"rgb(" + currR + "," + currG + "," + currB + ")\" "
          + "visibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+" + (speed * s.getAppearTick())
          + "ms\" dur=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\" "
          + "to=\"visible\" fill=\"freeze\" />\n";
        
        //Add animations
        for (Animation a: animations) {
          if (a.getShapeName().equals(s.getName())) {
            animateStringStart = 
                "<animate attributeType=\"xml\" begin=\"base.begin+" + (speed * a.getStartTick())
              + "ms\" dur=\"" + (a.getEndTick() - a.getStartTick()) * speed + "ms\" ";
            
            svgText += animateStringStart;
            
            a.animateShape(a.getEndTick());
            
            //Add Move animation
            if (a.getType().equals("Move")) {
              newX = s.getReferenceX();
              newY = s.getReferenceY();
              
              svgText +=
                  "attributeName=\"x\" from=\"" + currX + "\" to=\"" + newX
                + "\" fill=\"freeze\" />\n"
                + animateStringStart
                + "attributeName=\"y\" from=\"" + currY + "\" to=\"" + newY
                + "\" fill=\"freeze\" />\n";
              
              //update current x and y positions
              currX = newX;
              currY = newY;
            }
            //Add ChangeColor animation
            else if (a.getType().equals("ChangeColor")) {
              newR = s.getColor()[0];
              newG = s.getColor()[1];
              newB = s.getColor()[2];
              
              svgText +=
                  "attributeName=\"fill\" from=\"rgb(" + currR + "," + currG + "," + currB
                + ")\" to=\"rgb(" + newR + "," + newG + "," + newB + ")\" fill=\"freeze\" />\n";
              
              //update current RBG values
              currR = newR;
              currG = newG;
              currB = newB;
            }
            //Add Resize animation
            else if (a.getType().equals("Resize")) {
              newDimX = s.getDimensionX();
              newDimY = s.getDimensionY();
              
              svgText +=
                  "attributeName=\"width\" from=\"" + currDimX + "\" to=\"" + newDimX
                + "\" fill=\"freeze\" />\n"
                + animateStringStart
                + "attributeName=\"height\" from=\"" + currDimY + "\" to=\"" + newDimY
                + "\" fill=\"freeze\" />\n";
              
              //update current width and height
              currDimX = newDimX;
              currDimY = newDimY;
            }
          }
        }
        svgText += "</rect>\n";
      }
      else if (s instanceof Ellipse) {
        currDimX = currDimX / 2;  //Convert diameter to radius
        currDimY = currDimY / 2;  //Convert diameter to radius
        currX = currX + currDimX; //Shift to refer to center
        currY = currY + currDimY; //Shift to refer to center
        
        svgText +=
            "<ellipse id=\"" + s.getName() + "\" cx=\"" + currX
          + "\" cy=\"" + currY + "\" rx=\"" + currDimX + "\" ry=\"" + currDimY
          + "\" fill=\"rgb(" + currR + "," + currG + "," + currB + ")\" "
          + "visibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+" + (speed * s.getAppearTick())
          + "ms\" dur=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\" "
          + "to=\"visible\" fill=\"freeze\" />\n";
        
        //Add animations
        for (Animation a: animations) {
          if (a.getShapeName().equals(s.getName())) {
            animateStringStart = 
                "<animate attributeType=\"xml\" begin=\"base.begin+" + (speed * a.getStartTick())
              + "ms\" dur=\"" + (a.getEndTick() - a.getStartTick()) * speed + "ms\" ";
            
            svgText += animateStringStart;
            
            a.animateShape(a.getEndTick());
            
            //Add Move animation
            if (a.getType().equals("Move")) {
              newX = s.getReferenceX() + currDimX; //Shift to refer to center
              newY = s.getReferenceY() + currDimY;
              
              svgText +=
                  "attributeName=\"cx\" from=\"" + currX + "\" to=\"" + newX
                + "\" fill=\"freeze\" />\n"
                + animateStringStart
                + "attributeName=\"cy\" from=\"" + currY + "\" to=\"" + newY
                + "\" fill=\"freeze\" />\n";
              
              //update current x and y positions
              currX = newX;
              currY = newY;
            }
            //Add ChangeColor animation
            else if (a.getType().equals("ChangeColor")) {
              newR = s.getColor()[0];
              newG = s.getColor()[1];
              newB = s.getColor()[2];
              
              svgText +=
                  "attributeName=\"fill\" from=\"rgb(" + currR + "," + currG + "," + currB
                + ")\" to=\"rgb(" + newR + "," + newG + "," + newB + ")\" fill=\"freeze\" />\n";
              
              //update current RBG values
              currR = newR;
              currG = newG;
              currB = newB;
            }
            //Add Resize animation
            else if (a.getType().equals("Resize")) {
              newDimX = s.getDimensionX();
              newDimY = s.getDimensionY();
              
              svgText +=
                  "attributeName=\"rx\" from=\"" + currDimX / 2 + "\" to=\"" + newDimX / 2
                + "\" fill=\"freeze\" />\n"
                + animateStringStart
                + "attributeName=\"ry\" from=\"" + currDimY / 2 + "\" to=\"" + newDimY / 2
                + "\" fill=\"freeze\" />\n";
              
              //update current width and height
              currDimX = newDimX;
              currDimY = newDimY;
            }
          }
        }
        svgText += "</ellipse>\n";
      }
    }
    svgText += "</svg>";
    
    try {
      output.append(svgText);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (output instanceof FileWriter) {
      try {
        ((FileWriter) output).close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
