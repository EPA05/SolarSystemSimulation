package ScreenClasses.Screens;

import ScreenClasses.Screen;
import processing.core.*;
import Logic.SimManager;

public class Simulation extends Screen {
  PApplet p;
  private SimManager simManager;

  public Simulation(PApplet p) {
    this.p = p;
    simManager = new SimManager(p);
  }

  public void update() {
    simManager.run();
  }
}
