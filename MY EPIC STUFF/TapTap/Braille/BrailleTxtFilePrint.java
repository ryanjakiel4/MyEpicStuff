import java.util.*;
import java.io.*;
public class BrailleTxtFilePrint
{
   private static int amountOfStartingLetters = 1;
   private static String alphabet = "qwertyuiopasdfghjklzxcvbnm";
   private static ArrayList<String> words = new ArrayList<String>();
   private static ArrayList<String> knownLetters  = new ArrayList<String>();
   private static ArrayList<UnknownLetter> unknownLetters  = new ArrayList<UnknownLetter>();
   private static String tempWord;
   private static ArrayList<String> tempChars = new ArrayList<String>();
   private static ArrayList<String> tempComputerChars = new ArrayList<String>();
   private static int testCount = 0;
   private static PrintWriter pw;
   private static boolean wordFound;
   public static void main(String[] args) throws FileNotFoundException
   {
      getKnownLetters();
      try
      {
         Scanner sc = new Scanner(new File("dictionary.txt"));
         while(sc.hasNextLine())
         {
            String st = sc.nextLine();
            if(!st.contains("-") && !st.contains("'"))
               words.add(st);
         }
         sc.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not Found");
         System.exit(1);
      }
      pw =  new PrintWriter(new File("results.txt"));
      while(26 - knownLetters.size() != 0)
      {
         pw.println("Test #"+testCount);
         pw.println("Known Letters  :" + knownLetters);
         pw.println("Unknown Letters:" + unknownLetters);
         wordFound = false;
         while(!wordFound)
         {
            tempWord = words.get((int)(Math.random()*words.size()));
            for(String s : tempWord.split(""))
               if(!knownLetters.contains(s))
               {
                  wordFound = true;
                  break;
               }
         }
         pw.println("Word:                 "+tempWord);
         for(String s : tempWord.split(""))
         {
            if(getIdentity(s) != -1)
            {
               tempChars.add(s);
               if(knownLetters.contains(s))
                  tempComputerChars.add(s);
               else
               {
                  UnknownLetter ul = new UnknownLetter(getIdentity(s));
                  if(!containsUnknownLetter(s))
                     unknownLetters.add(ul);
                  tempComputerChars.add(""+ul.identity());
               }
            }
         }
         pw.println("Characters In Word:   "+tempChars);
         pw.println("How Computer Sees it: "+tempComputerChars);
         pw.println("Possible Words:");
         ArrayList<String> wordPossibilities = getWordPossibilities(tempComputerChars);
         pw.println("Updated Letter Possibilities (if available):");
         getLetterPossibilities(wordPossibilities, tempComputerChars);
         pw.println("--------------------------------------------------------------------------------------------");
         getNewKnownLetters();
         tempChars = new ArrayList<String>();
         tempComputerChars = new ArrayList<String>();
         testCount++;
      }
      pw.close();
   }
   public static ArrayList<String> getWordPossibilities(ArrayList<String> computerLetters)
   {
      ArrayList<String> possibleWords = new ArrayList<String>();
      for(String word : words)
         if(computerLetters.size() == (word.length()) && doesWordMatch(word,computerLetters))
         {
            possibleWords.add(word);
            pw.println(word);
         }
      return possibleWords;
   }
   public static void getLetterPossibilities(ArrayList<String> possibleWords, ArrayList<String> computerLetters)
   {
      for(String word : possibleWords)
         for(int n = 0; n < word.length(); n++)
            if(computerLetters.get(n).length() > 1)
               for(UnknownLetter ul : unknownLetters)
                  if(Integer.parseInt(computerLetters.get(n)) == ul.identity())
                     ul.addLetter(word.substring(n,n+1));
      for(UnknownLetter ul : unknownLetters)
      {
         ul.setPossibilities();
         pw.println(ul.identity() + "" + ul.getPossibilities());
      }
   }
   public static void getKnownLetters()
   {
      while(knownLetters.size() < amountOfStartingLetters)
      {
         int a = (int)(Math.random()*26);
         String r = alphabet.substring(a,a+1);
         if(!knownLetters.contains(r))
            knownLetters.add(r);
      }
   }
   public static boolean doesWordMatch(String word, ArrayList<String> computerLetters)
   {
      for(int n = 0; n < word.length()-1; n++)
         if(computerLetters.get(n).length() == 1)
            if(!computerLetters.get(n).equals(word.substring(n,n+1)))
               return false;
      return true;
   }
   public static void getNewKnownLetters()
   {
      for(int n = 0; n < unknownLetters.size(); n++)
      {
         UnknownLetter ul = unknownLetters.get(n);
         ArrayList<String> let = ul.getPossibilities();
         if(ul.getPossibilities().size() == 1)
         {
            if(!knownLetters.contains(let.get(0)))
               knownLetters.add(let.get(0));
            unknownLetters.remove(n);
         }
      }
   }
   public static boolean containsUnknownLetter(String str)
   {
      for(UnknownLetter ul : unknownLetters)
         if(ul.identity() == getIdentity(str))
            return true;
      return false;
   }
   public static int getIdentity(String st)
   {
      String str = st.toLowerCase();
      if(str.equals("a"))
         return 10;
      if(str.equals("b"))
         return 20;
      if(str.equals("c"))
         return 30;
      if(str.equals("d"))
         return 40;
      if(str.equals("e"))
         return 50;
      if(str.equals("f"))
         return 60;
      if(str.equals("g"))
         return 70;
      if(str.equals("h"))
         return 80;
      if(str.equals("i"))
         return 90;
      if(str.equals("j"))
         return 100;
      if(str.equals("k"))
         return 110;
      if(str.equals("l"))
         return 120;
      if(str.equals("m"))
         return 130;
      if(str.equals("n"))
         return 140;
      if(str.equals("o"))
         return 150;
      if(str.equals("p"))
         return 160;
      if(str.equals("q"))
         return 170;
      if(str.equals("r"))
         return 180;
      if(str.equals("s"))
         return 190;
      if(str.equals("t"))
         return 200;
      if(str.equals("u"))
         return 210;
      if(str.equals("v"))
         return 220;
      if(str.equals("w"))
         return 230;
      if(str.equals("x"))
         return 240;
      if(str.equals("y"))
         return 250;
      if(str.equals("z"))
         return 260;
      return -1;
   }
}