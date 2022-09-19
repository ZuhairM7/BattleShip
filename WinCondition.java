import javax.swing.*;
import java.awt.*;

public class WinCondition
{
    //instance variable counters for correct ship guesses
    private int oneCounter;
    private int twoCounter;

    public WinCondition(JButton[][] arrayOne, JButton[][] arrayTwo, JLabel random)
    {
        //Counts num of buttons in both player one and two arrays grids that are green
        //if green = 15 for one of the grids the opposing grid wins and turn label is updated to
        // declare winner
        for (int r = 0; r < 10; r++)
        {
            for (int c = 0; c < 10; c++)
            {
                if (arrayOne[r][c].getBackground() == Color.green)
                {
                    oneCounter++;
                }
                if (arrayTwo[r][c].getBackground() == Color.green)
                {
                    twoCounter++;
                }
            }
        }
        if (oneCounter == 15)
        {
            random.setText("Player Two Wins");
            random.setForeground(Color.blue);

        }
        if (twoCounter == 15)
        {
            random.setText("Player One Wins");
            random.setForeground(Color.blue);

        }


    }


}
