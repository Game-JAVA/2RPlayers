import java.awt.*;

public class Pipe {

    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    private boolean passed;

    public Pipe(int x, int y, int width, int height, Image img ) {
        this.x =x;
        this.y =y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.passed = false;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;    
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}