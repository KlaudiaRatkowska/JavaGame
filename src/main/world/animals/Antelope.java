package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.Defines;

import static main.world.Defines.ANTELOPE;
import static main.world.Defines.HUMAN;

public class Antelope extends Animals{

    public Antelope(COORDINATES coor, World world, int age){
        super(world, coor, ANTELOPE, age, 4, 2);
    }
}
