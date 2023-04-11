package main.world.animals;
import main.world.Animals;
import main.world.World;
import main.world.COORDINATES;
import main.world.World;
import main.world.plants.Grass;
import main.world.Organism;
import java.lang.System.*;
import java.util.Random;

import static main.world.Defines.*;

public class Human extends Animals{
    public int direction;
    private int specialSkill;
    public int skillActive;
    public int skillReload;
    public int skillActiveRounds;

    public int GetSkillActive() { return skillActive; }
    public int GetSkillReload() { return skillReload; }
    public void SetSkillActive(int skillActive) { this.skillActive = skillActive; }
    public int GetSkillActiveRounds() { return skillActiveRounds;}

    public Human(COORDINATES coor, World world, int age){
    super(world, coor, HUMAN, age, 5, 1);
    }

    public int GetXcoor() {
        return coordinates.x;
    }

    public int GetYcoor() {
        return coordinates.y;
    }

    public void SetDirection(int direction) {
        this.direction = direction;
    }

    public void MakeOperation() {
        char button;
        boolean move = true;
        COORDINATES coor;
        COORDINATES startingCoor;
        startingCoor = coordinates;
        Random random = new Random();
        while (move) {
            direction = random.nextInt(4);
            direction++;
            switch (direction)
            {
                case GO_UP: {
                    if (coordinates.x - step >= 0) {
                        this.coordinates.x -= 1;
                        System.out.println("Human goes up");
                        move = false;
                    }
                }
                break;
                case GO_DOWN: {
                    if (coordinates.x + step < world.GetHeight()) {
                        this.coordinates.x += 1;
                        System.out.println("Human goes down");
                        move = false;
                    }
                }
                break;
                case GO_LEFT: {
                    if (coordinates.y - step >= 0) {
                        this.coordinates.y -= 1;
                        System.out.println("Human goes left");
                        move = false;
                    }
                }
                break;
                case GO_RIGHT: {
                    if (coordinates.y + step < world.GetWidth()) {
                        this.coordinates.y += 1;
                        System.out.println("Human goes right");
                        move = false;
                    }
                }
                break;
            }
        }
        age++;
        coor = coordinates;

        if (world.GetArea(coor).GetSign() != GRASS) //there is other organism in this area
        {
            if (world.GetArea(coor).GetStrength() < this.GetStrength()) { //other oragnism is weaker
            System.out.println("Human fought with organism " + world.GetArea(coor).GetSign() + " and won");
            System.out.println("Its new coordinates are x: " + coor.x + " y: " + coor.y);
            world.SetOrganism(coor, this);
            world.PlantGrassAfterMove(startingCoor);
        }
			else { //other organism is stronger
            System.out.println("Human fought with organism " + world.GetArea(coor).GetSign() + " and lost");
            System.out.println("Human died");
            this.SetAge(DEAD_ORG);
        }

        }

	else {
            System.out.println("Humans' new coordinates are x: " + coor.x + " y: " + coor.y);  //animal moved
            world.SetOrganism(coor, this);
            world.deleteOld(startingCoor);
            world.PlantGrassAfterMove(startingCoor);
            System.out.println("");
        }

        if (specialSkill == SPECIAL_SKILL) {
            System.out.println("Special skill activated: burns area nearby human");
            SpecialSkill();
            specialSkill = 1;
        }
        else {
            specialSkill++;
        }
    }

    private void SpecialSkill() { //area nearby human is grass
        COORDINATES coor = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                coor.x = this.coordinates.x - 1 + i;
                coor.y = this.coordinates.y - 1 + j;
                if (coor.x >= 0 && coor.x < world.GetWidth() && coor.y >= 0 && coor.y < world.GetHeight()) {
                    if (world.GetArea(coor).GetSign() != HUMAN) {
                        Organism org = new Grass(coor, world, 0);
                        world.SetOrganism(coor, org);
                    }
                }
            }
        }
    }
}
