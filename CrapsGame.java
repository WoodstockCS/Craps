// Implements the game of Craps logic

public class CrapsGame
{
  private int point = 0;
  private int lastRoll = 0;

  /**
   *  Calculates the result of the next dice roll in the Craps game.
   *  The parameter total is the sum of dots on two dice.
   *  point is set to the saved total, if the game continues,
   *  or 0, if the game has ended.
   *  Returns 1 if player won, -1 if player lost,
   *  0 if player continues rolling.
   */
  public int processRoll(int total)
  { 
    int result=0;
    while (point==0)
    {
      if(total==7 || total==11)
      {
         result=1;
         return result;
      }
      if(total==12 || total==2 || total==3)
      {
         result=-1;
         return result;
      }
      else       
      {
         break;
      }

    }
   
    if (total==7)
    {
      result=-1;
      point=0;
    }
    if (total==point)
    {
      result=1;
      point=0;
    }
    if (result==0)
    {
      point+=total;
    }
    if(total==lastRoll)
    {
      result=1;
      point=0;
    }
    lastRoll=total;
    return result;
  }

  /**
   *  Returns the saved point
   */
  public int getPoint()
  {
    return point;
  }
}

