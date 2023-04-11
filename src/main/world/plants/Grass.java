package main.world.plants;
import main.world.Plants;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.GRASS;
import static main.world.Defines.GUARANA;

public class Grass extends Plants{


    public Grass(COORDINATES coor, World world, int age){
        super(world, coor, GRASS, age, 0);
    }
}
