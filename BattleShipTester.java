import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BattleShipTester extends JFrame implements ActionListener //implements Action
// Listener for buttons
{

    //initializing all instance variables
    private Container window;
    private JPanel player;
    private JPanel playerTwo;
    private JButton[][] butArray;
    private JButton[][] oppArray;
    private JLabel title;
    private JLabel one;
    private JLabel two;
    private JLabel turn;
    private int ships = 10;
    private boolean direction = false;
    private boolean playerOneAttack = true;
    private JButton horizontal;
    private JButton vertical;
    private JButton test;
    private int firstTester = 0;
    private JButton startGame;
    private boolean start = false;
    private int countOfGreens;
    private int countOfGreensTwo;
    private JLabel newTurn;
    private JTextArea rules;
    private JLabel info;


    public BattleShipTester()
    {
//calling all methods to the main
        setupWindow();
        addPlayerGrid();
        addStufftoWindow();
    }

    public void win()
    {
        //calls WinCondition class and creates a new object "b" of that class. Implements
        // parameters. WinCondition changes Labels and disables buttons if there is a Winner
        WinCondition b = new WinCondition(butArray, oppArray, newTurn);
    }

    public JButton[][] playerOne()
    {
        //can be used to access playerOne from another class if needed
        return butArray;
    }

    public JButton[][] playerTwo()
    {
        //can be used to access playerTwo from another class if needed
        return oppArray;
    }

    public JLabel turnLabel()
    {
        return turn;
    }

    public void setupWindow()
    {
        //sets up size and title of window.
        window = getContentPane();
        window.setLayout(null);
        window.setSize(1500, 1000);
        window.setBackground(Color.black);
        setTitle("BattleShip");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addPlayerGrid()
    {
        //adds player Ones grid of buttons (2d Array [10][10] ) to a JPanel in a window. Adds
        // Action Listener to all the buttons
        player = new JPanel(new GridLayout());
        player.setBounds(100, 100, 400, 400);
        player.setLayout(new GridLayout(10, 10, 0, 0));
        butArray = new JButton[10][10];
        for (int rowsize = 0; rowsize < 10; rowsize++)
        {
            for (int colsize = 0; colsize < 10; colsize++)
            {
                butArray[rowsize][colsize] = new JButton();
                butArray[rowsize][colsize].addActionListener(this::actionPerformed);
                butArray[rowsize][colsize].setBackground(Color.white);
                butArray[rowsize][colsize].setOpaque(true);


                player.add(butArray[rowsize][colsize]);
            }

        }

        //adds player Twos grid of buttons (2d Array [10][10] ) to a JPanel in a window. Adds
        // Action Listener to all the buttons
        playerTwo = new JPanel(new GridLayout());
        playerTwo.setBounds(700, 100, 400, 400);
        playerTwo.setLayout(new GridLayout(10, 10, 0, 0));
        oppArray = new JButton[10][10];
        for (int r = 0; r < 10; r++)
        {
            for (int c = 0; c < 10; c++)
            {
                oppArray[r][c] = new JButton();
                oppArray[r][c].addActionListener(this::actionPerformed);
                oppArray[r][c].setBackground(Color.white);
                oppArray[r][c].setOpaque(true);

                playerTwo.add(oppArray[r][c]);

            }

        }

        //adds JPanels to the window
        window.add(player);
        window.add(playerTwo);
    }

    public void addStufftoWindow()
    {
        //Sets text color and font of title.
        title = new JLabel("BattleShip Player vs Player");
        title.setBounds(470, 0, 200, 100);
        title.setForeground(Color.cyan);
        title.setSize(300, 100);
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        window.add(title);

        //Labels player one and twos grids
        one = new JLabel("Player One");
        one.setForeground(Color.white);
        one.setBounds(270, 50, 200, 50);
        one.setFont(new Font("Calibri", Font.BOLD, 20));
        window.add(one);

        two = new JLabel("Player Two");
        two.setForeground(Color.white);
        two.setBounds(865, 50, 200, 50);
        two.setFont(new Font("Calibri", Font.BOLD, 20));
        window.add(two);

        //creates a Jlabel that gives directions on who's turn it is to place and count of ships
        turn = new JLabel("Player One place your ships : count " + (ships - 5));
        turn.setBounds(480, 600, 300, 100);
        turn.setForeground(Color.MAGENTA);
        turn.setFont(new Font("Calibri", Font.BOLD, 17));
        window.add(turn);

        //creates a JLabel that gives direction on who's turn it is to attack
        newTurn = new JLabel("Player One Attack Player Two");
        newTurn.setBounds(480, 600, 300, 100);
        newTurn.setVisible(false);
        newTurn.setFont(new Font("Calibri", Font.BOLD, 17));
        newTurn.setForeground(Color.MAGENTA);
        window.add(newTurn);

        //creates buttons that determine the direction of ship
        horizontal = new JButton("Place Horizontally right(inclusive)");
        horizontal.setBounds(300, 540, 250, 50);
        horizontal.addActionListener(this::actionPerformed);
        window.add(horizontal);

        vertical = new JButton("Place Vertically down(inclusive)");
        vertical.setBounds(600, 540, 250, 50);
        vertical.addActionListener(this::actionPerformed);
        window.add(vertical);

        test = new JButton("Test");
        test.setBounds(600, 900, 100, 50);
        test.addActionListener(this::actionPerformed);
        window.add(test);

        //Button used to start attacking
        startGame = new JButton("Start Game");
        startGame.setBounds(600, 700, 100, 50);
        startGame.addActionListener(this::actionPerformed);
        startGame.setVisible(false);
        window.add(startGame);

        //creates a Text Area of rules
        rules = new JTextArea("Player One starts by placing all 5 of his ships.                  " +
                "                             The count increment stands for how many ships are " +
                "left to place as well as the length of the ship                      Once both " +
                "players have placed their ships a Start Game button appears  that you must press" +
                " in order to start attacking                      " +
                "                          Both players take turns attacking. A  correct guess " +
                "will become green        and an incorrect guess will become red.                " +
                "                                              Note that at any point in the code" +
                " when placing ships if your attempt at placing a ship" +
                " results in going out  of bounds or overlaps, your count and grid will not be " +
                "afffected. This also applies for attacking a spot where       you have already " +
                "atttacked. You      won't lose your turn and will just have to attack correctly");
        rules.setEditable(false);
        rules.setLineWrap(true);
        rules.setBounds(1200, 150, 200, 400);
        rules.setBackground(Color.cyan);
        window.add(rules);

        info = new JLabel("Rules");
        info.setVisible(true);
        info.setBounds(1200, 50, 200, 100);
        info.setForeground(Color.BLACK);
        info.setFont(new Font("Calibri", Font.BOLD, 20));
        window.add(info);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton clickedButton = (JButton) e.getSource();
            //runs wincondition in the background
            win();

            //When start game is clicked the Attacking starts. ships goes to -1 and stops
            // affecting the code. boolean start is set to true
            if (clickedButton.equals(startGame))
            {
                newTurn.setText("Player One Attack Player Two");
                ships--;
                start = true;
                startGame.setVisible(false);
            }

            //a boolean is true if vertical false if horizontal
            if (clickedButton.equals(horizontal))
            {
                direction = false;
            }
            if (clickedButton.equals(vertical))
            {
                direction = true;
            }

            //ships starts at 10. As long as its greater than 5 its player Ones turn to place his
            // ship. if direction is false
            //player one places ship horizontally inclusive of where he clicked. ships minus 1.
            // turn label is updated to new count/length of ships
            //if statements prevent out of bounds and overlapping
            //set foreground is used to represent a ship as it doesn't affect the look of the button
            //There are 5 ships each with a decreasing length starting from 5 buttons
            for (int rowsize = 0; rowsize < 10; rowsize++)
            {
                for (int colsize = 0; colsize < 10; colsize++)
                {
                    if (clickedButton.equals(butArray[rowsize][colsize]))
                    {
                        if (ships > 5)
                        {
                            if (direction == false)
                            {
                                if (colsize + (ships - 5) <= 10)
                                {

                                    for (int i = ships - 1; i >= 5; i--)
                                    {
                                        int oneTest = 0;
                                        if (butArray[rowsize][colsize + (i - 5)].getForeground() == Color.white)
                                        {
                                            oneTest++;
                                        }
                                        firstTester += oneTest;
                                    }
                                    if (firstTester == 0)
                                    {
                                        for (int i = ships - 1; i >= 5; i--)
                                        {


                                            butArray[rowsize][colsize + (i - 5)].setForeground(Color.white);

                                        }


                                        ships--;
                                        if (ships >= 6)
                                            turn.setText("Player One place your ships : count " + (ships - 5));
                                        else
                                            turn.setText("Player Two Place your ships : count " + (ships));

                                    }

                                    firstTester = 0;
                                }


                            }

                            //if direction is true place vertically down inclusive of where clicked
                            else if (direction = true)
                            {
                                if (rowsize + (ships - 5) <= 10)
                                {
                                    for (int i = ships - 1; i >= 5; i--)
                                    {
                                        int VertOneTest = 0;
                                        if (butArray[rowsize + (i - 5)][colsize].getForeground() == Color.white)
                                        {
                                            VertOneTest++;
                                        }
                                        firstTester += VertOneTest;


                                    }
                                    if (firstTester == 0)
                                    {

                                        for (int i = ships - 1; i >= 5; i--)
                                        {
                                            butArray[rowsize + (i - 5)][colsize].setForeground(Color.white);
                                        }
                                        ships--;
                                        if (ships >= 6)
                                            turn.setText("Player One place your ships : count " + (ships - 5));
                                        else
                                            turn.setText("Player Two Place your ships : count " + (ships));


                                    }
                                    firstTester = 0;
                                }
                            }

                        }


                    }

                    //if the clicked button is PlayerTwos array
                    //ships has to between 1 and 5 for it to place ship since that signifies its
                    // player twos turn
                    else if (clickedButton.equals(oppArray[rowsize][colsize]))
                    {
                        if (ships <= 5 && ships >= 1)
                        {
                            if (direction == false)
                            {
                                if (colsize + (ships) <= 10)
                                {

                                    for (int i = ships - 1; i >= 0; i--)
                                    {
                                        int oneTest = 0;
                                        if (oppArray[rowsize][colsize + (i)].getForeground() == Color.white)
                                        {
                                            oneTest++;
                                        }
                                        firstTester += oneTest;
                                    }
                                    if (firstTester == 0)
                                    {
                                        for (int i = ships - 1; i >= 0; i--)
                                        {


                                            oppArray[rowsize][colsize + (i)].setForeground(Color.white);

                                        }


                                        ships--;
                                        turn.setText("Player Two place your ships : count " + (ships));

                                    }

                                    firstTester = 0;
                                }
                                //Note that firstTester tests for overlapping

                                //if vertical is true when player two button is clicked
                            }
                            else if (direction = true)
                            {
                                if (rowsize + (ships) <= 10)
                                {
                                    for (int i = ships - 1; i >= 0; i--)
                                    {
                                        int VertOneTest = 0;
                                        if (oppArray[rowsize + (i)][colsize].getForeground() == Color.white)
                                        {
                                            VertOneTest++;
                                        }
                                        firstTester += VertOneTest;


                                    }
                                    if (firstTester == 0)
                                    {

                                        for (int i = ships - 1; i >= 0; i--)
                                        {
                                            oppArray[rowsize + (i)][colsize].setForeground(Color.white);
                                        }
                                        ships--;
                                        turn.setText("Player Two place your ships : count " + (ships));


                                    }
                                    firstTester = 0;
                                }
                            }
                        }
                    }
                }

            }
            //Test button that was useful in determining where the ships were throughout the project
            //Removed during final game
            if (clickedButton.equals(test))
            {
                for (int r = 0; r < 10; r++)
                {
                    for (int c = 0; c < 10; c++)
                    {
                        if (butArray[r][c].getForeground() == (Color.white))
                        {
                            butArray[r][c].setBackground(Color.blue);
                        }

                        if (oppArray[r][c].getForeground() == Color.white)
                        {
                            oppArray[r][c].setBackground(Color.blue);
                        }
                    }

                }
            }
            //Once all ships have been placed display start game buttom so attacking can begin
            if (ships == 0)
            {

                turn.setVisible(false);
                startGame.setVisible(true);
                newTurn.setVisible(true);
                newTurn.setText("Press Start Game");
            }

            //if game has been started and its player ones turn when a ship spot is guessed on
            // player2's grid
            //then show green if guess is correct and red if guess is wrong if the guessed spot
            // has not already been guessed
            //Make playerOneAttack false and update Jlabel to show that its player twos turn to
            // attack
            //count of greens has to be less than or equal to 14 for the guess to work since if
            // its 15 a winner has been declared
            if (start == true)
            {
                for (int r = 0; r < 10; r++)
                {
                    for (int c = 0; c < 10; c++)
                    {
                        if (clickedButton.equals(oppArray[r][c]))
                        {
                            if (playerOneAttack == true)
                            {

                                if (oppArray[r][c].getBackground() != Color.green && oppArray[r][c].getBackground() != Color.red)
                                {
                                    if (oppArray[r][c].getForeground() == Color.white)
                                    {

                                        if (countOfGreens <= 14)
                                        {
                                            oppArray[r][c].setBackground(Color.green);
                                            playerOneAttack = false;
                                            countOfGreens++;
                                            if (countOfGreens <= 14)
                                            {
                                                newTurn.setText("Player Two Attack Player One");
                                            }
                                            else
                                            {
                                                win();
                                                for (int i = 0; i < 10; i++)
                                                {
                                                    for (int j = 0; j < 10; j++)
                                                    {
                                                        oppArray[i][j].removeActionListener(this::actionPerformed);
                                                        butArray[i][j].removeActionListener(this::actionPerformed);
                                                    }
                                                }
                                            }


                                        }

                                    }
                                    else
                                    {
                                        if (countOfGreens <= 14 && countOfGreensTwo <= 14)
                                        {
                                            oppArray[r][c].setBackground(Color.red);
                                            playerOneAttack = false;
                                            newTurn.setText("Player Two Attack PlayerOne");
                                        }
                                    }
                                }

                            }
                        }
                        else if (clickedButton.equals(butArray[r][c]))
                        {
                            if (playerOneAttack == false)
                            {

                                if (butArray[r][c].getBackground() != Color.green && butArray[r][c].getBackground() != Color.red)
                                {
                                    if (butArray[r][c].getForeground() == Color.white)
                                    {

                                        if (countOfGreensTwo <= 14)
                                        {
                                            butArray[r][c].setBackground(Color.green);
                                            playerOneAttack = true;
                                            countOfGreensTwo++;
                                            if (countOfGreensTwo <= 14)
                                                newTurn.setText("Player One Attack Player Two");
                                            else
                                            {
                                                //if countOfGreens = 15 run win() and disable
                                                // buttons
                                                win();
                                                for (int i = 0; i < 10; i++)
                                                {
                                                    for (int j = 0; j < 10; j++)
                                                    {
                                                        oppArray[i][j].removeActionListener(this::actionPerformed);
                                                        butArray[i][j].removeActionListener(this::actionPerformed);
                                                    }
                                                }

                                            }

                                        }
                                    }
                                    else
                                    {
                                        if (countOfGreensTwo <= 14 && countOfGreens <= 14)
                                        {
                                            butArray[r][c].setBackground(Color.red);
                                            playerOneAttack = true;
                                            newTurn.setText("Player One Attack Player Two");
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }
        }

    }


}
