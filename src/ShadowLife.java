import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * SWEN20003-Project-2B
 * @author Yun-Chi Hsiao #1074004
 */
public class ShadowLife extends AbstractGame {

    /**
     * The pixel per tile.
     */
    public static final int TILE_SIZE = 64;

    /**
     * The size of font.
     */
    public static final int FONT_SIZE = 16;

    /**
     * The format of font.
     */
    public static final String FONT_FILE = "res/VeraMono.ttf";

    private final Image background = new Image("res/images/background.png");
    private static final ArrayList<Actor> actors = new ArrayList<>();
    private final String WORLD_FILE;
    private final int TICK_RATE;
    private final int MAX_TICKS;
    private long lastTick = 0;
    private int tickCount = 0;

    /**
     * Create a ShadowLife game.
     */
    public ShadowLife() {

        String[] args = argsFromFile();

        if (args == null) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        TICK_RATE = Integer.parseInt(argsValid(args)[0]);
        MAX_TICKS = Integer.parseInt(argsValid(args)[1]);
        WORLD_FILE = argsValid(args)[2];

        loadActors();
    }

    // Read command line from args.txt (MacOS issue).
    private static String[] argsFromFile() {

        try {
            return Files.readString(Path.of("args.txt"),
                    Charset.defaultCharset()).split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Catch command line errors.
    private String[] argsValid(String[] args) {

        if (args.length != 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        try{
            int temp = Integer.parseInt(args[0]);
            if (temp < 0) {
                System.out.println("usage: ShadowLife <tick rate>");
                System.exit(-1);
            }
        }catch (NumberFormatException e) {
            System.out.println("usage: ShadowLife <tick rate>");
            System.exit(-1);
        }

        try{
            int temp = Integer.parseInt(args[1]);
            if (temp < 0) {
                System.out.println("usage: ShadowLife <max ticks>");
                System.exit(-1);
            }
        }catch (NumberFormatException e) {
            System.out.println("usage: ShadowLife <max ticks>");
            System.exit(-1);
        }

        return args;
    }

    /**
     * This method is used to add an actor into the ArrayList.
     * @param actor This is the actor which will be added into the actor ArrayList.
     */
    public static void addActor(Actor actor) {
        actors.add(actor);
    }

    /**
     * This method is used to remove an actor from the ArrayList.
     * @param actor This is the actor which will be removed from the actor ArrayList.
     */
    public static void removeActor(Actor actor) {
        actors.remove(actor);
    }

    // Reference to project-1 sample solution.
    private void loadActors() {
        // Line number in the world file.
        int lineNum = 1;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(WORLD_FILE))) {

            while ((line = reader.readLine()) != null) {

                try {

                    Actor newActor = null;
                    String[] parts = line.split(",");
                    String type = parts[0];
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);

                    if (parts.length != 3 || x < 0 || y < 0) {
                        System.out.format("error: in file \"%s\" at line %d\n", WORLD_FILE, lineNum);
                        System.exit(-1);
                    }

                    switch (type) {
                        case Gatherer.TYPE:
                            newActor = new Gatherer(x, y);
                            break;
                        case Thief.TYPE:
                            newActor = new Thief(x, y);
                            break;
                        case Tree.TYPE:
                            newActor = new Tree(x, y);
                            break;
                        case GoldenTree.TYPE:
                            newActor = new GoldenTree(x, y);
                            break;
                        case Stockpile.TYPE:
                            newActor = new Stockpile(x, y);
                            break;
                        case Hoard.TYPE:
                            newActor = new Hoard(x, y);
                            break;
                        case Pool.TYPE:
                            newActor = new Pool(x, y);
                            break;
                        case Fence.TYPE:
                            newActor = new Fence(x, y);
                            break;
                        case Pad.TYPE:
                            newActor = new Pad(x, y);
                            break;
                        // Four different sign in one class.
                        case Sign.TYPE_SIGN_UP:
                        case Sign.TYPE_SIGN_DOWN:
                        case Sign.TYPE_SIGN_LEFT:
                        case Sign.TYPE_SIGN_RIGHT:
                            newActor = new Sign(x, y, type);
                            break;
                        // Invalid name for actor.
                        default:
                            System.out.format("error: in file \"%s\" at line %d\n", WORLD_FILE, lineNum);
                            System.exit(-1);
                    }
                    addActor(newActor);
                    lineNum++;
                  // Catch error when Part[0] or Part[1] can't be convert to Integer.
                } catch (NumberFormatException e) {
                    System.out.format("error: in file \"%s\" at line %d\n", WORLD_FILE, lineNum);
                    System.exit(-1);
                }
            }
        }  catch (FileNotFoundException e) {
            System.out.format("error: file \"%s\" not found", WORLD_FILE);
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionUpdate() {
        // Move all active actors
        for (Actor actor : actors) {
            if (actor instanceof ActiveActor && ((ActiveActor) actor).isActive()) {
                    ((ActiveActor) actor).move();
            }
        }

        int n = actors.size();
        /* Check ActiveActor whether is standing on the other actors.
        If yes, the active actor will execute the action depending on the other actor. */
        for (int i = 0; i < n; i++) {
            if (actors.get(i) instanceof ActiveActor && ((ActiveActor) actors.get(i)).isActive()) {
                int j = 0;
                while (j < actors.size()) {
                    if (actors.get(i).equalsLocation(actors.get(j).getX(), actors.get(j).getY())) {
                        actors.get(j).interact((ActiveActor) actors.get(i));
                        /* Since the ActiveActor will be removed if it meets Pool, the order of actors in the ArrayList
                        will be changed as well. It needs to check new i--, otherwise it will miss a tick. */
                        if (actors.get(j).type.equals("Pool")) {
                            // Since the ActiveActor will be destroy, to the next one will be actor.get(i--).
                            i--;
                            // The ArrayList size in this particularly tick should minus one.
                            n--;
                            break;
                        }
                    }
                    j++;
                }
            }
        }
    }

    // Check at least one actor is acting
    private boolean acting() {
        for (Actor actor : actors) {
            if (actor instanceof ActiveActor && ((ActiveActor) actor).isActive()) {
                return true;
            }
        }
        return false;
    }

    private boolean timeOut(){
        return tickCount >= MAX_TICKS;
    }

    private void gameResult() {
        if (timeOut()) {
            System.out.println("Timed Out");
            System.exit(-1);
        } else {
            System.out.println(tickCount + " ticks");
            for (Actor actor : actors) {
                if (actor.type.equals("Stockpile") || actor.type.equals("Hoard")) {
                    System.out.println(((FruitActor)actor).getFruitNum());
                }
            }
            System.exit(0);
        }
    }

    private void renderAll() {
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            actor.render();
        }
    }

    @Override
    protected void update(Input input){
        if (acting() && !timeOut()){
            if (System.currentTimeMillis() - lastTick > TICK_RATE){
                tickCount++;
                lastTick = System.currentTimeMillis();
                actionUpdate();
            }
            renderAll();
        } else {
            gameResult();
        }
    }

    /**
     * This is the main method which makes use of ShadowLife game.
     * @param args The strings from command line.
     */
    public static void main(String[] args) {
        new ShadowLife().run();
    }
}
