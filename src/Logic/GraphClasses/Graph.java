package Logic.GraphClasses;

import processing.core.*;
import Logic.Planet;
import Logic.Sun;

public class Graph {
  PApplet p;
  Planet planet;
  Sun sun;
  int graphWidth;
  int graphHeight;
  int graphX;
  int graphY;
  int distanceFromEdge;
  int borderWidth;
  final double G = 6.67428e-11;
  float v;
  float r;

  float[] planetDistances = { 0.39f, 0.72f, 1.0f, 1.52f, 5.20f, 9.58f, 19.18f, 30.07f }; // in AU
  float[] planetVelocities = { 47.36f, 35.02f, 29.78f, 24.07f, 13.07f, 9.68f, 6.80f, 5.43f }; // Observed velocity in
                                                                                              // km/s
  int[] planetColours;

  public Graph(PApplet p, Sun sun) {
    this.p = p;
    this.sun = sun;
    graphWidth = 600;
    graphHeight = 400;
    graphX = 20;
    graphY = 20;
    distanceFromEdge = 80;
    borderWidth = 2;

    planetColours = new int[] {
        p.color(112, 128, 144), p.color(255, 198, 73), p.color(0, 0, 255),
        p.color(156, 46, 53), p.color(176, 127, 53), p.color(253, 229, 34),
        p.color(178, 214, 219), p.color(142, 195, 195) }; // Colours of the planets
  }

  public void drawGraphBackground() {
    // Draw the graph background
    p.fill(0, 200);
    p.rect(graphX, graphY, graphWidth, graphHeight);

    // Draw the graph border
    p.stroke(255);
    p.strokeWeight(borderWidth);
    p.noFill();
    p.rect(graphX, graphY, graphWidth, graphHeight);

    // Draw the x-axis
    p.stroke(255, 0, 0); // Set stroke color to red
    p.line(graphX + distanceFromEdge, graphY + graphHeight - distanceFromEdge,
        graphX + graphWidth - distanceFromEdge,
        graphY + graphHeight - distanceFromEdge);
    p.fill(255);
    p.textSize(20);
    p.text("Distance [AU]", graphWidth / 2 - 30, graphY + graphHeight - 30);

    // Draw the y-axis
    p.stroke(0, 0, 255); // Set stroke color to blue
    p.line(graphX + distanceFromEdge, graphY + distanceFromEdge, graphX + distanceFromEdge,
        graphY + graphHeight - distanceFromEdge);
    p.fill(255);
    p.pushMatrix(); // Save the current transformation matrix
    p.translate(graphX + 20, graphY + graphHeight / 2 + 70); // Translate to the text position
    p.rotate(-PApplet.HALF_PI); // Rotate the text by -90 degrees
    p.text("Velocity [km/s]", 0, 0); // Draw the rotated text
    p.popMatrix(); // Restore the previous transformation matrix

    // Draw the vertical lines on the x-axis
    p.textSize(15);
    p.stroke(0, 255, 0);
    int numVerticalLines = 8;
    float verticalLineSpacing = (float) (graphWidth - 2 * distanceFromEdge) / (numVerticalLines - 1);
    for (int i = 0; i < numVerticalLines; i++) {
      float x = graphX + distanceFromEdge + i * verticalLineSpacing;
      p.line(x, graphY + graphHeight - distanceFromEdge, x, graphY + graphHeight - distanceFromEdge + 5);
      p.text((i * 5) + " AU", x - 10, graphY + graphHeight - distanceFromEdge + 20); // Add text at each line
    }

    // Draw the horizontal lines on the y-axis
    p.textSize(15);
    p.stroke(255, 255, 0);
    int numHorizontalLines = 7;
    float horizontalLineSpacing = (float) (graphHeight - 2 * distanceFromEdge) / (numHorizontalLines - 1);
    for (int i = 0; i < numHorizontalLines; i++) {
      float y = graphY + graphHeight - distanceFromEdge - i * horizontalLineSpacing;
      p.line(graphX + distanceFromEdge, y, graphX + distanceFromEdge - 5, y);
      p.text((i * 10) + " km/s", graphX + distanceFromEdge - 55, y + 5); // Add text at each line
    }
    p.noStroke();
  }

  private boolean graphCalculated = false;
  private PShape graphShape;

  public void drawGraph(Sun sun) {
    p.stroke(255, 0, 255); // Set stroke color to magenta
    p.noFill();

    if (!graphCalculated) {
      graphShape = p.createShape();
      graphShape.beginShape();
      for (r = (float) 3.685e10; r < 5.2359e12; r += 1e9) {
        v = (float) (Math.sqrt((G * sun.mass) / r)) / 1000;
        float rInAu = (float) (r / 1.496e11);
        float mappedV = PApplet.map(v, 0, 60, 0, graphHeight - 2 * distanceFromEdge);
        float mappedR = PApplet.map(rInAu, 0, 35, 0, graphWidth - 2 * distanceFromEdge);
        graphShape.vertex(mappedR, -mappedV);
      }
      graphShape.endShape();
      graphCalculated = true;
    }
    p.pushMatrix();
    p.translate(graphX + distanceFromEdge, graphY + graphHeight - distanceFromEdge);
    p.shape(graphShape);
    drawRealVelocities();
    p.popMatrix();
    p.noStroke();
  }

  public void drawRealVelocities() {
    for (int i = 0; i < planetDistances.length; i++) {
      float mappedR = PApplet.map(planetDistances[i], 0, 35, 0, graphWidth - 2 * distanceFromEdge);
      float mappedV = PApplet.map(planetVelocities[i], 0, 60, 0, graphHeight - 2 * distanceFromEdge);
      p.stroke(planetColours[i]);
      p.fill(planetColours[i]);
      p.ellipse(mappedR, -mappedV, 5, 5); // Draw a circle with a diameter of 10
      p.noStroke();
    }
  }

  public void graphinformation() {
    p.textSize(15);
    String[] planetNames = { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };
    int yOffset = 90;
    for (int i = 0; i < planetNames.length; i++) {
      p.fill(planetColours[i]);
      p.circle(graphX + graphWidth - 120, graphY + yOffset - 5, 5);
      p.text(planetNames[i], graphX + graphWidth - 100, graphY + yOffset);
      yOffset += 20;
    }
  }
}
