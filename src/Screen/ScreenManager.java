package Screen;

import processing.core.*;

public class ScreenManager {
  PApplet p;
  Sun sun;
  Planet mercury;

  public ScreenManager(PApplet p) {
    this.p = p;
    sun = new Sun(p, 10);
    mercury = new Planet(p, 4.869e24, 5.79e10, p.color(255, 192, 73));
  }

  public void run() {
    p.background(0);
    sun.show();
    mercury.show();
  }
}
