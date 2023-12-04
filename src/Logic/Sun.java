package Logic;

import processing.core.*;

public class Sun {
  PApplet p;
  Planet planet;
  int radius; // Radius of the sun
  public double mass; // Mass of the sun in kg
  public PVector position; // Position of the sun
  final double G = 6.67428e-11; // Gravitational constant

  public Sun(PApplet p, int r, Planet planet) {
    this.p = p;
    radius = r;
    mass = 1.989e30;
    position = new PVector(p.width / 2, p.height / 2); // Position of the sun is in the center of the screen
    this.planet = planet;
  }

  public void show() {
    p.fill(255, 128, 0);
    p.circle(position.x, position.y, radius); // Draw the sun

  }

  PVector attract(Planet planet) {
    PVector force = PVector.sub(position, planet.position); // Calculate the direction of the force
    double distance = force.mag() / planet.pixelDistance; // Distance between objects
    force.normalize();
    double GravitationalForce = (G * mass * planet.mass) / (distance * distance); // Calculate the gravitational force
    force.mult((float) GravitationalForce); // Apply the force
    return force;
  }

}
