package Logic.GraphClasses;

import processing.core.*;
import Logic.Sun;

public class GraphManager {
  PApplet p;
  Graph graph;
  Sun sun;

  public GraphManager(PApplet p) {
    this.p = p;
    this.sun = new Sun(p, 0, null);
    graph = new Graph(p, sun);
  }

  public void draw() {
    graph.drawGraphBackground();
    graph.drawGraph(sun);
    graph.graphinformation();
  }

}
