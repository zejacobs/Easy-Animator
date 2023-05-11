package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;

import javax.swing.JPanel;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This class represents the animation panel of the Easy Animator class which creates and displays
 * the generated animations when in a visual view type.
 * @author Zachary Jacobs
 *
 */
public class AnimationPanel extends JPanel {
  
  private Collection<Shape> shapes;
  
  /**
   * Instantiates a new AnimationPanel JPanel used to display the animation for the Easy
   * Animator visual view.
   */
  public AnimationPanel() {
    super();
    this.shapes = null;
    this.setVisible(true);
  }
  
  /**
   * Adds the shapes to be drawn to display the animation.
   * @param shapes a collection of shape objects to be drawn
   */
  public void addShapes(Collection<Shape> shapes) {
    this.shapes = shapes;
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;

    if (this.shapes != null) {
      int red;
      int green;
      int blue;
      for (Shape s:this.shapes) {
        //Set color of shape
        red = (int)s.getColor()[0];
        green = (int)s.getColor()[1];
        blue = (int)s.getColor()[2];
       
        g2d.setColor(new Color(red, green, blue));

        //Draw rectangle
        if (s instanceof Rectangle) {
          g2d.fillRect(((int)s.getReferenceX()), ((int)s.getReferenceY()),
              ((int)s.getDimensionX()), ((int)s.getDimensionY()));
        }
        
        //Draw ellipse
        else if (s instanceof Ellipse) {
          g2d.fillOval(((int)s.getReferenceX()), ((int)s.getReferenceY()),
              ((int)s.getDimensionX()), ((int)s.getDimensionY()));
        }
      }
    }
  }
}