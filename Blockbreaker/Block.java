import processing.core.PGraphics;

public class Block {

    public float x, y, width, height;

    Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(PGraphics g) {
        g.fill(g.color(0, 255, 0));
        g.rect(x, y, width, height);
    }
}