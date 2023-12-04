import ScreenClasses.ScreenManager;
import processing.core.*;

public class App extends PApplet {
    ScreenManager sm;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1600, 900);
        sm = new ScreenManager(this);
    }

    public void setup() {
        background(0);
        frameRate(24);
    }

    public void draw() {
        sm.run();
    }
}
