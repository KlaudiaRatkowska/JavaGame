package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.HUMAN;
import static main.world.Defines.SHEEP;

public class Sheep extends Animals{

    public Sheep(COORDINATES coor, World world, int age){
        super(world, coor, SHEEP, age, 4, 1);
    }
}
