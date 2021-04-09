/**
 * This is Fence class which inheritances Actor class.
 */
public class Fence extends Actor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Fence";
    private static final String IMAGE = "res/images/fence.png";

    /**
     * Create a Fence with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Fence(int x, int y) {
        super(IMAGE, TYPE, x, y);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        actor.setActive(false);
        actor.changeDirection(180, "Clockwise");
        actor.move();
    }
}
