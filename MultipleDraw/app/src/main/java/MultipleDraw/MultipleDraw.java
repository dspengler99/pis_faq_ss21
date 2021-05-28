// Original source code: https://gist.github.com/denkspuren/86e2132b6563d609902e
// Minor adaptations were required for a Java 13 environment.  

package MultipleDraw;

import processing.core.PApplet;
import processing.core.PGraphics;

public class MultipleDraw extends PApplet {
    
    public static void main(String[] args) {
        String[] appArgs = {"Grab Ball"};
		MultipleDraw multipleDraw = new MultipleDraw();
		PApplet.runSketch(appArgs, multipleDraw);
    }

    int black = color(0, 0, 0);
    
    int maxWidth = 400;
    int maxHeight = 300;
    
    DrawableBall ball = new DrawableBall(0, maxHeight / 2);
    
    
    public void settings() {
        size(maxWidth, maxHeight);
      }

    public void setup() {
        background(black);
        noStroke();
    }

    // Needs to be outside method scope for not loosing value after method finishes
    float direction = 0; // Movement direction is evaluated inside draw-method
        
    public void draw() {
        background(black);
        if(ball.getX() + 1 == maxWidth) {
            direction = -1;
        } else if(ball.getX() == 0) {
            direction = 1;
        }
        ball.setX(ball.getX() + direction);
        ball.toDraw(super.g);
    }
}