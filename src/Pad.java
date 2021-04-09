/**
 * This is Pad class which inheritances Actor class.
 */
public class Pad extends Actor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Pad";
    private static final String IMAGE = "res/images/pad.png";

    /**
     * Create a Pad with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Pad(int x, int y) {
        super(IMAGE, TYPE, x, y);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if (actor.type.equals("Thief")) {
            ((Thief)actor).setConsuming(true);
        }
    }
}
