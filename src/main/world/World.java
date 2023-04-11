package main.world;
import main.world.animals.*;
import main.world.plants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;

import java.util.*;

import static main.world.Defines.*;

public class World {
    private int width;
    private int height;
    private int round;
    private int amountOfOrg;
    private Organism[][] map;
    public ArrayList<Organism> org = new ArrayList<>();

    public World() {

    }

    public int GetHeight() {
        return height;
    }
    public int GetWidth() {
        return width;
    }
    public int GetRound() { return round; }

    public Organism GetArea(COORDINATES coor) {
        return map[coor.x][coor.y];
    }

    public void World() {
    }
    public void addOrganism(Organism newOrg){
        org.add(newOrg);
    }

    public World( int width, int height, int round)
        {
            this.width = width;
            this.height = height;
            this.round = round;
            this.map = new Organism[width][height];
         //   ArrayList<Organism> org = new ArrayList<>();

            System.out.println("New world created width: " + width + " height: " + height);
            plantGrass(org);

            Random random = new Random();
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            COORDINATES coor2 = new COORDINATES(x,y);
            coor2.x = x;
            coor2.y = y;
            Organism human = new Human(coor2, this, 0);
            org.add(human);
            SetOrganism(coor2, human);

            int amountOfOrg = width * height * 3 / 10;
            System.out.println("Organisms take up 30% of area");

            x = random.nextInt(width - 1);
            y = random.nextInt(height - 1);

            int chooseOrg;
            for (int i = 0; i < (amountOfOrg - 1); i++) {
                COORDINATES coor = new COORDINATES(0, 0);
                coor.x = random.nextInt(width - 1);
                coor.y = random.nextInt(height - 1);
                if (map[coor.x][coor.y].GetSign() == GRASS) {
                    chooseOrg = random.nextInt(main.world.Defines.AMOUNT_OF_ORGANISMS);  //without human and grass
                    switch (chooseOrg) {
                        case 0: {
                            Organism newOrg2 = new Wolf(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 1: {
                            Organism newOrg2 = new Antelope(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 2: {
                            Organism newOrg2 = new Turtle(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 3: {
                            Organism newOrg2 = new Fox(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 4: {
                            Organism newOrg2 = new Sheep(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 5: {
                            Organism newOrg2 = new Dandelion(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 6: {
                            Organism newOrg2 = new Guarana(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 7: {
                            Organism newOrg2 = new Wolfberries(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                        case 8: {
                            Organism newOrg2 = new Borsch(coor, this, 0);
                            SetOrganism(coor, newOrg2);
                            org.add(newOrg2);
                            break;
                        }
                    }
                } else {
                    i--;
                }
            }

            round = 1;

            printMap();

           // playRound(org);
        }

        public Organism GetHuman() {
        return org.get(0);
        }


    public void SetOrganism(COORDINATES coor, Organism org) {
        map[coor.x][coor.y] = org;
    }

    public void plantGrass(ArrayList<Organism> org) {
        for (int i = 0; i < width; i++) //rest of area is grass
        {
            for (int j = 0; j < height; j++)
            {
                if (map[i][j] == null)
                {
                    COORDINATES coor1 = new COORDINATES(i, j);
                    Organism newOrg = new Grass(coor1, this, 0);
                    SetOrganism(coor1, newOrg);
                }
            }
        }
    }

    public void printMap() {
        int l = 0;
        System.out.print("  ");
        for (int k = 0; k < width; k++) {
            if (k % 10 == 0 && k >0)
                l++;
            System.out.print(l);
        }
        System.out.println();
        System.out.print("  ");
        for (int k = 0; k < width; k++)
            System.out.print(k % 10);

        System.out.println();

        for (int i = 0; i < width; i++)
        {
            if (i < 10)
                System.out.print(" " + i);
            else
                System.out.print(i);

            for (int j = 0; j < height; j++)
            {
                System.out.print(map[j][i].GetSign());
            }
            System.out.println();
        }
    }

    public void playRound(ArrayList<Organism> org) {
        if (org.get(0).GetAge() >= 0) {
            HumanMoves(org.get(0));

            for (int i = 1; i < org.size(); i++) {
                Organism Organism = org.get(i);
                if (org.get(i).GetAge() == main.world.Defines.DEAD_ORG) {
                    System.out.print("Organism: " + org.get(i).GetSign() + " whose coordinates were x: " + org.get(i).GetXcoor() + " y: " + org.get(i).GetYcoor() +" is dead");
                    org.remove(i);
                }
			else {
                    if (org.get(i).GetStep() > 0) { //animals
                        Animals animal = (Animals) org.get(i);
                        animal.moveOrganisms(map);
                    }
				else {
                    Plants plant = (Plants) org.get(i);
                        plant.PlantsActions();
                    }
                    int age = org.get(i).GetAge();
                    org.get(i).SetAge(++age);
                }
            }
            printMap();
        }
    }

    public void HumanMoves(Organism human){
        System.out.println("             ");
        System.out.println("ROUND: " + round);
        if (human.GetAge() == main.world.Defines.DEAD_ORG) {
            System.out.println("Human does not exist - game over");
            java.lang.System.exit(1);
        }
        else {
            System.out.println("Human coordinates are: " + "x: " + human.GetXcoor() + " y: " + human.GetYcoor());
        }
        Human hum = (Human)human;
        hum.MakeOperation();
        round++;
    }

    public void PlantGrassAfterMove(COORDINATES coor) {
        Organism grass = new Grass(coor, this, 0);
        SetOrganism(coor, grass);
    }

    public void deleteOld(COORDINATES coor)
    {
        map[coor.x][coor.y] = null;
    }

    public void SaveWorldInFile(String fileName){
        fileName += ".txt";
        try {
            FileWriter myObj = new FileWriter(fileName);
            myObj.write(width + " " + height + " "+ round + " " + amountOfOrg + '\n');

            for (Organism org_ : org) {
                if (org_.GetSign() != GRASS) {
                    String save = org_.GetSign() +  " " +  org_.GetXcoor() +  " " +  org_.GetYcoor() +  " " +  org_.GetAge() + '\n';

                    myObj.write(save);
                    System.out.print("saved " + save );
                }
            }

            myObj.close();
            System.out.println("Game saved in file " + fileName);
        } catch (IOException e) {
            System.out.println("Error, game is not saved");
            e.printStackTrace();
            System.exit(1);
        }
    }
    private void LoadInfoFromFile(Scanner myReader){
        String data = myReader.nextLine();

        String[] strArr = data.split("\\s+");

        width = Integer.parseInt(strArr[0]);
        height = Integer.parseInt(strArr[1]);
        round =  Integer.parseInt(strArr[2]);
        if(round <= 0)
            round = 1;
        amountOfOrg = Integer.parseInt(strArr[3]);

        System.out.println("Wordl loaded: " + width + " " + height + " " + round + " " + amountOfOrg);
    }
    private void LoadMapFromFile(){
        System.out.println("World: width: " + width + " " + " height: " + height + " round: " + round);

        map = new Organism [width][height];

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                COORDINATES coor3 = new COORDINATES( j,i );
                SetOrganism(coor3, new Grass(coor3, this, 0));
            }
        }
    }

    private void LoadOrganisms(Scanner myReader){
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.charAt(0) != '/')
            {
                String[] strArr = data.split("\\s+");

                char species = data.charAt(0);

                int x = Integer.parseInt(strArr[1]);
                int y = Integer.parseInt(strArr[2]);
                int age = Integer.parseInt(strArr[3]);

                if (x >= width || y >= height) {
                    System.out.println("incorrect position ");
                    if (x >= width) System.out.println("x ");
                    if (y >= height)  System.out.println("y ");
                    System.out.println("of one of organisms");
                    System.exit(1);
                }

                COORDINATES coor = new COORDINATES(x,y);

                Organism newOrg = null;
                switch(species) {
                    case ANTELOPE: { newOrg = new Antelope(coor, this, 0);} break;
                    case FOX: { newOrg = new Fox(coor, this, 0); } break;
                    case SHEEP: { newOrg = new Sheep(coor, this, 0); } break;
                    case WOLF: { newOrg = new Wolf(coor, this, 0); } break;
                    case TURTLE: { newOrg =new Turtle(coor, this, 0); } break;
                    case BORSCH: { newOrg =new Borsch(coor, this, 0); } break;
                    case GUARANA: { newOrg =new Guarana(coor, this, 0); } break;
                    case DANDELION: { newOrg = new Dandelion(coor, this, 0); } break;
                    case WOLFBERRIES: { newOrg = new Wolfberries(coor, this, 0); } break;
                }

                    if (map[x][y].GetSign() != GRASS)
                        System.out.println("Area " + x + " " + y + " is already occupied by " + map[x][y].GetSign());
                    else {
                            System.out.println("Nowy organizm: " + species + "  position: " + x + " " + y + "  wiek: " + age);
                            SetOrganism(coor, newOrg);
                        }
                }
        }
    }

    public void LoadWorldFromFile(String fileName){
        try {
            File myObj = new File(fileName+".txt");
            Scanner myReader = new Scanner(myObj);

            LoadInfoFromFile(myReader);
            if (height > 0 && width > 0 && round > 0) {
                LoadMapFromFile();
                LoadOrganisms(myReader);
            }
            System.out.println("World loaded");
            printMap();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error, world not loaded");
            e.printStackTrace();
        }
    }

}


