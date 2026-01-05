import java.io.*;
import java.util.*;
public class UnknownLetter
{
   private ArrayList<String> letterPossibilities = new ArrayList<String>();
   private ArrayList<String> newPossibilities = new ArrayList<String>();
   private int identity;
   public UnknownLetter(int i)
   {
      identity = i;
   }
   public int identity()
   {
      return identity;
   }
   public void addLetter(String s)
   {
   if(!newPossibilities.contains(s))
      newPossibilities.add(s);
   }
   public ArrayList<String> getPossibilities()
   {
      return letterPossibilities;
   }
   public void setPossibilities()
   {
      ArrayList<String> temp = new ArrayList<String>();
      for(String l : newPossibilities)
         if(!letterPossibilities.contains(l))
            temp.add(l); 
      letterPossibilities = temp;
      newPossibilities = new ArrayList<String>();
   }
   public String toString()
   {
      return ""+identity;
   }
}