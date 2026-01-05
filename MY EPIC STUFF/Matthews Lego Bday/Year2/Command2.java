import java.util.*;

public class Command2 {
   private int CHARLIMIT = 37;
   private String command = "";
   private ArrayList<ArrayList<String>> message = new ArrayList<ArrayList<String>>();
   private String firstTalker = "";
   private ArrayList<Integer> tecTalks = new ArrayList<Integer>();
   private ArrayList<Integer> matthewTalks = new ArrayList<Integer>();

   public Command2(ArrayList<String> mess) {
      if (mess == null || mess.size() < 2) {
         System.out.println(
               "ERROR: Command2 received invalid message list (size: " + (mess == null ? "null" : mess.size()) + ")");
         command = "";
         firstTalker = "";
         return;
      }
      command = mess.remove(0);
      firstTalker = mess.remove(0);
      String tempString = "";
      ArrayList<String> tempArray = new ArrayList<String>();
      boolean tecIsTalking = "tec".equals(firstTalker);
      int whoIsTalking = 0;
      for (String m : mess) {
         tempString = "";
         tempArray = new ArrayList<String>();
         for (String s : m.split(" ")) {
            if (tempString.length() + s.length() < CHARLIMIT)
               tempString += s + " ";
            else {
               if (tempArray.size() < 3)
                  tempArray.add(tempString.trim());
               else {
                  message.add(tempArray);
                  if (tecIsTalking)
                     tecTalks.add(whoIsTalking);
                  else
                     matthewTalks.add(whoIsTalking);
                  whoIsTalking++;
                  tempArray = new ArrayList<String>();
                  tempArray.add(tempString.trim());
               }
               tempString = s + " ";
            }
         }
         if (tempArray.size() < 3)
            tempArray.add(tempString.trim());
         else {
            message.add(tempArray);
            if (tecIsTalking)
               tecTalks.add(whoIsTalking);
            else
               matthewTalks.add(whoIsTalking);
            whoIsTalking++;
            tempArray = new ArrayList<String>();
            tempArray.add(tempString.trim());
         }
         while (tempArray.size() < 3)
            tempArray.add(" ");
         message.add(tempArray);
         if (tecIsTalking)
            tecTalks.add(whoIsTalking);
         else
            matthewTalks.add(whoIsTalking);
         whoIsTalking++;
         tecIsTalking = !tecIsTalking;
      }
   }

   public boolean checkCommand(String c) {
      return command.equals(c);
   }

   public ArrayList<ArrayList<String>> getMessage() {
      return message;
   }

   public String getTalker(int s) {
      if (tecTalks.contains(s))
         return "tec";
      else if (matthewTalks.contains(s))
         return "matthew";
      else
         return "???";
   }
}