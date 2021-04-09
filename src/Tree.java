/**
 * This is Tree class which inheritances FruitActor class.
 */
public class Tree extends FruitActor {

    /**
     * The name of the actor.
     */
    public static final String TYPE = "Tree";
    private static final String IMAGE = "res/images/tree.png";

    /**
     * Create a Tree with the initial xy coordinates.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     */
    public Tree(int x, int y) {
        super(IMAGE, TYPE, x, y);
        setFruitNum(3);
    }

    /**
     * This method is used to change the ActiveActor which meets the actor .
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        if ((actor).type.equals("Gatherer")) {
            if (!actor.isCarrying() && getFruitNum() > 0) {
                setFruitNum(getFruitNum() - 1);
                actor.setCarrying(true);
                actor.changeDirection(180, "Clockwise");;
            }
        } else if (actor.type.equals("Thief")) {
            if (!actor.isCarrying() && getFruitNum() > 0) {
                setFruitNum(getFruitNum() - 1);
                actor.setCarrying(true);
            }
        }
    }
}
