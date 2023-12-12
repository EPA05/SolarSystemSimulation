package ScreenClasses;

import ScreenClasses.Screens.StartMenu;
import processing.core.*;

public class ScreenManager {
  PApplet p;
  private Screen s;

  public ScreenManager(PApplet p) {
    this.p = p;
    s = new StartMenu(p, this);
  }

  public void run() {
    s.update();
  }

  public void setScreen(Screen s) {
    this.s = s;
  }
}
