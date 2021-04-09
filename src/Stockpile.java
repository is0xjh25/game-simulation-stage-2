/**
 * This is Stockpile class which inheritances FruitActor class.
 */
public class Stockpile extends FruitActor{

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Stockpile";
    private static final String IMAGE = "res/images/cherries.png";

    /**
     * Create a Stockpile with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Stockpile(int x, int y) {
        super(IMAGE, TYPE, x, y);
        setFruitNum(0);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if (actor.type.equals("Gatherer")) {
            if (actor.isCarrying()) {
                actor.setCarrying(false);
                setFruitNum(getFruitNum() + 1);
            }
            actor.changeDirection(180, "Clockwise");;
        }
        else if (actor.type.equals("Thief")) {
            if (!actor.isCarrying()) {
                if (getFruitNum() > 0) {
                    actor.setCarrying(true);
                    ((Thief)actor).setConsuming(false);
                    setFruitNum(getFruitNum() - 1);
                    actor.changeDirection(90, "Clockwise");
                }
            } else {
                actor.changeDirection(90, "Clockwise");
            }
        }
    }
}
