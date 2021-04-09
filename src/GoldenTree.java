/**
 * This is GoldenTree class which inheritances Actor class.
 */
public class GoldenTree extends Actor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "GoldenTree";
    private static final String IMAGE = "res/images/gold-tree.png";

    /**
     * Create a GoldenTree with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public GoldenTree(int x, int y) {
        super(IMAGE, TYPE, x, y);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if (actor.type.equals("Gatherer")) {
            if (!actor.isCarrying()) {
                actor.setCarrying(true);
                actor.changeDirection(180, "Clockwise");
            }
        } else if (actor.type.equals("Thief")) {
            if (!actor.isCarrying()) {
                actor.setCarrying(true);
            }
        }
    }
}
