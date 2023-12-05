package Logic;

import processing.core.PApplet;
import processing.core.PVector;

public class SimManager {
  Planet[] planets;
  PApplet p;
  Sun sun;
  Planet mercury;

  public SimManager(PApplet p) {
    this.p = p;
    sun = new Sun(p, 10, mercury);
    planets = new Planet[8];

    // Mercury
    planets[0] = new Planet(p, 4.869e24, 5.79e10, p.color(112, 128, 144), sun);

    // Venus
    planets[1] = new Planet(p, 4.868e24, 1.08e11, p.color(255, 198, 73), sun);

    // Earth
    planets[2] = new Planet(p, 5.972e24, 1.50e11, p.color(0, 0, 255), sun);

    // Mars
    planets[3] = new Planet(p, 6.471e23, 2.28e11, p.color(156, 46, 53), sun);

    // Jupiter
    planets[4] = new Planet(p, 1.898e27, 7.78e11, p.color(176, 127, 53), sun);

    // Saturn
    planets[5] = new Planet(p, 5.683e26, 1.4e12, p.color(253, 229, 34), sun);

    // Uranus
    planets[6] = new Planet(p, 6.833e25, 3.00e12, p.color(178, 214, 219), sun);

    // Neptune
    planets[7] = new Planet(p, 1.024e26, 4.5e12, p.color(142, 195, 195), sun);
  }

  public void run() {
    p.background(0);
    sun.show();

    // Loop that runs through all the planets and their gravitational force from the
    // sun and other planets
    for (int i = 0; i < planets.length; i++) {
      for (int j = 0; j < planets.length; j++) {
        if (i != j) {
          PVector force = planets[j].attract(planets[i]);
          planets[i].applyForce(force);
        }
      }
      PVector force = sun.attract(planets[i]);
      planets[i].applyForce(force);
      planets[i].update();
      planets[i].show();
    }
  }
}