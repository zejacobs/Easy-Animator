package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cs5004.animator.util.AnimationBuilder;

/**
 * This class contains the implementation of the model for the Easy Animator application.
 * The model creates and manages the shapes and animation events that the Easy Animator
 * application uses to generate the entire animation. The model mainly keeps track of all the
 * shapes and animations and delegates the specific shape or animation work to the shapes and
 * animation objects themselves.
 * The model originally centered around storing the shapes and animations in lists within the
 * model but the hash map "dictionary" of the declared shapes and their shape type from the 
 * AnimationReader/Builder was added to keep track of the shapes as they were declared and
 * then the shapes themselves are instantiated once the specific attribute information is
 * made available.
 * 
 * @author Zachary Jacobs
 *
 */
public class AnimatorModelImpl implements AnimatorModel {

  private Map<String, String> declaredShapes;
  private ArrayList<Shape> shapes;
  private ArrayList<Animation> animations;
  private int[] canvasDimensions;
  private int currentTick;
  
  /**
   * Instantiates a new AnimatorModelImpl object and creates the internal data structures used to
   * store the Shape and Animation data that will be read in from the AnimationReader/Builder from
   * the animation input text file.
   * */
  public AnimatorModelImpl() {
    this.declaredShapes = new HashMap<String, String>();
    this.shapes = new ArrayList<Shape>();
    this.animations = new ArrayList<Animation>();
    this.canvasDimensions = new int[4];
    this.currentTick = 1;
  }

  @Override
  public void addShape(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public void addAnimation(Animation a) {
    this.animations.add(a);
  }

  @Override
  public ArrayList<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  public ArrayList<Animation> getAnimations() {
    return this.animations;
  }

  @Override
  public int getCurrentTick() {
    return this.currentTick;
  }

  @Override
  public void nextTick() {
    this.currentTick++;
  }
  
  @Override
  public int getLargestTick() {
    List<Integer> endTicks = this.animations.stream().map(b -> b.getEndTick())
        .collect(Collectors.toList());
    if (!endTicks.isEmpty()) {
      return Collections.max(endTicks);
    }
    return 1;
  }

  @Override
  public int[] getCanvasDimensions() {
    return this.canvasDimensions;
  }
 
  /**
   * This static class implements the AnimationBuilder interface and works together with the
   * AnimationReader class to read the formatted animation input text files and populate the
   * animation model with the appropriate Shape and Animation objects to display the animation.
   * @author Zachary Jacobs
   *
   */
  public static final class BuilderImpl implements AnimationBuilder<AnimatorModel> {

    AnimatorModelImpl model = new AnimatorModelImpl();
    
    @Override
    public AnimatorModelImpl build() {
      return model;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      model.canvasDimensions[0] = x;
      model.canvasDimensions[1] = y;
      model.canvasDimensions[2] = width;
      model.canvasDimensions[3] = height; 
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      model.declaredShapes.put(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {

      //Map the model shape list to a list of all shape names
      List<String> shapeNames = model.shapes.stream()
          .map(b -> b.getName()).collect(Collectors.toList());
      
      //Check if the shape who's motion is being read has been created yet and do so if not
      if (!shapeNames.contains(name)) {
        String shapeType = model.declaredShapes.get(name);
        
        if (shapeType.equalsIgnoreCase("rectangle")) {
          model.addShape(new Rectangle(name, x1, y1, w1, h1, r1, g1, b1, t1, 1000000));
        }
        else if (shapeType.equalsIgnoreCase("ellipse")) {
          model.addShape(new Ellipse(name, x1, y1, w1, h1, r1, g1, b1, t1, 1000000));
        }
      }
      
      //Check which shape attributes are changing and add the appropriate animation(s) to the model
      for (Shape s:model.shapes) {
        if (s.getName().equals(name)) {
          if (x1 != x2 || y1 != y2) { //Move
            model.addAnimation(new Move(s, t1, t2, x1, y1, x2, y2));
          }
          if (w1 != w2 || h1 != h2) { //Resize
            model.addAnimation(new Resize(s, t1, t2, w1, h1, w2, h2));
          }
          if (r1 != r2 || g1 != g2 || b1 != b2) { //ChangeColor
            model.addAnimation(new ChangeColor(s, t1, t2, r1, g1, b1, r2, g2, b2));
          }
          break; //Break searching for loop if shape is found
        }
      }
      
      //Sort Animations by their start tick
      Collections.sort(model.getAnimations(), Comparator.comparingInt(Animation::getStartTick));
      
      //Set all shapes disappear time to the end of the last animation
      int largestTick = model.getLargestTick();
      for (Shape s:model.shapes) {
        s.setDisappearTick(largestTick);
      }
      
      return this;
    }
  }

  /**
   * Defines the string representation of the AnimatorModel as a list of all of the shapes of
   * this model and their names followed by a list of all of the animations of this model followed
   * by the shape associated to the animation.
   */
  @Override
  public String toString() {
    String model = "";
    
    for (Shape s:this.getShapes()) {
      if (s instanceof Rectangle) {
        model += "Rectangle ";
      }
      else if (s instanceof Ellipse) {
        model += "Oval ";
      }
      model +=  s.getName() + "\n";
    }
    
    model += "\n"; //add extra line break to separate shapes and animations
    
    for (Animation a:this.getAnimations()) {
      if (a instanceof Move) {
        model += "Move ";
      }
      else if (a instanceof ChangeColor) {
        model += "ChangeColor ";
      }
      else if (a instanceof Resize) {
        model += "Resize ";
      }
      model += a.getShapeName() + "\n";
    }
    
    if (model.equals("\n")) {
      return "Model is empty";
    }
    return model;
  }

}
