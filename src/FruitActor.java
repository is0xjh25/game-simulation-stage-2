import bagel.Font;

/**
 * This is abstract FruitActor class which is designed for actors containing fruit (like Tree and Hoard).
 * This class inheritances Actor class.
 */
public abstract class FruitActor extends Actor {

    private int fruitNum;

    /**
     * Create an FruitActor with an image, a name and xy coordinates.
     * @param filename This is the image file that objects need.
     * @param type This is the name of object.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public FruitActor(String filename, String type, int x, int y) {
        super(filename, type, x, y);
    }

    /**
     * This method is used to get the number of fruit.
     * @return This gives the object's current fruit number.
     */
    public int getFruitNum() {
        return fruitNum;
    }

    /**
     * This method is used to set the number of fruit.
     * @param fruitNum This updates the object's current fruit number.
     */
    public void setFruitNum(int fruitNum) {
        this.fruitNum = fruitNum;
    }

    /**
     * This method is used to render object's image and the its fruit number.
     */
    @Override
    public void render() {
        super.render();
        // A FruitActor has to draw its containing fruit number.
        Font font = new Font(ShadowLife.FONT_FILE, ShadowLife.FONT_SIZE);
        font.drawString(Integer.toString(fruitNum), getX(), getY());
    }
}
