/**
 * This is Hoard class which inheritances FruitActor class.
 */
public class Hoard extends FruitActor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Hoard";
    private static final String IMAGE = "res/images/hoard.png";

    /**
     * Create a Hoard with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Hoard(int x, int y) {
        super(IMAGE, TYPE, x, y);
        setFruitNum(0);
    }

    /**
     * This method is used to change the ActiveActor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if (actor.type.equals("Gatherer")) {
            if (actor.isCarrying()) {
                actor.setCarrying(false);
                setFruitNum(getFruitNum() + 1);
            }
            actor.changeDirection(180, "Clockwise");
        } else if (actor.type.equals("Thief")) {
            if (((Thief)actor).isConsuming()) {
                ((Thief)actor).setConsuming(false);
                if (!actor.isCarrying()) {
                    if (getFruitNum() > 0) {
                        actor.setCarrying(true);
                        setFruitNum(getFruitNum() - 1);
                    } else {
                        actor.changeDirection(90, "Clockwise");
                    }
                }
            } else if (actor.isCarrying()) {
                actor.setCarrying(false);
                setFruitNum(getFruitNum() + 1);
                actor.changeDirection(90, "Clockwise");
            }
        }
    }
}
