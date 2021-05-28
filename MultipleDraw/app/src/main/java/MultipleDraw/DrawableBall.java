package MultipleDraw;

import processing.core.PApplet;
import processing.core.PGraphics;

class DrawableBall extends Ball {

    public DrawableBall(float x, float y) {
        super(x, y);
    }

    public void toDraw(PGraphics g) {
        g.fill(255);
        g.ellipse(getX(), getY(), 50, 50);
    }
}