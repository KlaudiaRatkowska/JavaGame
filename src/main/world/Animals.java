package main.world;
import main.world.Organism;
import main.world.animals.*;

import java.util.Random;

import static main.world.DIRECTION.*;
import static main.world.DIRECTION.NO_CHANGE;
import static main.world.Defines.*;

public class Animals extends Organism {
    public Animals() {}

    public Animals(World world, COORDINATES coor, char sign, int age, int strength, int step){
        super(world, coor, sign, age, strength, step);
    }


    public void moveOrganisms(Organism[][] map) {
        System.out.println("");
        System.out.println("Organism: " + sign + " of age: " + age + " and coordinates x: " + coordinates.x + " y: " + coordinates.y + " is changing its position");
        boolean positionNotChanged = true;
        DIRECTION direction = NO_CHANGE;
        COORDINATES coordinates2 = new COORDINATES(0,0);
        COORDINATES coor = new COORDINATES(0,0);
        COORDINATES startingCoor = new COORDINATES(0,0);
        startingCoor.x = coordinates.x;
        startingCoor.y = coordinates.y;
        int chancesToMove = 10;
        Random rand = new Random();
        while (positionNotChanged == true && chancesToMove > 0) {
            int dir = rand.nextInt(4);
            dir++; //random number 1-4
            switch (dir)
            {
                case GO_UP: {
                    if (coordinates.y - step >= 0)
                    {
                        this.SetYcoor(coordinates.y - step); //change coordinates
                        positionNotChanged = false;
                        direction = UP;
                    }
                }
                break;
                case GO_DOWN: {
                    if (coordinates.y + step < world.GetHeight()) {
                        this.SetYcoor(coordinates.y + step);
                        positionNotChanged = false;
                        direction = DOWN;
                    }
                }
                break;
                case GO_LEFT: {
                    if (coordinates.x - step >= 0) {
                        this.SetXcoor(coordinates.x - step);
                        positionNotChanged = false;
                        direction = LEFT;
                    }
                }
                break;
                case GO_RIGHT: {
                    if (coordinates.x + step < world.GetWidth()) {
                        this.SetXcoor(coordinates.x + step);
                        positionNotChanged = false;
                        direction = RIGHT;
                    }
                }
                break;
            }
            chancesToMove--;
        }
        coor.x = coordinates.x;
        coor.y = coordinates.y;
        if (direction == NO_CHANGE) {
            System.out.println("This organism did not move");
        }
        else {
            if (world.GetArea(coor).GetSign() != GRASS) //there is other organism in this area
            {
                if (map[coordinates.x][coordinates.y].GetSign() == this.GetSign()) { //there is the same spiecies - parent2
                coordinates2.x = coordinates.x; //parent2' coordinates
                coordinates2.y = coordinates.y;
                this.ReturnToStartingPosition(direction); //parent1 changes coordinates
                this.multiplicate(map, coordinates2); //create new orgianism of the same spiecies nearby
            }
			else { //there is other species
                if (map[coordinates.x][coordinates.y].GetStrength() < this.GetStrength()) { //other oragnism is weaker
                    System.out.println("This organisms fought with organism " + map[coordinates.x][coordinates.y].GetSign() + " and won");
                    System.out.println("Its new coordinates are x: " + coordinates.x + " y: " + coordinates.y);
                    if (map[coordinates.x][coordinates.y].GetSign() == GUARANA) {
                        int strength = this.GetStrength();
                        this.SetStrength(strength + 3);
                    }
                    if (map[coordinates.x][coordinates.y].GetSign() == WOLFBERRIES) {
                        System.out.println("OOppss, it was wolfberries - animal die");
                        world.PlantGrassAfterMove(coor);
                    }
                    if (map[coordinates.x][coordinates.y].GetSign() == HUMAN) {
                        map[coordinates.x][coordinates.y].SetAge(DEAD_ORG);
                    }
                    world.SetOrganism(coordinates, this);
                    world.PlantGrassAfterMove(startingCoor);
                }
				else { //other organism is stronger
                    System.out.println("This organisms fought with organism " + map[coordinates.x][coordinates.y].GetSign() + " and lost");
                    System.out.println("This organism died");
                    this.SetAge(DEAD_ORG);
                    world.PlantGrassAfterMove(coor);
                }
            }
            }

		else {
                System.out.println("Its new coordinates are x: " +coordinates.x + " y: " + coordinates.y);  //animal moved
                world.SetOrganism(coordinates, this);
                world.deleteOld(startingCoor);
                world.PlantGrassAfterMove(startingCoor);
                System.out.println("");
            }
        }
    }
    public void multiplicate(Organism[][] map, COORDINATES coordinates) {
        COORDINATES newAnimalCoor = new COORDINATES(0,0);
        if (this.NewOrgArea(newAnimalCoor, coordinates)) { //there is place for new organism
            createNewOrg(sign, newAnimalCoor);
            System.out.println("This organism has multiplied and did not change its coordinates");
            System.out.println("New organisms' coordinates are x: " + newAnimalCoor.x + " y: " + newAnimalCoor.y);
            System.out.println("");
        }
	else {
            System.out.println("This organism tried to multiply but failed and has not changed its coordinates");
            System.out.println("");
        }


    }

    public void ReturnToStartingPosition(DIRECTION direction) {
        if (direction == UP)
            this.SetYcoor(coordinates.x + step);
        if (direction == DOWN)
            this.SetYcoor(coordinates.x - step);
        if (direction == LEFT)
            this.SetXcoor(coordinates.y + step);
        if (direction == RIGHT)
            this.SetXcoor(coordinates.y - step);
    }

    boolean NewOrgArea(COORDINATES newAnimalCoor, COORDINATES coor) {
        boolean newArea = false;
        for (int i = (coor.y - 1); i <= (coor.y + 1); i++) {
            for (int j = (coor.x - 1); j <= (coor.x - 1); j++) {
                coor.y = i;
                coor.x = j;
                if (i >= 0 && i < world.GetHeight() && j >= 0 && j < world.GetWidth()) {
                    if (world.GetArea(coor).GetSign() == GRASS) {
                        newAnimalCoor.x = j;
                        newAnimalCoor.y = i;
                        newArea = true;
                        return newArea;
                    }
                }
            }
        }
        return newArea;
    }

    public void createNewOrg(char sign, COORDINATES newAnimalCoor){
        Organism newOrg;
        switch (sign) {
            case WOLF:
            {
                newOrg = new Wolf(newAnimalCoor, world, 0);
                world.SetOrganism(newAnimalCoor, newOrg);
                world.org.add(newOrg);
            }break;
            case ANTELOPE:
            {
                newOrg = new Antelope(newAnimalCoor, world, 0);
                world.SetOrganism(newAnimalCoor, newOrg);
                world.org.add(newOrg);
            }break;
            case TURTLE:
            {
                newOrg = new Turtle(newAnimalCoor, world, 0);
                world.SetOrganism(newAnimalCoor, newOrg);
                world.org.add(newOrg);
            }break;
            case FOX:
            {
                newOrg = new Fox(newAnimalCoor, world, 0);
                world.SetOrganism(newAnimalCoor, newOrg);
                world.org.add(newOrg);
            }break;
            case SHEEP:
            {
                newOrg = new Sheep(newAnimalCoor, world, 0);
                world.SetOrganism(newAnimalCoor, newOrg);
                world.org.add(newOrg);
            }break;
        }
    }

}
