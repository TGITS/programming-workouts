import java.util.Random;
import java.util.stream.IntStream;

class DnDCharacter {

    private static final Random random = new Random();

    private int strength;
    private int dexterity;
    private int constitution;
    private int charisma;
    private int intelligence;
    private int wisdom;
    private int hitpoints;

    public DnDCharacter() {
        this.charisma = ability();
        this.constitution = ability();
        this.dexterity = ability();
        this.intelligence = ability();
        this.strength = ability();
        this.wisdom = ability();
        this.hitpoints = 10 + modifier(this.constitution);
    }

    int ability() {
        return IntStream.generate(() -> (1 + random.nextInt(6))).limit(4).sorted().skip(1).sum();
    }

    int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.0);
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getHitpoints() {
        return hitpoints;
    }
}
