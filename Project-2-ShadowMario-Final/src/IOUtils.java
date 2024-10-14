import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import bagel.util.Point;

/**
 * Class that contains methods to read a CSV file and a properties file.
 * You may edit this as you wish.
 */
public class IOUtils {

    /***
     * Method that reads a CSV file and return a list of GameEntity objects
     * @param csvFile: the path to the CSV file
     * @param gameProps: object properties for Shadow Mario
     * @return list of GameEntity
     */

    public static List<GameEntity> readCsv(String csvFile, Properties gameProps) {
        List<GameEntity> gameEntities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                // read and split each line
                String cells[] = line.split(",");
                String gameEntity = cells[0];
                double x = Double.parseDouble(cells[1]);
                double y = Double.parseDouble(cells[2]);

                // create game entity objects
                switch (gameEntity) {
                    case "PLATFORM":
                        gameEntities.add(new Platform(gameProps.getProperty("gameObjects.platform.image"),
                                new Point(x, y)));
                        break;
                    case "FLYING_PLATFORM" :
                        gameEntities.add(new FlyingPlatform(gameProps.getProperty("gameObjects.flyingPlatform.image"),
                                new Point(x, y)));
                        break;
                    case "COIN" :
                        gameEntities.add(new Coin(gameProps.getProperty("gameObjects.coin.image"),
                                new Point(x, y)));
                        break;
                    case "ENEMY" :
                        gameEntities.add(new Enemy(gameProps.getProperty("gameObjects.enemy.image"),
                                new Point(x, y)));
                        break;
                    case "END_FLAG" :
                        gameEntities.add(new EndFlag(gameProps.getProperty("gameObjects.endFlag.image"),
                                new Point(x, y)));
                        break;
                    case "PLAYER" :
                        gameEntities.add(new Player(gameProps.getProperty("gameObjects.player.imageRight"),
                                new Point(x, y)));
                        break;
                    case "ENEMY_BOSS" :
                        gameEntities.add(new EnemyBoss(gameProps.getProperty("gameObjects.enemyBoss.image"),
                                new Point(x, y)));
                        break;
                    case "INVINCIBLE_POWER" :
                        gameEntities.add(new Invincible(gameProps.getProperty("gameObjects.invinciblePower.image"),
                                new Point(x, y)));
                        break;
                    case "DOUBLE_SCORE" :
                        gameEntities.add(new DoubleScore(gameProps.getProperty("gameObjects.doubleScore.image"),
                                new Point(x, y)));
                        break;
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return gameEntities;
    }

    /***
     * Method that reads a properties file and return a Properties object
     * @param configFile: the path to the properties file
     * @return Properties object
     */
    public static Properties readPropertiesFile(String configFile) {
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(configFile));
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        return appProps;
    }
}
