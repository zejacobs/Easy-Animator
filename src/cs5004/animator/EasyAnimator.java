package cs5004.animator;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.controller.AnimatorController;
import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;

/**
 * This class contains the main method to run the Easy Animator application.
 * @author Zachary Jacobs
 *
 */
public class EasyAnimator {

  /**
   * Runs the Easy Animator Application with the passed arguments for input file, output file,
   * view type, and animation speed.
   * @param args the arguments for input, output, view type, and animation speed
   */
  public static void main(String[] args) {
    
    AnimationBuilder<AnimatorModel> builder = new AnimatorModelImpl.BuilderImpl();
    AnimatorModel model = builder.build();
    
    AnimatorController controller = new AnimatorControllerImpl(model, args);
    
    AnimationReader.parseFile(controller.getInputFile(), builder); 
    
    controller.initializeView();
    
    controller.runAnimation();

  }

}
