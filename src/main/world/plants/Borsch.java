package main.world.plants;
import main.world.Plants;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.BORSCH;
import static main.world.Defines.GUARANA;

public class Borsch extends Plants{

    public Borsch(COORDINATES coor, World world, int age){
        super(world, coor, BORSCH, age, 10);
    }
}
