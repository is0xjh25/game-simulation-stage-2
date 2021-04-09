/**
 * This is Gatherer class which inheritances ActiveActor class.
 */
public class Gatherer extends ActiveActor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Gatherer";
    private static final String IMAGE = "res/images/gatherer.png";

    /**
     * Create a Gatherer with the initial xy coordinates.
     * @param x This records the initial x-coordinate
     * @param y This records the initial y-coordinate
     */
    public Gatherer(int x, int y) {
        super(IMAGE, TYPE, x, y);
        setDirection("Left");
        setActive(true);
        setCarrying(false);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if (actor.type.equals("Thief")) {
            actor.changeDirection(270, "Clockwise");
        }
    }
}
