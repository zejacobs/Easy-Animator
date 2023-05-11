package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * This class represents the visual view of the Easy Animator application. The visual view of the
 * animation utilizes java.swift graphics to display the shapes and their animations in a 
 * JFrame Window.
 * @author Zachary Jacobs
 *
 */
public class AnimatorVisualView extends JFrame implements AnimatorView {
  
  private AnimationPanel tickFrame;
  
  /**
   * Initializes a new AnimatorVisualView object with passed integer array containing the location
   * dimensions of the JFrame animation display window. Used to display a visual representation of
   * the model's shapes and animations tick by tick.
   * @param canvas the dimensions of the JFrame display window
   */
  public AnimatorVisualView(int[] canvas) {
    super("Easy Animator");
    
    int frameWidth = canvas[2] + 20;
    int frameHeight = canvas[3] + 40;
    int canvasWidth = canvas[2] + canvas[0];
    int canvasHeight = canvas[3] + canvas[1];
    
    //Setup main JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(canvas[0], canvas[1]);
    this.setPreferredSize(new Dimension(frameWidth, frameHeight));
    this.setBackground(Color.WHITE);
    this.setLayout(new GridLayout());
    
    //Setup AnimationPanel
    this.tickFrame = new AnimationPanel();
    this.tickFrame.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    //this.add(tickFrame);
    
    //Setup scroll pane
    JScrollPane scrollPane = new JScrollPane(tickFrame);
    scrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane);

    this.setVisible(true);
    this.pack();
  }
  
  @Override
  public void display(Collection<Shape> shapes, Collection<Animation> animations) {
    this.tickFrame.addShapes(shapes);
    this.tickFrame.repaint();
  }

}
