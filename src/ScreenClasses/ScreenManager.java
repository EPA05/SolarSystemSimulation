package ScreenClasses;

import ScreenClasses.Screens.Simulation;
import processing.core.*;

public class ScreenManager {
  private PApplet p;
  private Screen s;

  public ScreenManager(PApplet p) {
    this.p = p;
    s = new Simulation(this.p);
  }

  public void run() {
    s.update();
  }
}
