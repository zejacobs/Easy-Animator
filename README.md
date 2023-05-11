# Easy Animator
Zachary Jacobs
CS5004 Final Project

The Easy Animator is a java application capable of reading formatted .txt files to generate
animations of varying types. The application can also produce textual descriptions of the animations
it has generated, SVG files that can be opened with a web browser to view the animation, and
a visual display of the animation using Java Swing graphics. The user specifies the input file
the application will generate the animation from, the type of view that application will display
the output path of the created animation file if one is created, and the speed of the animation
in ticks per second. These options are passed as command line arguments when running the application.

## Model

The model of the Easy Animator mainly consists of two ArrayLists, one containing all the shapes
and one containing all the animations performed to the shapes. The Interfaces Shape and Animation
and the classes that implement them were created to represent the items in these lists. Shape
objects have a variety of getter and setter methods methods to access/modify Shape attributes and
Animation objects have a variety of getter methods to get information on which shape the animation
is associated with, what type of animation it is, and which ticks the animation takes place during.
Animation objects also have the ability to modify the shape it is associated with via its
animateShape method. This method mutates the Shape object and sets the shape attributes the
Animation is changing ((x,y) for Move, x-axis/y-axis dimensions for Resize, and RGB values for
ChangeColor) to the new values passed to the Animation during its construction.
Using the provided AnimationReader class and the implementation of the provided 
AnimatiorBuilder interface, the model parses the formatted input text file and populates the
Shape and Animation lists with Shape and Animation concrete class objects. The AnimationReader
also model gets the location and dimensions of the animation canvas from the input file.
Upon moving on to part II of the project and learning more about the AniationReader/Builder
as well as concept of thinking of the animation as a series of unit-less animation ticks,
a hash map "dictionary" of declared shapes with the shape name and type was added to the model.
This allows the model to keep track of the shapes declared at the beginning of the input text
files but wait until the information regarding their starting attributes and appearance tick
is read before instantiating the Shape object and storing it in the model shape ArrayList.
Upon reading in a "motion" from the input, a list of the names of shapes in the model list is
checked to see if the shape the motion is referring has already been created and added to the
model. If the shape does not exist in the model shape list it is created and then the starting and
ending values of the motion are compared to see what kind of animations to create for this shape.
Different (x,y) coordinates results in a "Move" animation, different x-axis/y-axis dimensions
results in a "Resize" animation, and different RGB color values result in a "ChangeColor" animation
being created and added to the model animation list. Animations of different types can be added to
the model from one motion line from the input file if there are more than one set of attributes
changing and the animations are sorted from earliest start tick to latest.
Once the model has read in the input file and has populated the shape and animation lists it
"carries out" the animation relative to the currentTick attribute which stores the current
tick it is in the animation. This is defaulted to 1 and the model has methods to retrieve the
current tick and increment it by 1. The model also contains methods to get the shape and animation
lists in order to display the shapes and mutate them via the animation objects. In the views that
require it, the shapes in the model can be retrieved, the model current tick can be incremented,
the animations that are happening at the current tick can modify the shapes attributes to the
appropriate "tween" values for that tick, and then the new updated list of shapes can be retrieved.
This process can be done until the last animation tick been reached and the animation has finished.
	
## View
The text view of the Easy Animator application is the simplest and produces a text summary of
the shapes and then animations in the model. The Shapes will list information detailing their
attributes and then the animations list which shape is doing what and when in chronological order.
The text view utilizes the toString methods of the Shape and Animation objects and simply iterates
through the passed model shape and animation lists and calls each objects toString method.
The text view adds minimal formating. The text view can either output to the user designated output
.txt file or to the console (System.out).
The SVG view of the Easy Animator application is similar to the text view in that it outputs
text, but it is more complex in the way it translates the animation to the SVG language.
The SVG view iterates through each shape of the passed model shape list and iterates through each
animation of the passed model animation list to find the animations associated with the current
shape. It then uses the shapes current attributes and the shapes attributes after the animation is
completed to generate the appropriate SVG animate command. This view is also passed the animation
canvas location and dimensions stored in the model to be added to the SVG text. The SVG view can
either output to the user designated output .svg file or to the console (System.out). The .svg
output file can then be opened in a web browser to view the animation or opened with a text editor
to see the SVG language text. Output to the console only shows the SVG language text.
The visual view of the Easy Animator application utilizes Java Swing Graphics to produce the
model shapes and animations in a Java JFrame application window. The visual view is sent the
animation tick by tick. It is passed the model shape list and displays them then the model is
advanced to the next tick, the shapes are updated via the animations animateShape method and then
the new updated model shape list is sent to the visual view again with the next tick of the
animation to display. To play the entire animation this process would repeated until the last tick
of the animation has been displayed. The visual view creates a JFrame with the passed canvas
location and dimensions and will add scroll bars to the animation panel if the animation does not
fit in the passed canvas dimensions.
The playback view of the Easy Animator application is the same as the visual view but with
added keyboard controls that the user can use to control the animation. If the user can use the
space bar to pause or resume the animation, the 'z' key to slow the animation down, and the
'x' key to speed the animation up.

## Controller

The controller of the Easy Animator application is responsible for taking in the command line
arguments from the main and using that to appropriately construct the model and view. The
controller must be passed arguments for the input text file to read from and what type of view to
use at minimum, but also excepts an argument for an output file to use for the text and svg views
and an argument for the speed of the animation in ticks per second. The controller passes the
input file to the AnimationReader in the main program so that the animation model can be populated
and then it instantiates the correct view implementation class based on the view argument it was
given.
To display the views, the controller extracts shape and animation data from the model and
passes it to the views. In the case of the text and svg views, the controller uses the models
getShapes and getAnimations methods to pass the Shape and Animation objects to the views once
where they than can be converted into the text-based translation specified in those classes display
methods. For the visual view, the controller sends the animation shapes tick by tick until the
whole animation has been played. To do so the controller increments the currentTick of the model
in a while loop and updates the shapes for each tick. The controller makes the animations that are
occurring during the current tick update the shape attributes its associated with and then the new
collection of updated shapes for the current tick is sent to the view to be displayed. The speed
of the animation is regulated by a "sleep" method which pauses the main thread by the desired time
while in the while loop to delay the call of the visual views display method. The speed is
determined by the passed ticks per second argument passed to the controller and the default is set
to 10 ticks per second.
When the playback view is selected the controller gets the additional role of being a
keylistener for user input from the keyboard while the animation is playing. the
AnimationPlaybackView object is passed a reference to the controller during its construction
which allows it to add the controller as a keylistener. As a result, keyboard input can be used
to alter the tickSpeed attribute of the the controller to increase or decrease the animation speed
while the while loop sending the view the shapes current to display is running. The change in
tickSpeed alters the time that the main thread sleeps, therefore changing the time between
the view's display method calls. The pause feature is implemented using an if statement containing
the entirety of the animation displaying while loop. When the controller boolean pause attribute
is set to false the animation displaying while loop will continue and when pause is true none of 
while loop contents will run. Since the controller calls the model method to advance the current
animation tick inside this if statement the outer while loop while not finish if the animation is
paused. Once the pause is set to false again the while loop will continue as usual.
