package Logic.GraphClasses;

import processing.core.*;
import Logic.Planet;
import Logic.SimManager;
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

  public Graph(PApplet p, Sun sun) {
    this.p = p;
    this.sun = sun;
    graphWidth = 500;
    graphHeight = 400;
    graphX = 20;
    graphY = 20;
    distanceFromEdge = 60;
    borderWidth = 2;
  }

  public void drawVelocityGraph() {
    // Set up the graph parameters

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
    p.line(graphX + distanceFromEdge, graphY + graphHeight - distanceFromEdge, graphX + graphWidth - distanceFromEdge,
        graphY + graphHeight - distanceFromEdge);
    p.fill(255);
    p.textSize(20);
    p.text("Distance [AU]", graphX + graphWidth / 2, graphY + graphHeight - 10);

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
      p.text((i * 10) + " km/s", graphX + distanceFromEdge - 50, y + 5); // Add text at each line

    }
    p.noStroke();
  }

  public void drawGraph(Sun sun) {

    // Draw the graph line based on the function v(r) = sqrt((G*M)*r^2)
    p.stroke(255, 255, 0); // Set stroke color to yellow
    p.noFill();
    p.beginShape();
    p.pushMatrix();
    p.translate(graphX + distanceFromEdge, graphY + graphHeight + distanceFromEdge);
    for (float r = 0; r < 4.5179e12; r += 1e6) {
      float v = (float) Math.sqrt((G * sun.mass) / Math.pow(r, 2));
      v = v / 1000; // Convert velocity from m/s to km/s
      float x = (float) (r / 4.5179e12);
      float y = graphY - distanceFromEdge + v;
      p.vertex(x, y);
      System.out.println(v);
    }
    p.popMatrix();
    p.endShape();
    p.noStroke();
  }
}
