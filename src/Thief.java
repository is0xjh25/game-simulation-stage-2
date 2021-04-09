/**
 * This is Thief class which inheritances ActiveActor class.
 */
public class Thief extends ActiveActor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Thief";
    private static final String IMAGE = "res/images/thief.png";
    private boolean consuming;

    /**
     * Create a Thief with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Thief(int x, int y) {
        super(IMAGE, TYPE, x, y);
        setDirection("Up");
        setActive(true);
        setCarrying(false);
        setConsuming(false);
    }

    /**
     * This method is used to get the thief's consuming status .
     * @return This gives the object's current consuming.
     */
    public boolean isConsuming() {return consuming;}

    /**
     * This method is used to set the thief's consuming status .
     * @param consuming This updates the object's consuming.
     */
    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }

    /**
     * This method is used to change the ActiveActor which meets the actor .
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {}
}