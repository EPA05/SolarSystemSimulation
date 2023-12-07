package Logic;

import processing.core.*;

public class TimeSlider {
  PApplet p;
  int sliderX, sliderY; // Position of the slider
  int sliderWidth; // Width of the slider
  int sliderHeight; // Height of the slider
  int barX, barY; // Position of the bar
  int barWidth, barHeight; // Width and height of the bar
  int time; // Time in days
  float timePixelCostant; // Costant to convert pixels to time

  public TimeSlider(PApplet p) {
    this.p = p;
    barX = 1250;
    barY = 50;
    barHeight = 30;
    barWidth = 200;
    sliderX = barX + sliderWidth / 2;
    sliderY = barY;
    sliderWidth = 10;
    sliderHeight = 30;

  }

  public void show() {
    p.fill(0, 120, 255);
    p.rect(barX, barY, barWidth, barHeight, 15);
    p.fill(255);
    p.rect(sliderX, sliderY, sliderWidth, sliderHeight, 15);
    p.textSize(20);
    p.text("1 Day", 1200, 70);
    p.textSize(24);
    p.text("365 Days", 1460, 70);
    p.text("Days passed per second: " + (int) time, 1250, 30);
  }

  public void update() {
    mousePressed();
    calculateTime();
  }

  // Method to check if the mouse is pressed and is insider the bar
  void mousePressed() {
    if (p.mousePressed == true)
      // Checks if the mouse is inside my sliders boundaries
      if (p.mouseX > barX && p.mouseX < barX + barWidth - sliderWidth / 2 && p.mouseY > barY
          && p.mouseY < barY + sliderHeight) {
        sliderX = p.mouseX; // Sets the position of the slider to the mouse
      }
  }

  void calculateTime() {
    timePixelCostant = (float) (365.0 / (barWidth - sliderWidth / 2)); // Calculate how many days each pixel is worth
    time = (int) ((sliderX - barX) * timePixelCostant) + 1; // Calculate time based on slider position
  }

  int getTime() {
    return time;
  }
}
