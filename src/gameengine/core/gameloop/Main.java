package gameengine.core.gameloop;

import java.util.function.Consumer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static final float SCALE = 100f; /* pixels per meter */
    public static final float WIDTH = 12f; /* 12m */
    public static final float HEIGHT = 8f; /* 8m */

    @Override
    public void start(Stage stage)
    { 
    	Group root = new Group();
        Consumer<Float> updater = secondsElapsed -> update();
        Runnable renderer = () -> render();
//        Consumer<Float> interpolater = alpha -> activeGame.interpolatePositions(alpha);
        Consumer<Integer> fpsReporter = fps -> System.out.println(String.format("FPS: %d", fps));
        
        VariableSteps v = new VariableSteps(updater, renderer, fpsReporter);
//                new FixedSteps(updater, renderer, fpsReporter),
//                new FixedStepsWithInterpolation(updater, renderer, interpolater, fpsReporter)
       
        Scene scene = new Scene(root, WIDTH * SCALE, HEIGHT * SCALE);
        stage.setScene(scene);
        stage.setTitle("FX Game Loop");
        stage.setResizable(false);
        stage.show();
        v.start();
    }
    
    
    public static void main(String... args)
    {
       launch(args);
    }
    public void update() {
    	System.out.println("on update");
    }
    public void render() {
    	System.out.println("on dessine");
    }
}
