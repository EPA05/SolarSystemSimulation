package Screen;

import processing.core.*;

public class Planet {
  PApplet p;
  double mass; // Mass of the planet in kg
  double distance; // Distance from the sun in km
  int colour; // Colour of the planet
  double pixelDistance; // Costant to convert distance from m to pixels
  PVector position; // Position of the planet

  public Planet(PApplet p, double m, double d, int c) {
    this.p = p;
    mass = m;
    distance = d;
    colour = c;
    pixelDistance = (p.height) / 4.5179e12; // Calculate the distance of one pixel in meter
    position = new PVector((float) (p.width / 2 + distance * pixelDistance), (float) (p.height / 2)); // Calculate the
                                                                                                      // start position
                                                                                                      // of the planet
  }

  void show() {
    p.fill(colour);
    p.circle(position.x, position.y, 8);
  }

}
