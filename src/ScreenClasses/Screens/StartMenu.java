package ScreenClasses.Screens;

import processing.core.*;
import ScreenClasses.Screen;
import ScreenClasses.ScreenManager;

public class StartMenu extends Screen {

  PApplet p;
  ScreenManager sm;

  public StartMenu(PApplet p, ScreenManager sm) {
    this.p = p;
    this.sm = sm;
  }

  public void show() {
    p.background(0);
    p.textSize(40);
    p.textAlign(PApplet.CENTER);
    p.text("Click anywhere to start the simulation", p.width / 2, p.height / 2);
    p.textAlign(PApplet.LEFT, PApplet.BASELINE);
  }

  public void update() {
    show();

    if (p.mousePressed) {
      sm.setScreen(new Simulation(p, sm));
    }
  }
}
