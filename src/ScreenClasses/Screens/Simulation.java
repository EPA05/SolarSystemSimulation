package ScreenClasses.Screens;

import ScreenClasses.Screen;
import ScreenClasses.ScreenManager;
import processing.core.*;
import Logic.SimManager;

public class Simulation extends Screen {
  PApplet p;
  private SimManager simManager;
  // private GraphManager graphManager;

  public Simulation(PApplet p, ScreenManager sm) {
    this.p = p;
    simManager = new SimManager(p);
    // graphManager = new GraphManager(p);
  }

  public void update() {
    simManager.run();
    // graphManager.draw();
  }
}
