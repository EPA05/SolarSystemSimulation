package Screen;

import processing.core.*;

public class ScreenManager {
  PApplet p;
  Sun sun;

  public ScreenManager(PApplet p) {
    this.p = p;
    sun = new Sun(p, 10);
  }

  public void run() {
    p.background(0);
    sun.show();
  }
}
