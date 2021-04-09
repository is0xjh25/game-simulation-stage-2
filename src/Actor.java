import bagel.Image;

/**
 * This is abstract Actor class which is designed for all types of actor.
 */
public abstract class Actor {

    /**
     * The name of the actor.
     */
    public final String type;
    private final Image image;
    private int x;
    private int y;

    /**
     * Create an Actor with an image, a name and xy coordinates.
     * @param filename This is the image file that objects needs.
     * @param type This is the name of the object.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * Each actor has its own way to change the ActiveActors (Gatherer and Thief)
     * @param actor This ActiveActor is going to interact with the actor.
     */
    public abstract void interact(ActiveActor actor);

    /**
     * This is the getter for x-coordinate.
     * @return This gives the actor's x-coordinate.
     */
    public int getX() { return x; }

    /**
     * This is the getter for y-coordinate.
     * @return This gives the actor's y-coordinate.
     */
    public int getY() { return y; }

    /**
     * This is the setter for x-coordinate.
     * @param x This updates the actor's x-coordinate.
     */
    public void setX(int x) {this.x = x; }

    /**
     * This is the setter for y-coordinate.
     * @param y This updates the actor's y-coordinate.
     */
    public void setY(int y) {this.y = y; }

    /**
     * This method is used to draw the image of the object.
     */
    public void render() { image.drawFromTopLeft(x, y); }

    /**
     * This method is used to see whether there is another actor standing on its location.
     * @param x This is the other object's x-coordinate.
     * @param y This is the other object's y-coordinate.
     * @return This give true or false.
     */
    public boolean equalsLocation (int x, int y) {
        return  x == this.x && y == this.y;
    }
}
