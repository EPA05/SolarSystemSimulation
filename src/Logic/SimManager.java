package Logic;

import processing.core.PApplet;
import processing.core.PVector;

public class SimManager {
  PApplet p;
  Sun sun;
  Planet mercury;

  public SimManager(PApplet p) {
    this.p = p;
    sun = new Sun(p, 10, mercury);
    mercury = new Planet(p, 4.869e24, 5.79e10, p.color(255, 192, 73), sun);
  }

  public void run() {
    p.background(0);
    PVector force = sun.attract(mercury);
    mercury.applyForce(force);
    mercury.update();

    sun.show();
    mercury.show();
  }
}