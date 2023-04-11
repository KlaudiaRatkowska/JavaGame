package main.world.Swing;

import main.world.COORDINATES;
import main.world.Organism;
import main.world.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

import main.world.animals.*;
import main.world.plants.Borsch;
import main.world.plants.Dandelion;
import main.world.plants.Guarana;
import main.world.plants.Wolfberries;

import static java.lang.System.exit;
import static main.world.Defines.DEAD_ORG;
import static main.world.Defines.GRASS;

public class Swing implements KeyListener {
    JButton[][] map;
    JLabel direction;
    JFrame Frame;

    int width;
    int height;
    World world = new World();

   public static void main(String[] args) {
        new Swing();
    }

    public Swing()
    {
        setFrame(500, 530);

        int Xpos=150;
        int Ypos=80;
        int W = 200;
        int H = 50;

       JButton load = new JButton("Load game from .txt file");
        load.setBounds(Xpos,3*Ypos,W,H);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
                LoadGameFromFile();
            }
       });

        JButton createNewGame = new JButton("Create new game");
        createNewGame.setBounds(Xpos,2*Ypos,W,H);
        createNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
                typeInfo();
            }
        });

        addExitButton(Xpos,4*Ypos,W,H);

        Frame.add(createNewGame);
        Frame.add(load);
        Frame.repaint();
    }

    private void LoadGameFromFile(){

        setFrame(300,400);

        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JTextField> textFields = new ArrayList<>();

        labels.add(new JLabel("File name"));

        for(JLabel lab : labels){
            int index = labels.indexOf(lab);
            lab.setBounds(50,(1+index)*50-20,200,20);
            textFields.add(new JTextField());
            textFields.get(index).setBounds(50,(1+index)*50,150,20);
            Frame.add(lab);
            Frame.add(textFields.get(index));
        }

        JButton loadFromFileButton = new JButton("create game");
        loadFromFileButton.setBounds(25,250,100,50);
        addExitButton(150,250,100,50);

        loadFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField tf1 = textFields.get(0);
                if(!tf1.getText().equals("")){
                        world = new World();
                        world.LoadWorldFromFile(tf1.getText());
                        width = world.GetWidth();
                        height = world.GetHeight();

                        Frame.dispose();
                        showMap();
                        Human Hum = (Human) world.GetHuman();
                        if (Hum != null)
                            Hum.SetDirection(4); //no_change
                }
                else
                    error("Fill in the field");
            }
        });

        Frame.add(loadFromFileButton);
        Frame.repaint();
    }

    private void setFrame(int width, int height){
        Frame = new JFrame("Game");
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width1 = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height1 = Toolkit.getDefaultToolkit().getScreenSize().height;
        Frame.setLocation((width1 - 500)/2, (height1 - 530)/2);
        Frame.setFocusable(true);
        Frame.setSize(width, height);
        Frame.setLayout(null);
        Frame.setResizable(false);
    }

    private void addExitButton(int x, int y, int w, int h){
        JButton exit = new JButton("Exit");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(1);
            }
        });

        exit.setBounds(x,y,w,h);
        Frame.add(exit);
    }

    private void typeInfo()
    {
        setFrame(400, 500);

        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JTextField> textFields = new ArrayList<>();

        labels.add(new JLabel("Width"));
        labels.add(new JLabel("Height"));

        for(JLabel lab : labels){
            int index = labels.indexOf(lab);
            lab.setBounds(170,(1+index)*80-20,200,20);
            textFields.add(new JTextField());
            textFields.get(index).setBounds(140,(1+index)*80,100,20);
            Frame.add(lab);
            Frame.add(textFields.get(index));
        }

        JButton newWorld = new JButton("Save");

        newWorld.setBounds(80, 270, 100, 50);
        addExitButton(210, 270, 100, 50);
        newWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewWorld(textFields);
            }
        });

        Frame.add(newWorld);
        Frame.repaint();
    }

    private void error(String err){
        showErrorLabel(err);
        throw new IllegalArgumentException(err);
    }

    private void showErrorLabel(String text){
        JLabel l5 = new JLabel();
        l5.setBounds(0,0,150,20);
        l5.setText(text);
        Frame.add(l5);
        Frame.repaint();
    }

    private void createNewWorld(ArrayList<JTextField> arr){
        JTextField tf1 = arr.get(0);
        JTextField tf2 = arr.get(1);

        if(!tf1.getText().equals("") && !tf2.getText().equals(""))
        {
                try {
                    width = Integer.parseInt(tf1.getText());
                    height = Integer.parseInt(tf2.getText());
                } catch (Exception e) {
                    showErrorLabel("Type only numbers");
                    throw new IllegalArgumentException("Type only numbers");
                }

                if (width > 0 && height > 0) {
                    Frame.dispose();
                    world = new World(width, height, 1);
                    showMap();
                } else
                    error("Illegal values");
        }
        else
            error("Fill all gaps!");
    }

    private void SaveGameInFile(){
        setFrame(300,400);

        JLabel l1 = new JLabel("File name");
        l1.setBounds(50,30,150,20);
        JTextField tf1=new JTextField();
        tf1.setBounds(50,50,150,20);

        JButton saveToTxt = new JButton("save");

        saveToTxt.setBounds(25,250,100,50);
        addExitButton(150,250,100,50);

        saveToTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tf1.getText().equals("")){
                    world.SaveWorldInFile(tf1.getText());
                    System.exit(1);
                }
                else{
                    error("Uzupelnij pole!");
                }
            }
        });

        Frame.add(l1);
        Frame.add(tf1);
        Frame.add(saveToTxt);
        Frame.repaint();
    }

    private void showMap(){
        Frame = new JFrame();
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setFocusable(true);

        map = new Buttons[width][height];

        for(int col=0;col<width;col++)
            for(int row=0;row<height;row++){

                char species = world.GetArea(new COORDINATES(col, row)).GetSign();

                map[col][row] = new Buttons(col,row, species);

                if(species == GRASS)
                    addMenuToButton(map[col][row], col,row);

                Frame.add(map[col][row]);
            }

        int buttW = map[0][0].getWidth();
        int buttH = map[0][0].getHeight();
        showButtonsOnBoard(60, buttW);
        adjustFrameSize(buttW, buttH);
    }

    private void showButtonsOnBoard(int distance, int buttW){
        Human Hum = (Human) world.GetHuman();
        if (Hum == null) {
            direction = new JLabel("Game over");
            direction.setBounds(width*distance,60,300,50);
            Frame.add(direction);
            Frame.repaint();
        }
        else{
            addPlanszaButtons(distance);
            Frame.setFocusable(true);
            Frame.addKeyListener(this);
        }
    }
    private void adjustFrameSize(int buttW, int buttH){
        int x=0,y=0;
        if(height < 5)
            x = 600;
        else
            x = (1+width)*buttW + 200;
        if(width < 5)
            y = 600;
        else
            y = (1+height)*buttH + 200;

        Frame.setSize(x,y);

        Frame.setLayout(null);
        Frame.setVisible(true);
    }

    private void addMenuToButton(JButton b, int x, int y){

        JPopupMenu popupmenu = new JPopupMenu();
        LinkedList<JMenuItem> BoardButtonsMenu = new LinkedList<>();

        BoardButtonsMenu.add(new JMenuItem("antelope"));
        BoardButtonsMenu.add(new JMenuItem("fox"));
        BoardButtonsMenu.add(new JMenuItem("sheep"));
        BoardButtonsMenu.add(new JMenuItem("wolf"));
        BoardButtonsMenu.add(new JMenuItem("turtle"));
        BoardButtonsMenu.add(new JMenuItem("borsch"));
        BoardButtonsMenu.add(new JMenuItem("guarana"));
        BoardButtonsMenu.add(new JMenuItem("dandelion"));
        BoardButtonsMenu.add(new JMenuItem("wolfberries"));

        for (JMenuItem menuButton : BoardButtonsMenu ) {
            popupmenu.add(menuButton);
            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HandleNewAnimal(b, menuButton, x, y);
                }
            });
        }
        b.addKeyListener(this);
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupmenu.show(Frame, b.getX(), b.getY());
            }
        });

        Frame.add(popupmenu);
        Frame.add(b);
    }

    private void HandleNewAnimal(JButton butt,JMenuItem item, int x, int y){
        COORDINATES coor = new COORDINATES(x,y );

        System.out.println("x: " + coor.x  + " y: " + coor.y);

        switch(item.getText()) {
            case "antelope": {
                Organism newOrg = new Antelope(coor, world, 0);
                world.SetOrganism(coor,newOrg);
                world.org.add(newOrg);
            }
                break;
            case "fox": {
                Organism newOrg = new Fox(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "sheep": {
                Organism newOrg = new Sheep(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "wolf": {
                Organism newOrg = new Wolf(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "turtle": {
                Organism newOrg =new Turtle(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "borsch": {
                Organism newOrg =new Borsch(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "guarana": {
                Organism newOrg =new Guarana(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case "dandelion": {
                Organism newOrg = new Dandelion(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
            case"wolfberries": {
                Organism newOrg = new Wolfberries(coor, world, 0);
                world.SetOrganism(coor, newOrg);
                world.org.add(newOrg);
            }
                break;
        }
        butt.setText(Character.toString(world.GetArea(coor).GetSign()));

        Frame.repaint();
    }

    private void addPlanszaButtons(int buttWidth){

        addExitButton(width*buttWidth,0,150,50);

        JButton saveThisGameToFile = new JButton("Save world");
        saveThisGameToFile.setBounds(width*buttWidth,70,150,50);
        saveThisGameToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
               SaveGameInFile();
            }
        });
        Frame.add(saveThisGameToFile);

        JButton nextRound = new JButton("Next round");
        nextRound.setBounds(width*buttWidth,140,150,50);
        nextRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
                if(world.org.get(0).GetAge() != DEAD_ORG)
                    world.playRound(world.org);
                else
                    exit(1);
                showMap();

                Human Hum = (Human) world.org.get(0);
                if (Hum != null)
                    Hum.SetDirection(4); //ustawanie na "no_change"
            }
        });

        Frame.add(nextRound);

        Human Hum = (Human) world.GetHuman();
        if (Hum != null) {

            Hum.SetDirection(4);
            int akt = Hum.GetSkillActive();
            int odn = Hum.GetSkillReload();
            if(akt == 0 && odn == 0) {
                JButton activateAbility = new JButton("Skill");
                activateAbility.setBounds(width * buttWidth, 210, 150, 50);
                activateAbility.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Human Hum = (Human)world.org.get(0);
                        if (Hum != null) {
                            Hum.SetDirection(4);
                            Hum.SetSkillActive(Hum.GetSkillActiveRounds());
                            Frame.dispose();
                            if(world.org.get(0).GetAge() != DEAD_ORG)
                                world.playRound(world.org);
                            else
                                exit(1);
                            showMap();
                            Hum.SetDirection(4);
                        }
                    }
                });
                Frame.add(activateAbility);
            }
            else{
                JLabel inf = new JLabel();
                if(akt > 0 && odn == 0)
                    inf = new JLabel("umiej akt przez: " + akt);
                else if( akt == 0 && odn > 0)
                    inf = new JLabel("umiej odn przez: " + odn);

                inf.setBounds(width*buttWidth,210,150,50);
                Frame.add(inf);
            }

        }

        JLabel ltrua = new JLabel("tura: " + world.GetRound());
        ltrua.setBounds(width*buttWidth,270,300,50);
        Frame.add(ltrua);

        direction = new JLabel("no change");
        direction.setBounds(width*buttWidth,300,300,50);
        Frame.add(direction);
    }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        Human Hum = (Human) world.org.get(0);

        if (Hum != null) {
            int keyCode = e.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    Hum.SetDirection(1);
                    direction.setText("Up");
                    Frame.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    Hum.SetDirection(2);
                    direction.setText("Down");
                    Frame.repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    Hum.SetDirection(3);
                    direction.setText("Left");
                    Frame.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    Hum.SetDirection(4);
                    direction.setText("Right");
                    Frame.repaint();
                    break;
            }
        } else {
            direction.setText("GAME OVER");
            Frame.repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}


