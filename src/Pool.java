/**
 * This is Pool class which inheritances Actor class.
 */
public class Pool extends Actor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Pool";
    private static final String IMAGE = "res/images/pool.png";

    /**
     * Create a Pool with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Pool(int x, int y) {
        super(IMAGE, TYPE, x, y);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {

        ActiveActor copyOne = null;
        ActiveActor copyTwo = null;

        if (actor.type.equals("Gatherer")) {
            copyOne = new Gatherer(getX(), getY());
            copyTwo = new Gatherer(getX(), getY());
        }
        else if (actor.type.equals("Thief")) {
            copyOne = new Thief(getX(), getY());
            copyTwo = new Thief(getX(), getY());
        }

        // Ensure CopyOne and CopyTwo exist.
        assert copyOne != null;

        copyOne.setDirection(actor.currentDirection());
        copyTwo.setDirection(actor.currentDirection());
        copyOne.changeDirection(90, "Clockwise");
        copyTwo.changeDirection(90, "CounterClockwise");
        copyOne.move();
        copyTwo.move();
        ShadowLife.addActor(copyOne);
        ShadowLife.addActor(copyTwo);
        ShadowLife.removeActor(actor);
    }
}
