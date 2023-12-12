package Logic.GraphClasses;

import processing.core.*;
import Logic.Sun;
import Logic.Planet;

public class GraphManager {
  PApplet p;
  Graph graph;
  Sun sun;
  Planet planet;

  public GraphManager(PApplet p) {
    this.p = p;
    this.sun = new Sun(p, 0, planet);
    this.planet = new Planet(p, 0, 0, 0, 0, sun); // Initialize the planet variable using the appropriate constructor
    graph = new Graph(p, sun, planet);
  }

  public void draw() {
    graph.drawGraphBackground();
    graph.drawGraph(sun);
    graph.graphinformation();
  }

}
