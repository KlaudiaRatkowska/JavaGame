package main.world;

public class Organism {
    protected COORDINATES coordinates;
    protected int age;
    protected int strength;
    protected char sign;
    protected int step;
    protected World world;

    public Organism() {}

    public Organism(World world, COORDINATES coor, char sign, int age, int strength, int step){
        this.coordinates= coor;
        this.sign = sign;
        this.age = age;
        this.world = world;
        this.strength = strength;
        this.step = step;
    }

public char GetSign() {
        return sign;
}

public int GetAge() {
        return age;
}

public void SetAge(int age){
        this.age = age;
}

    public int GetStep() {
        return step;
    }

    public int GetXcoor() {
        return coordinates.x;
    }

    public int GetYcoor() {
        return coordinates.y;
    }
    public void SetXcoor(int coor) {
        this.coordinates.x = coor;
    }

    public void SetYcoor(int coor) {
        this.coordinates.y = coor;
    }
    public int GetStrength() {
        return strength;
    }
    public void SetStrength(int strength) {
        this.strength = strength;
    }
}
