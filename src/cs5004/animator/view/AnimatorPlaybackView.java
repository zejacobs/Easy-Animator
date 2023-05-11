package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * This class represents the playback view of the Easy Animator application. The playback view
 * is the similar to the visual view but with added features to pause, resume, and adjust
 * the speed of the animation via keyboard input.
 * @author Zachary Jacobs
 *
 */
public class AnimatorPlaybackView extends JFrame implements AnimatorView {

  private AnimationPanel tickFrame;
  
  /**
   * Instantiates a new AnimatorPlaybackView object with the passed canvas dimensions to set
   * the size of the JFrame window and a reference to the Easy Animator controller to add it as
   * a key listener to react to user keyboard input.
   * @param canvas the location and dimensions of the JFrame window
   * @param controller the controller of the Easy Animator application
   */
  public AnimatorPlaybackView(int[] canvas , AnimatorControllerImpl controller) {
    super("Easy Animator");
    this.addKeyListener(controller);
    
    int frameWidth = canvas[2];
    int frameHeight = canvas[3];
    int canvasWidth = canvas[2] + canvas[0];
    int canvasHeight = canvas[3] + canvas[1];
    
    //Setup main JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(canvas[0], canvas[1]);
    this.setPreferredSize(new Dimension(frameWidth + 20, frameHeight + 80));
    this.setBackground(Color.WHITE);
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    
    //Setup AnimationPanel
    this.tickFrame = new AnimationPanel();
    this.tickFrame.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    
    //Setup scroll pane
    JScrollPane scrollPane = new JScrollPane(tickFrame);
    scrollPane.setLocation(0, 0);
    scrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane);
    
    //Setup playback options panel
    String optionList = "Pause: SPACE  Slower: Z  Faster X";
    JLabel options = new JLabel(optionList, JLabel.CENTER);

    this.add(options);

    this.setVisible(true);
    this.pack();
  }
  
  @Override
  public void display(Collection<Shape> shapes, Collection<Animation> animations) {
    this.tickFrame.addShapes(shapes);
    this.tickFrame.repaint();
  }

}
