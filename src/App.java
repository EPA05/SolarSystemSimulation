import ScreenClasses.ScreenManager;
import processing.core.*;

public class App extends PApplet {
    ScreenManager sm;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1600, 900, P2D);
        sm = new ScreenManager(this);
        smooth(8);
    }

    public void setup() {
        background(0);
        frameRate(60);

    }

    public void draw() {
        sm.run();
        System.out.println(frameRate);

    }
}
