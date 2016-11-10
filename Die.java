public class Die
{
   private int dieValue;
      
   public void roll()
   {
    dieValue = (int) ((Math.random()*6)+1);

   }
   public int getNumDots()
   {
      return dieValue; 
      
   }
   public static void main(String[] args)
   {
      Die die =new Die();
      die.roll();
      System.out.println(die.getNumDots());
      die.roll();
      System.out.println(die.getNumDots());

   }
}