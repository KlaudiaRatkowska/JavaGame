package main.world.plants;
import main.world.Plants;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.GUARANA;
import static main.world.Defines.WOLFBERRIES;

public class Wolfberries extends Plants{

    public Wolfberries(COORDINATES coor, World world, int age){
        super(world, coor, WOLFBERRIES, age, 99);
    }
}
