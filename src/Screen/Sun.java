package Screen;

import processing.core.*;

public class Sun {
  PApplet p;
  int radius; // Radius of the sun
  double mass; // Mass of the sun in kg
  PVector position; // Position of the sun

  public Sun(PApplet p, int r) {
    this.p = p;
    radius = r;
    mass = 10e30;
    ;
    position = new PVector(p.width / 2, p.height / 2); // Position of the sun is in the center of the screen
  }

  public void show() {
    p.fill(255, 128, 0);
    p.circle(position.x, position.y, radius);// Draw the sun
  }
}
