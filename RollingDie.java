import java.awt.Color;
import java.awt.Graphics;

public class RollingDie
  extends Die
{
  private static final double slowdown = 0.97D;
  private static final double speedFactor = 0.04D;
  private static final double speedLimit = 2.0D;
  private static int tableLeft;
  private static int tableRight;
  private static int tableTop;
  private static int tableBottom;
  private final int dieSize = 24;
  private int xCenter;
  private int yCenter;
  private double xSpeed;
  private double ySpeed;
  
  public static void setBounds(int left, int right, int top, int bottom)
  {
    tableLeft = left;
    tableRight = right;
    tableTop = top;
    tableBottom = bottom;
  }
  
  public RollingDie()
  {
    xCenter = -1;
    yCenter = -1;
  }
  
  public void roll()
  {
    super.roll();
    int width = tableRight - tableLeft;
    int height = tableBottom - tableTop;
    xCenter = tableLeft;
    yCenter = (tableTop + height / 2);
    xSpeed = (width * (Math.random() + 1.0D) * 0.04D);
    ySpeed = (height * (Math.random() - 0.5D) * 2.0D * 0.04D);
  }
  
  public boolean isRolling()
  {
    return (xSpeed > 2.0D) || (xSpeed < -2.0D) || (ySpeed > 2.0D) || (ySpeed < -2.0D);
  }
  
  public void avoidCollision(RollingDie other)
  {
    if (other == this) {
      return;
    }
    while ((Math.abs(xCenter - other.xCenter) < 24) && (Math.abs(yCenter - other.yCenter) < 24)) {
      move();
    }
  }
  
  private void move()
  {
    this.xCenter = ((int)(xCenter + xSpeed));
    this.yCenter = ((int)(yCenter + ySpeed));
    
    int radius = 12;
    if (xCenter < tableLeft + radius)
    {
      xCenter = (tableLeft + radius);
      xSpeed = (-xSpeed);
    }
    if (xCenter > tableRight - radius)
    {
      xCenter = (tableRight - radius);
      xSpeed = (-xSpeed);
    }
    if (yCenter < tableTop + radius)
    {
      yCenter = (tableTop + radius);
      ySpeed = (-ySpeed);
    }
    if (yCenter > tableBottom - radius)
    {
      yCenter = (tableBottom - radius);
      ySpeed = (-ySpeed);
    }
  }
  
  public void draw(Graphics g)
  {
    if ((xCenter < 0) || (yCenter < 0)) {
      return;
    }
    if (isRolling())
    {
      move();
      drawRolling(g);
      xSpeed *= 0.97D;
      ySpeed *= 0.97D;
    }
    else
    {
      drawStopped(g);
    }
  }
  
  private void drawRolling(Graphics g)
  {
    int x = xCenter - 12 + (int)(3.0D * Math.random()) - 1;
    int y = yCenter - 12 + (int)(3.0D * Math.random()) - 1;
    g.setColor(Color.RED);
    if (x % 2 != 0) {
      g.fillRoundRect(x, y, 24, 24, 6, 6);
    } else {
      g.fillOval(x - 2, y - 2, 28, 28);
    }
    Die die = new Die();
    die.roll();
    drawDots(g, x, y, die.getNumDots());
  }
  
  private void drawStopped(Graphics g)
  {
    int x = xCenter - 12;
    int y = yCenter - 12;
    g.setColor(Color.RED);
    g.fillRoundRect(x, y, 24, 24, 6, 6);
    drawDots(g, x, y, getNumDots());
  }
  
  private void drawDots(Graphics g, int x, int y, int numDots)
  {
    g.setColor(Color.WHITE);
    
    int dotSize = 6;
    int step = 3;
    int x1 = x + step - 1;
    int x2 = x + 3 * step;
    int x3 = x + 5 * step + 1;
    int y1 = y + step - 1;
    int y2 = y + 3 * step;
    int y3 = y + 5 * step + 1;
    switch (numDots)
    {
    case 1: 
      g.fillOval(x2, y2, dotSize, dotSize);
      break;
    case 2: 
      g.fillOval(x1, y1, dotSize, dotSize);
      g.fillOval(x3, y3, dotSize, dotSize);
      break;
    case 3: 
      g.fillOval(x1, y1, dotSize, dotSize);
      g.fillOval(x2, y2, dotSize, dotSize);
      g.fillOval(x3, y3, dotSize, dotSize);
      break;
    case 4: 
      g.fillOval(x1, y1, dotSize, dotSize);
      g.fillOval(x3, y1, dotSize, dotSize);
      g.fillOval(x1, y3, dotSize, dotSize);
      g.fillOval(x3, y3, dotSize, dotSize);
      break;
    case 5: 
      g.fillOval(x1, y1, dotSize, dotSize);
      g.fillOval(x3, y1, dotSize, dotSize);
      g.fillOval(x2, y2, dotSize, dotSize);
      g.fillOval(x1, y3, dotSize, dotSize);
      g.fillOval(x3, y3, dotSize, dotSize);
      g.fillOval(x2, y2, dotSize, dotSize);
      break;
    case 6: 
      g.fillOval(x1, y1, dotSize, dotSize);
      g.fillOval(x1, y2, dotSize, dotSize);
      g.fillOval(x1, y3, dotSize, dotSize);
      g.fillOval(x3, y1, dotSize, dotSize);
      g.fillOval(x3, y2, dotSize, dotSize);
      g.fillOval(x3, y3, dotSize, dotSize);
    }
  }
}
