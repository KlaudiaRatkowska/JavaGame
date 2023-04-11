package main.world.plants;
import main.world.Plants;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.GUARANA;
import static main.world.Defines.WOLF;

public class Guarana extends Plants{

    public Guarana(COORDINATES coor, World world, int age){
        super(world, coor, GUARANA, age, 0);
    }
}
