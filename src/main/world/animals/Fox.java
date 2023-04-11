package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.FOX;
import static main.world.Defines.HUMAN;

public class Fox extends Animals{
    public Fox(COORDINATES coor, World world, int age){
        super(world, coor, FOX, age, 3, 1);
    }
}
