package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.HUMAN;
import static main.world.Defines.WOLF;

public class Wolf extends Animals{

    public Wolf(COORDINATES coor, World world, int age){
        super(world, coor, WOLF, age, 9, 1);
    }
}
