package Logic;

import processing.core.*;

public class Planet {

  PApplet p;
  Sun sun;
  public double mass; // Mass of the planet in kg
  public double distance; // Distance from the sun in m
  double startVelocity;
  int colour; // Colour of the planet
  public double pixelDistance; // Costant to convert distance from m to pixels
  public PVector position; // Position of the planet
  public PVector velocity; // Velocity of the planet
  double GravitationalForce; // Gravitational force between the planet and the sun
  int timeConstant; // Time constant to speed up the simulation
  PVector acceleration; // Acceleration of the planet
  final double G = 6.67428e-11; // Gravitational constant

  public Planet(PApplet p, double m, double d, int c, Sun sun) {
    this.p = p;
    this.sun = sun;
    mass = m;
    distance = d;
    colour = c;
    pixelDistance = (p.height) / 4.5179e12; // Calculate the distance of one pixel in meter
    timeConstant = (int) ((60 * 60 * 24) / p.frameRate); // Calculate the time constant to speed up the simulation to 1
                                                         // day per second
    acceleration = new PVector(0, 0);
    velocity = new PVector(0, 0);
    position = new PVector((float) (p.width / 2 + distance * pixelDistance), (float) (p.height / 2));// Calculate the
                                                                                                     // start position
                                                                                                     // of the planet

  }

  void update() {
    velocity(sun);
    velocity.add(PVector.mult(acceleration, timeConstant)); // Add the acceleration to the velocity
    position.add(PVector.mult(velocity, timeConstant)); // Add the velocity to the position
    acceleration.mult(0); // Reset the acceleration so it doesn't accumulate

  }

  void show() {
    p.fill(colour); // Set the colour of the planet
    p.circle(position.x, position.y, 8); // Draw the planet

  }

  void velocity(Sun sun) {
    if (velocity.mag() == 0) { // Check if velocity has already been calculated
      double orbitalSpeed = Math.sqrt((G * sun.mass) / (distance)); // Calculate the orbital speed of the planet
      PVector directionToSun = PVector.sub(sun.position, position); // Vector from planet to sun
      directionToSun.normalize(); // Normalize to get a unit vector
      PVector VelocityDirection = directionToSun.rotate(PConstants.HALF_PI); // Rotate 90 degrees to get a
      // perpendicular
      velocity = VelocityDirection.mult((float) orbitalSpeed); // Calculate the velocity of the planet
      velocity.mult((float) pixelDistance); // Convert velocity from m/s to pixels/s
    }
  }

  void applyForce(PVector force) {
    PVector acc = PVector.div(force, (float) mass); // Calculate the acceleration
    acceleration.add(acc); // Add the acceleration to the acceleration vector
    acceleration.mult((float) pixelDistance); // Convert acceleration from m/s^2 to pixels/s^2
  }

  PVector attract(Planet planet) {
    PVector force = PVector.sub(position, planet.position); // Calculate the direction of the force
    double distance = force.mag() / planet.pixelDistance; // Distance between objects
    force.normalize();
    double GravitationalForce = (G * mass * planet.mass) / (distance * distance); // Calculate the gravitational force
    force.mult((float) GravitationalForce); // Apply the force
    return force;
  }

  // Function to get the velocity of the planet
  public int getVelocity() {
    return (int) (velocity.mag() / pixelDistance);
  }

  public int getAverageVelocity() {
    int totalVelocity = 0; // Initialize totalVelocity variable
    totalVelocity += getVelocity();
    int averageVelocity = totalVelocity / p.frameCount / 1000;
    return averageVelocity;
  }
}