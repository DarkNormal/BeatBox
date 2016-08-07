import java.io.Serializable;

/**
 * Created by Mark on 07/08/2016.
 */
public class GameCharacter implements Serializable{
    int power;
    String type;
    String[] weapons;

    public GameCharacter(int power, String type, String[] weapons){
        this.power = power;
        this.type = type;
        this.weapons = weapons;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getWeapons() {
        String weaponList = "";
        for (String weapon : weapons){
            weaponList += weapon + ", ";
        }
        return weaponList;
    }
}
