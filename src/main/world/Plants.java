package main.world;
import main.world.Organism;
import main.world.plants.Dandelion;

import java.util.Random;

import static java.lang.System.exit;
import static main.world.Defines.*;

public class Plants extends Organism {
    public Plants() {}


    public Plants(World world, COORDINATES coor, char sign, int age, int strength){
        super(world, coor, sign, age, strength, 0);
    }

    public void PlantsActions() {
        COORDINATES coor = new COORDINATES(0,0);
        Random rand = new Random();
        if (sign == DANDELION) { //has three chances to disper
            for (int i = 0; i < 3; i++) {
                int x_rand = rand.nextInt(3);
                int y_rand = rand.nextInt(3);
                coor.x = coordinates.x - 1 + x_rand;
                coor.y = coordinates.y - 1 + y_rand;
                if (coor.x >= 0 && coor.x < world.GetHeight() && coor.y >= 0 && coor.y < world.GetWidth()) {
                    if (world.GetArea(coor).GetSign() == GRASS) {
                        Organism dandelion = new Dandelion(coor, world, 0);
                        world.SetOrganism(coor, dandelion);
                    }
                }
            }
        }

        if (sign == BORSCH) { //kills all organisms nearby
            for (int j = (coordinates.x - 1); j <= (coordinates.x + 1); j++) {
                for (int i = (coordinates.y - 1); i <= (coordinates.y + 1); i++) {
                    coor.x = j;
                    coor.y = i;
                    if (i >= 0 && i < world.GetWidth() && j >=0 && j < world.GetHeight()) {
                        if (world.GetArea(coor).GetSign() != GRASS && world.GetArea(coor).GetSign() != BORSCH) {
                            System.out.println("Borsch " + coordinates.x + " " + coordinates.y + " killed organism: " + world.GetArea(coor).GetSign() + " coordinated : x: " + coor.x + " y: " + coor.y);
                            if (world.GetArea(coor).GetSign() == HUMAN) {
                                exit(1);
                            }
                            world.GetArea(coor).SetAge(DEAD_ORG);
                            world.PlantGrassAfterMove(coor);
                        }
                    }
                }
            }
        }
    }
}
