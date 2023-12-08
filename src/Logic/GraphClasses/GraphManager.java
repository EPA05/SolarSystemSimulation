package Logic.GraphClasses;

import processing.core.*;
import Logic.Sun;

public class GraphManager {
  PApplet p;
  Graph graph;
  Sun sun;

  public GraphManager(PApplet p) {
    this.p = p;
    graph = new Graph(p, sun);
  }

  public void draw() {
    graph.drawVelocityGraph();
    graph.drawGraph(sun);
  }

}
