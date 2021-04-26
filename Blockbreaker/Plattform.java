import processing.core.PGraphics;

public class Plattform {

    float x, y;
    public static final float width = 75.0f;
    public static final float height = 10.0f;

    Plattform(float initialX, float initialY) {
        x = initialX;
        y = initialY;
        }

    public void draw(PGraphics g) {
        g.fill(g.color(255, 255, 255));
        g.rect(x, y, width, height);
    }

    public void move(float value) {
        if(x+value+width>Blockbreaker.windowWidth || x+value<0) return;
        x+=value;
    }
}