/**
 * This is abstract ActiveActor class which is designed for main actors (like Gatherer and Thief).
 * This class inheritances Actor class.
 */
public abstract class ActiveActor extends Actor {

    private String direction;
    private boolean carrying;
    private boolean active;

    /**
     * Create an ActiveActor with an image, a name and xy coordinates.
     * @param filename This is the image file that objects need.
     * @param type     This is the name of object.
     * @param x        This records the initial x-coordinate.
     * @param y        This records the initial y-coordinate.
     */
    public ActiveActor(String filename, String type, int x, int y) {
        super(filename, type, x, y);
    }

    /**
     * This method is used to get the ActiveActor's direction.
     * @return This gives the object's current direction.
     */
    public String currentDirection() {
        return direction;
    }

    /**
     * This method is used to get the ActiveActor's carrying status.
     * @return This gives the object's current carrying status.
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * This method is used to get the ActiveActor's active status.
     * @return This gives the object's current active status.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is used to set the ActiveActor's direction.
     * @param direction This updates the object's direction.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * This method is used to set the ActiveActor's carrying status.
     * @param carrying This updates the object's carrying status.
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * This method is used to set the ActiveActor's active status.
     * @param active This updates the object's active status.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method is used to move an ActiveActor in one TILE_SIZE depending on its direction.
     */
    public void move() {
        switch (direction) {
            case "Up":
                setY(getY() - ShadowLife.TILE_SIZE);
                break;
            case "Down":
                setY(getY() + ShadowLife.TILE_SIZE);
                break;
            case "Right":
                setX(getX() + ShadowLife.TILE_SIZE);
                break;
            case "Left":
                setX(getX() - ShadowLife.TILE_SIZE);
                break;
        }
    }

    /**
     * This method is used to change the direction of an ActiveActor.
     * @param degree    The degree of rotation.
     * @param clockwise The direction of rotation.
     */
    public void changeDirection(int degree, String clockwise) {

        String newDirection = "";

        if (clockwise.equals("Clockwise")) {
            switch (direction) {
                case "Up":
                    switch (degree) {
                        case 90:
                            newDirection = "Right";
                            break;
                        case 180:
                            newDirection = "Down";
                            break;
                        case 270:
                            newDirection = "Left";
                            break;
                    }
                    break;
                case "Right":
                    switch (degree) {
                        case 90:
                            newDirection = "Down";
                            break;
                        case 180:
                            newDirection = "Left";
                            break;
                        case 270:
                            newDirection = "Up";
                            break;
                    }
                    break;
                case "Down":
                    switch (degree) {
                        case 90:
                            newDirection = "Left";
                            break;
                        case 180:
                            newDirection = "Up";
                            break;
                        case 270:
                            newDirection = "Right";
                            break;
                    }
                    break;
                case "Left":
                    switch (degree) {
                        case 90:
                            newDirection = "Up";
                            break;
                        case 180:
                            newDirection = "Right";
                            break;
                        case 270:
                            newDirection = "Down";
                            break;
                    }
                    break;
            }
        }
        else if (clockwise.equals("CounterClockwise")) {
            switch (direction) {
                case "Up":
                    switch (degree) {
                        case 90:
                            newDirection = "Left";
                            break;
                        case 180:
                            newDirection = "Down";
                            break;
                        case 270:
                            newDirection = "Right";
                            break;
                    }
                    break;
                case "Right":
                    switch (degree) {
                        case 90:
                            newDirection = "Up";
                            break;
                        case 180:
                            newDirection = "Left";
                            break;
                        case 270:
                            newDirection = "Down";
                            break;
                    }
                    break;
                case "Down":
                    switch (degree) {
                        case 90:
                            newDirection = "Right";
                            break;
                        case 180:
                            newDirection = "Up";
                            break;
                        case 270:
                            newDirection = "Left";
                            break;
                    }
                    break;
                case "Left":
                    switch (degree) {
                        case 90:
                            newDirection = "Down";
                            break;
                        case 180:
                            newDirection = "Right";
                            break;
                        case 270:
                            newDirection = "Up";
                            break;
                    }
                    break;
            }

        }
        direction = newDirection;
    }
}
