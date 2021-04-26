import processing.core.PApplet;
import processing.core.PGraphics;


public class Blockbreaker extends PApplet {

    public static float windowWidth;
    public static float windowHeight;
    private boolean levelStarted = false;
    Plattform plattform;
    Ball ball;
    Block[] blocks;

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Blockbreaker());
    }

    public void settings() {
        size(700, 700);
    }

    public void setup() {
        background(0);
        windowWidth = width;
        windowHeight = height;
        createLevel();
    }

    public void createLevel() {
        plattform = new Plattform((width/2)-(Plattform.width/2), height - 30.0f);
        ball = new Ball(plattform.x+(Plattform.width/2), plattform.y-(Ball.height/2));
        int blocksPerRow = 20;
        int numberOfRows = 2;
        blocks = new Block[numberOfRows*blocksPerRow];

        float blockWidth = windowWidth / blocksPerRow;
        float blockHeight = 25.0f;
        for(int row = 0; row<numberOfRows; row++) {
            for(int column = 0; column<blocksPerRow; column++) {
                int arrayPosition = (blocksPerRow *row) + column;
                if(column==0) {
                    blocks[arrayPosition] = new Block(0.0f, row*blockHeight, blockWidth, blockHeight);
                } else {
                    blocks[arrayPosition] = new Block(blocks[arrayPosition - 1].x+blockWidth, row*blockHeight, blockWidth, blockHeight);
                }
            }
        }
    }

    private void setRandomFlightDirection() {
        ball.setFlightDirection(random(2)+2, random(4)+4);
    }

    private void setFlightDirection() {
        if(ball.x >= plattform.x && ball.x <= plattform.x + ((1.0f/5.0f)*plattform.width)) ball.setFlightDirection(-3.5f, 2.0f);
        else if(ball.x >= plattform.x + (1.0f/5.0f) * plattform.width && ball.x <= plattform.x + ((2.0f/5.0f) * plattform.width)) ball.setFlightDirection(-2.0f, 4.5f);
        else if(ball.x >= plattform.x + ((2.0f/5.0f) *plattform.width) && ball.x <= plattform.x + ((3.0f/5.0f) * plattform.width)) ball.setFlightDirection(0.0f, 7.0f);
        else if(ball.x >= plattform.x + ((3.0f/5.0f) * plattform.width) && ball.x <= plattform.x +((4.0f/5.0f)*plattform.width)) ball.setFlightDirection(2.0f, 2.0f);
        else if(ball.x >= plattform.x +((4.0f/5.0f) * plattform.width) && ball.x <= plattform.x + ((5.0f/5.0f) * plattform.width)) ball.setFlightDirection(3.5f, 4.5f);
    }

    private void startLevel() {
        if(levelStarted) return;
        else {
            if(keyPressed) {
                if(key == CODED) {
                    if(keyCode == UP) {
                        setRandomFlightDirection();
                        levelStarted = true;
                    }
                }
            }
        }
    }

    private void moveObjects() {
        if(keyPressed) {
            if(key == CODED) {
                switch(keyCode) {
                    case RIGHT: plattform.move(5); break;
                    case LEFT: plattform.move(-5); break;
                    default: break;
                }
                if(!levelStarted) ball.move(plattform.x + (plattform.width / 2));
            }
        }
    }

    public void drawBlocks() {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].draw(super.g);
        }
    }

    public void checkBlockCollision() {
        int blockIndex = -1;
        for(int i = 0; i<blocks.length; i++) {
            if(ball.y - (ball.height/2) <= blocks[i].y + blocks[i].height+ball.dy
            && ball.x >= blocks[i].x && ball.x <= blocks[i].x + blocks[i].width) {
                blockIndex = i;
                break;
            }
        }
        if(blockIndex != -1) {
            int remainingBlocks = blocks.length - 1;
            if(remainingBlocks == 0) {
                textSize(100);
                fill(color(0, 255, 0));
                text("YOU WON", 100.0f, height/2);
                noLoop();
                return;
            }
            ball.dy *= -1;
            Block[] tmp = new Block[remainingBlocks];
            boolean passedBlockIndex = false;
            for(int i = 0; i < tmp.length; i++) {
                if(!passedBlockIndex && i==blockIndex) {
                    passedBlockIndex = true;
                    i-=1;
                } else if(passedBlockIndex) tmp[i] = blocks[i+1];
                else tmp[i] = blocks[i];
            }
            blocks = tmp;
        }
    }

    public boolean checkPlattformTouch() {
        if(ball.y + (ball.height / 2) >= plattform.y
        && ball.x >= plattform.x && ball.x <= plattform.x + plattform.width) return true;
        else return false;
    }

    public void draw() {
        background(0);
        moveObjects();
        startLevel();
        checkBlockCollision();
        if(levelStarted && checkPlattformTouch()) setFlightDirection();
        else {
         if(ball.y + (ball.height / 2) >= plattform.y + plattform.height) {
             textSize(100);
             fill(color(0, 0, 255));
             text("GAME OVER", 100.0f, height/2);
             textSize(20);
             fill(255);
             text("Press r to play again", 100.0f, height - 20.0f);
             if(keyPressed) {
                 if(key == 'r') createLevel();
                 levelStarted = false;
             }
         }
        }
        plattform.draw(super.g);
        ball.draw(super.g);
        drawBlocks();
    }
}