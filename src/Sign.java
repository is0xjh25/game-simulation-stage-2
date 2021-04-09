/**
 * This is Sign class which inheritances Actor class.
 */
public class Sign extends Actor {

    /**
     * The name of the actor which is up sign specifically.
     */
    public static final String TYPE_SIGN_UP = "SignUp";

    /**
     * The name of the actor which is down sign specifically.
     */
    public static final String TYPE_SIGN_DOWN = "SignDown";

    /**
     * The name of the actor which is left sign specifically.
     */
    public static final String TYPE_SIGN_LEFT = "SignLeft";

    /**
     * The name of the actor which is right sign specifically.
     */
    public static final String TYPE_SIGN_RIGHT = "SignRight";

    private static final String IMAGE_UP = "res/images/up.png";
    private static final String IMAGE_DOWN = "res/images/down.png";
    private static final String IMAGE_LEFT = "res/images/left.png";
    private static final String IMAGE_RIGHT = "res/images/right.png";
    private final String direction;

    /**
     * Create a Sign with the initial xy coordinates and the sign's direction.
     * @param x This records the initial x-coordinate.
     * @param y This records the initial y-coordinate.
     * @param sign This determines the sign's direction.
     */
    public Sign(int x, int y, String sign) {
        super(determineImage(sign), sign, x, y);
        direction = sign.substring(4);
    }

    private static String determineImage(String input) {
        String image ="";
        switch (input) {
                case "SignUp":
                    image = IMAGE_UP;
                    break;
                case "SignDown":
                    image = IMAGE_DOWN;
                    break;
                case "SignLeft":
                    image = IMAGE_LEFT;
                    break;
                case "SignRight":
                    image = IMAGE_RIGHT;
                    break;
        }
        return image;
    }

    /**
     * This method is used to change the ActiveActor which meets the actor.
     * @param actor This ActiveActor is going to interact with the actor.
     */
    @Override
    public void interact(ActiveActor actor) {
        actor.setDirection(direction);
    }
}
