import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;

import java.util.List;
import java.util.Properties;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2024
 *
 * Please enter your name below
 * @author : Tania Candra
 */
public class ShadowMario extends AbstractGame {
    private Properties PROP;
    private final Image BACKGROUND_IMAGE;
    private final Image PLAYER_IMAGE_LEFT;
    private final Image PLAYER_IMAGE_RIGHT;

    private List<GameEntity> gameEntities;
    private List<Fireball> fireballs;
    public Platform platform;
    public FlyingPlatform flyingPlatform;
    public Coin coin;
    public Enemy enemy;
    public EndFlag endFlag;
    public Player player;
    public EnemyBoss enemyBoss;
    public Invincible invincible;
    public DoubleScore doubleScore;
    public Fireball fireball;

    private boolean gameStart = false;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private boolean faceLeft = false;
    private boolean faceRight = true;

    private boolean level1 = false;
    private boolean level2 = false;
    private boolean level3 = false;

    public static int score = 0;
    private final static int INITIAL_SCORE = 0;
    public static double playerHealth;
    public static double enemyBossHealth;
    private final static double MIN_HEALTH = 0;

    public static double PLAYER_RADIUS;
    private final double PLAYER_INITIAL_HEALTH;

    public static double ENEMY_RADIUS;
    private final double ENEMY_DAMAGED_SIZE;
    public static int ENEMY_MAX_RANDOM_DISPLACEMENT;
    public static double ENEMY_SPEED;
    public static int ENEMY_RANDOM_SPEED;

    public static double ENEMY_BOSS_INITIAL_HEALTH;
    public static double ENEMY_BOSS_RADIUS;
    public static double ENEMY_BOSS_ACTIVATION_RADIUS;
    public static double ENEMY_BOSS_SPEED;

    public static double PLATFORM_SPEED;

    public static double FLYING_PLATFORM_MAX_RANDOM_DISPLACEMENT;
    public static double FLYING_PLATFORM_HALF_LENGTH;
    public static double FLYING_PLATFORM_HALF_HEIGHT;
    public static double FLYING_PLATFORM_SPEED;
    public static double FLYING_PLATFROM_RANDOM_SPEED;

    public static double COIN_RADIUS;
    public static double COIN_VALUE;
    public static double COIN_SPEED;

    public static double FIREBALL_RADIUS;
    public static double FIREBALL_DAMAGE_SIZE;
    public static double FIREBALL_SPEED;

    public static double DOUBLE_SCORE_RADIUS;
    public static double DOUBLE_SCORE_MAX_FRAMES;
    public static double DOUBLE_SCORE_SPEED;

    public static double INVINCIBLE_RADIUS;
    public static double INVINCIBLE_MAX_FRAMES;
    public static double INVINCIBLE_SPEED;

    public static double END_FLAG_RADIUS;
    public static double END_FLAG_SPEED;

    private final Font fontTitle;
    private final Font fontInstruction;
    private final Font fontScore;
    private final Font fontMessage;
    private final Font fontPlayerHealth;
    private final Font fontEnemyBossHealth;

    private String titleMessage;
    private String instructionMessage;
    private String scoreMessage;
    private String healthMessage;
    private String gameOverMessage;
    private String gameWonMessage;

    private final Point TITLE_MESSAGE_POSITION;
    private final int INSTRUCTION_Y_PIXEL;
    private final int MESSAGE_Y_PIXEL;
    private final Point SCORE_MESSAGE_POSITION;
    private final Point PLAYER_HEALTH_MESSAGE_POSITION;
    private final Point ENEMY_HEALTH_MESSAGE_POSITION;
    public final static Point PLATFORM_POSITION = new Point(3000, 745);
    public final static Point PLAYER_POSITION = new Point (100, 687);


    /**
     * The constructor
     */
    public ShadowMario(Properties game_props, Properties message_props) {
        super(Integer.parseInt(game_props.getProperty("windowWidth")),
                Integer.parseInt(game_props.getProperty("windowHeight")),
                message_props.getProperty("title"));

        BACKGROUND_IMAGE = new Image(game_props.getProperty("backgroundImage"));
        PLAYER_IMAGE_LEFT = new Image(game_props.getProperty("gameObjects.player.imageLeft"));
        PLAYER_IMAGE_RIGHT = new Image(game_props.getProperty("gameObjects.player.imageRight"));

        // you can initialise other values from the property files here

        // message properties
        this.titleMessage = message_props.getProperty("title");
        this.instructionMessage = message_props.getProperty("instruction");
        this.scoreMessage = message_props.getProperty("score");
        this.healthMessage = message_props.getProperty("health");
        this.gameOverMessage = message_props.getProperty("gameOver");
        this.gameWonMessage = message_props.getProperty("gameWon");

        fontTitle = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("title.fontSize")));
        TITLE_MESSAGE_POSITION = new Point(Integer.parseInt(game_props.getProperty("title.x")),
                Integer.parseInt(game_props.getProperty("title.y")));

        fontMessage = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("message.fontSize")));
        MESSAGE_Y_PIXEL = Integer.parseInt(game_props.getProperty("message.y"));

        fontInstruction = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("instruction.fontSize")));
        INSTRUCTION_Y_PIXEL = Integer.parseInt(game_props.getProperty("instruction.y"));

        fontScore = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("score.fontSize")));
        SCORE_MESSAGE_POSITION = new Point(Integer.parseInt(game_props.getProperty("score.x")),
                Integer.parseInt(game_props.getProperty("score.y")));

        fontPlayerHealth = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("playerHealth.fontSize")));
        PLAYER_HEALTH_MESSAGE_POSITION = new Point(Integer.parseInt(game_props.getProperty("playerHealth.x")),
                Integer.parseInt(game_props.getProperty("playerHealth.y")));

        fontEnemyBossHealth = new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.fontSize")));
        ENEMY_HEALTH_MESSAGE_POSITION =  new Point(Integer.parseInt(game_props.getProperty("enemyBossHealth.x")),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.y")));

        // game objects properties
        PLAYER_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.player.radius"));
        PLAYER_INITIAL_HEALTH = Double.parseDouble(game_props.getProperty("gameObjects.player.health"));
        score = INITIAL_SCORE;
        playerHealth = PLAYER_INITIAL_HEALTH;

        ENEMY_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.enemy.radius"));
        ENEMY_DAMAGED_SIZE = Double.parseDouble(game_props.getProperty("gameObjects.enemy.damageSize"));
        ENEMY_MAX_RANDOM_DISPLACEMENT = Integer.parseInt(game_props.getProperty("gameObjects.enemy.maxRandomDisplacementX"));
        ENEMY_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.enemy.speed"));
        ENEMY_RANDOM_SPEED = Integer.parseInt(game_props.getProperty("gameObjects.enemy.randomSpeed"));

        ENEMY_BOSS_INITIAL_HEALTH = Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.health"));
        ENEMY_BOSS_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.radius"));
        ENEMY_BOSS_ACTIVATION_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.activationRadius"));
        ENEMY_BOSS_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.speed"));
        enemyBossHealth =  ENEMY_BOSS_INITIAL_HEALTH;

        PLATFORM_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.platform.speed"));

        FLYING_PLATFORM_MAX_RANDOM_DISPLACEMENT = Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.maxRandomDisplacementX"));
        FLYING_PLATFORM_HALF_LENGTH = Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.halfLength"));
        FLYING_PLATFORM_HALF_HEIGHT = Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.halfHeight"));
        FLYING_PLATFORM_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.speed"));
        FLYING_PLATFROM_RANDOM_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.randomSpeed"));

        COIN_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.coin.radius"));
        COIN_VALUE = Double.parseDouble(game_props.getProperty("gameObjects.coin.value"));
        COIN_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.coin.speed"));

        FIREBALL_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.fireball.radius"));
        FIREBALL_DAMAGE_SIZE = Double.parseDouble(game_props.getProperty("gameObjects.fireball.damageSize"));
        FIREBALL_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.fireball.speed"));

        DOUBLE_SCORE_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.radius"));
        DOUBLE_SCORE_MAX_FRAMES = Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.maxFrames"));
        DOUBLE_SCORE_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.speed"));

        INVINCIBLE_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.radius"));
        INVINCIBLE_MAX_FRAMES = Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.maxFrames"));
        INVINCIBLE_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.speed"));

        END_FLAG_RADIUS = Double.parseDouble(game_props.getProperty("gameObjects.endFlag.radius"));
        END_FLAG_SPEED = Double.parseDouble(game_props.getProperty("gameObjects.endFlag.speed"));

        this.PROP = game_props;
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        Properties game_props = IOUtils.readPropertiesFile("res/app.properties");
        Properties message_props = IOUtils.readPropertiesFile("res/message_en.properties");

        ShadowMario game = new ShadowMario(game_props, message_props);
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        // close window
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        if (!gameStart) {
            // draw game title and instruction
            fontTitle.drawString(titleMessage, TITLE_MESSAGE_POSITION.x, TITLE_MESSAGE_POSITION.y);
            TextUtility.drawCentred(instructionMessage, fontInstruction, INSTRUCTION_Y_PIXEL);

            // load level 1
            if (input.wasPressed(Keys.NUM_1)) {
                gameEntities = IOUtils.readCsv(PROP.getProperty("level1File"), PROP);
                initializeEntities(gameEntities);
                gameStart = true;
                level1 = true;
            }

            // load level 2
            if (input.wasPressed(Keys.NUM_2)) {
                gameEntities = IOUtils.readCsv(PROP.getProperty("level2File"), PROP);
                initializeEntities(gameEntities);
                gameStart = true;
                level2 = true;
            }

            // load level 3
            if (input.wasPressed(Keys.NUM_3)) {
                gameEntities = IOUtils.readCsv(PROP.getProperty("level3File"), PROP);
                initializeEntities(gameEntities);
                gameStart = true;
                level3 = true;
            }

        } else {

            if (level1 || level2 || level3) {
                fontScore.drawString(scoreMessage + score, SCORE_MESSAGE_POSITION.x, SCORE_MESSAGE_POSITION.y);
                fontPlayerHealth.drawString(healthMessage + String.format("%d", Math.round(playerHealth * 100)),
                        PLAYER_HEALTH_MESSAGE_POSITION.x, PLAYER_HEALTH_MESSAGE_POSITION.y);
            }

            if (level3){
                fontEnemyBossHealth.drawString(healthMessage + String.format("%d", Math.round(enemyBossHealth * 100)),
                        ENEMY_HEALTH_MESSAGE_POSITION.x, ENEMY_HEALTH_MESSAGE_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
            }

            // check for movement inputs
            if (input.isDown(Keys.LEFT)) {
                faceLeft = true;
                faceRight = false;
                for (GameEntity entity : gameEntities) {
                    entity.moveLeft();
                }
            }

            if (input.isDown(Keys.RIGHT)) {
                faceRight = true;
                faceLeft = false;
                for (GameEntity entity : gameEntities) {
                    entity.moveRight();
                }
            }

            if (input.wasPressed(Keys.UP)) {
                player.jump();
            }

            // draw player based on current facing direction
            if (faceLeft) {
                PLAYER_IMAGE_LEFT.draw(player.position.x, player.position.y);
            }
            if (faceRight) {
                PLAYER_IMAGE_RIGHT.draw(player.position.x, player.position.y);
            }

            // draw all entities other than player to avoid overlapping
            for (GameEntity entity : gameEntities) {
                if (entity != player) {
                    entity.draw();
                }
            }

            // handle player collision with every gameEntities
            for (GameEntity entity : gameEntities) {
                // if collide with flying platform, stay on flying platform
                if (entity instanceof FlyingPlatform) {
                    if (player.isOnFlyingPlatform((FlyingPlatform) entity)) {
                        player.setVerticalSpeed(0);
                        player.setIsJumping(false);
                    }

                }
                // if collide with a coin, increase score by 1
                // if doubleScore power is activated and collide with a coin, increase score by 2
                if (entity != player && player.coinCollide(entity)) {
                    if (entity instanceof Coin) {
                        if (isAnyDoubleScoreActive()) {
                            score += 2 * COIN_VALUE;
                        } else {
                            score += COIN_VALUE;
                        }
                        ((Coin) entity).coinDisappear();
                    }
                }

                // if collide with an enemy, reduce health by 0.05
                // if invincible power is activated and collide with an enemy, health is not reduced
                if (entity != player && player.enemyCollide(entity)) {
                    if (entity instanceof Enemy) {
                        if (isAnyInvincibleActivate()) {
                            playerHealth *= 1; //no affect to health
                        } else {
                            playerHealth -= ENEMY_DAMAGED_SIZE;
                            // if health is equal to or below zero, game over
                            if (playerHealth <= MIN_HEALTH) {
                                gameOver = true;
                            }
                        }
                    }
                }

                // if collide with a doubleScore, activate and disappear
                if (entity != player && player.doubleScoreCollide(entity)) {
                    if (entity instanceof DoubleScore) {
                        ((DoubleScore) entity).activate();
                        ((DoubleScore) entity).doubleScoreDisappear();
                    }
                }

                // if collide with an invincible, activate and disappear
                if (entity != player && player.invincibleCollide(entity)) {
                    if (entity instanceof Invincible) {
                        ((Invincible) entity).activate();
                        ((Invincible) entity).invincibleDisappear();
                    }
                }

                // if collide with an endFlag, win the game
                if (entity != player && player.endFlagCollide(entity)) {
                    if (entity instanceof EndFlag) {
                        gameWon = true;
                    }
                }

                // if collide within enemyBoss range, create fireball
                if(entity != player && player.enemyBossDetection(entity)) {
                    if (entity instanceof EnemyBoss) {
                        System.out.println("enemyBoss detected");
                        fireball = new Fireball (PROP.getProperty("gameObjects.fireball.image"), new Point(3800,680));
                        System.out.println("x:" + fireball.position.x + "y:" + fireball.position.y);
                    }
                }

                entity.update();
            }
        }

        if (gameWon) {
            // display the win game message
            BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
            TextUtility.drawCentred(gameWonMessage, fontMessage, MESSAGE_Y_PIXEL);

            // restart game if space bar is pressed
            if(input.wasPressed(Keys.SPACE)) {
                restartGame();
            }
        }

        if (gameOver) {
            // player moves down until it disappears
            if (player.position.y < Window.getHeight()) {
                player.moveDown();
                player.draw();
            } else {
                // display the game over message
                BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
                TextUtility.drawCentred(gameOverMessage, fontMessage, MESSAGE_Y_PIXEL);

                // restart game if space bar is pressed
                if(input.wasPressed(Keys.SPACE)) {
                    restartGame();
                }
            }
        }
        // Code from Elliot Young on 6th April from ED Discussion
        // To slow down the speed movement of the entities drawn on screen
        // Adapted by Tania Candra on 15th April for smoother animation and to match the demo speed

        try {
            Thread.sleep(2000/120); // arg is in milliseconds // 120
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeEntities(List<GameEntity> entities) {
        for (GameEntity entity : entities) {
            if (entity instanceof Platform) {
                platform = (Platform) entity;
            } else if (entity instanceof FlyingPlatform) {
                this.flyingPlatform = (FlyingPlatform) entity;
            } else if (entity instanceof Coin) {
                coin = (Coin) entity;
            } else if (entity instanceof Enemy) {
                this.enemy = (Enemy) entity;
            } else if (entity instanceof EndFlag) {
                this.endFlag = (EndFlag) entity;
            } else if (entity instanceof Player) {
                this.player = (Player) entity;
            } else if (entity instanceof EnemyBoss) {
                this.enemyBoss = (EnemyBoss) entity;
            } else if (entity instanceof Invincible) {
                this.invincible = (Invincible) entity;
            } else if (entity instanceof DoubleScore) {
                this.doubleScore = (DoubleScore) entity;
//            } else if (entity instanceof Fireball) {
//                this.fireball = (Fireball) entity;
            }
        }
    }

    // helper method to check if any DoubleScore is active
    private boolean isAnyDoubleScoreActive() {
        for (GameEntity entity : gameEntities) {
            if (entity instanceof DoubleScore && ((DoubleScore) entity).isActivated()) {
                return true;
            }
        }
        return false;
    }

    // helper method to check if any Invincible is active
    private boolean isAnyInvincibleActivate() {
        for (GameEntity entity : gameEntities) {
            if (entity instanceof Invincible && ((Invincible) entity).isActivated()) {
                return true;
            }
        }
        return false;
    }

    // restart game by setting variables to initial condition
    // and position of each entity to initial position
    private void restartGame() {
        // reset game variables
        gameStart = false;
        gameOver = false;
        gameWon = false;
        score = INITIAL_SCORE;
        playerHealth = PLAYER_INITIAL_HEALTH;
        enemyBossHealth = ENEMY_BOSS_INITIAL_HEALTH;

        // reset all entities
        for (GameEntity entity : gameEntities) {
            if (entity != null) {
                entity.reset();
            }
        }
    }
}
