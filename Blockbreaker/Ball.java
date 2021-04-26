import processing.core.PGraphics;

public class Ball {

    public float x, y, dx, dy;
    public static final float width = 20.0f;
    public static final float height = 20.0f;

    Ball(float x, float y) {
        this.x = x;
        this.y = y;
        dx = 0.0f;
        dy = 0.0f;
    }

    public void move(float value) {
        x = value;
    }

    public void setFlightDirection(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void draw(PGraphics g) {
        if(x+(width/2)+dx >= Blockbreaker.windowWidth || x - (width / 2) <= 0.0f) dx *= -1;
        if(y - (height/2) - dy<=0) dy *= -1;
     x += dx;
        y-=dy;
        g.ellipse(x, y, width, height);
    }
}