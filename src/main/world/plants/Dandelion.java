package main.world.plants;
import main.world.Plants;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.DANDELION;
import static main.world.Defines.GUARANA;

public class Dandelion extends Plants{

    public Dandelion(COORDINATES coor, World world, int age){
        super(world, coor, DANDELION, age, 0);
    }
}
