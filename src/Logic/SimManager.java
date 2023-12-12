package Logic;

import Logic.GraphClasses.Graph;
import Logic.GraphClasses.GraphManager;
import processing.core.*;

public class SimManager {
  Planet[] planets;
  PApplet p;
  Sun sun;
  Planet mercury;
  TimeSlider timeSlider;
  GraphManager graph;
  boolean buttonPressed = false;

  public SimManager(PApplet p) {
    this.p = p;
    sun = new Sun(p, 10, mercury);
    timeSlider = new TimeSlider(p);
    planets = new Planet[8];
    graph = new GraphManager(p);

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
    textButton();
    timeSlider.show();
    timeSlider.update();
    graph.draw();

    // Loop that runs through all the planets and their gravitational force from the
    // sun and other planets
    for (int p = 0; p < timeSlider.getTime(); p++) {
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
    orbitalPeriodText();
  }

  void text() {
    p.fill(255);
    p.textSize(20);
    for (Planet planet : planets) {
      if (planet == planets[0]) {
        p.text("Mercury velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 30);
      }
      if (planet == planets[1]) {
        p.text("Venus velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 50);
      }
      if (planet == planets[2]) {
        p.text("Earth velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 70);
      }
      if (planet == planets[3]) {
        p.text("Mars velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 90);
      }
      if (planet == planets[4]) {
        p.text("Jupiter velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 110);
      }
      if (planet == planets[5]) {
        p.text("Saturn velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 130);
      }
      if (planet == planets[6]) {
        p.text("Uranus velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 150);
      }
      if (planet == planets[7]) {
        p.text("Neptune velocity: " + planet.getVelocity() + " m/s", p.width / 2 + 30, 170);
      }
    }
  }

  void textButton() {

    p.stroke(255);
    p.strokeWeight(2);
    p.fill(0);
    p.rect(p.width / 2 - 20, 20, 40, 40);
    p.noStroke();
    p.textSize(30);
    p.fill(255);
    p.text("V", p.width / 2 - 8, 50);
    if (p.frameCount % 5 == 0) {
      if (p.mouseX > p.width / 2 - 20 && p.mouseX < p.width / 2 + 40 - 20 && p.mouseY > 20 && p.mouseY < 20 + 40) {
        if (p.mousePressed && !buttonPressed) {
          buttonPressed = true;
        } else if (p.mousePressed && buttonPressed) {
          buttonPressed = false;
        }
      }
    }

    if (buttonPressed) {
      p.fill(255, 0, 0);
      text();
    }

  }

  void orbitalPeriodText() {
    p.fill(255);
    p.textSize(20);
    for (Planet planet : planets) {
      if (planet == planets[0]) {
        p.text("Mercury orbitalperiod : " + planet.getOrbitalPeriodInDays() + " days", 30, p.height - 170);
      }
      if (planet == planets[1]) {
        p.text("Venus orbitalperiod : " + planet.getOrbitalPeriodInDays() + " days", 30, p.height - 150);
      }
      if (planet == planets[2]) {
        p.text("Earth orbitalperiod : " + planet.getOrbitalPeriodInDays() + " days", 30, p.height - 130);
      }
      if (planet == planets[3]) {
        p.text("Mars orbitalperiod : " + planet.getOrbitalPeriodInDays() + " days", 30, p.height - 110);
      }
      if (planet == planets[4]) {
        p.text("Jupiter orbitalperiod : " + planet.getOrbitalPeriodInYears() + " years", 30, p.height - 90);
      }
      if (planet == planets[5]) {
        p.text("Saturn orbitalperiod : " + planet.getOrbitalPeriodInYears() + " years", 30, p.height - 70);
      }
      if (planet == planets[6]) {
        p.text("Uranus orbitalperiod : " + planet.getOrbitalPeriodInYears() + " years", 30, p.height - 50);
      }
      if (planet == planets[7]) {
        p.text("Neptune orbitalperiod : " + planet.getOrbitalPeriodInYears() + " years", 30, p.height - 30);
      }
    }
  }
}
