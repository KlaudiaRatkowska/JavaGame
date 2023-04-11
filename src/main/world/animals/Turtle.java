package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.HUMAN;
import static main.world.Defines.TURTLE;

public class Turtle extends Animals{

    public Turtle(COORDINATES coor, World world, int age){
        super(world, coor, TURTLE, age, 2, 1);
    }
}
