import java.io.*;

/**
 * Created by Mark on 07/08/2016.
 */
public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(100, "Elf", new String[]{"Bow", "Dagger", "Dust"});
        GameCharacter two = new GameCharacter(275, "Troll", new String[] {"Cleaver", "Battle Axe", "Club"});
        GameCharacter three = new GameCharacter(150, "Magician", new String[]{"Wand"});

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        }catch (Exception e){e.printStackTrace();}

        one = null;
        two = null;
        three = null;

        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();
            is.close();
            System.out.println("One's type: " + oneRestore.getType() + "\nOne's power: " + oneRestore.getPower() + "\nOne's weapons: " + oneRestore.getWeapons());
            System.out.println("Two's type: " + twoRestore.getType() + "\nTwo's power: " + twoRestore.getPower() + "\nTwo's weapons: " + twoRestore.getWeapons());
            System.out.println("Three's type: " + threeRestore.getType() + "\nThree's power: " + threeRestore.getPower() + "\nThree's weapons: " + threeRestore.getWeapons());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
