import java.util.*;
   import java.io.*;
   public class GenerateWordSearch
   {
      public static void main(String[] args) throws FileNotFoundException, IOException
      {
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter filename of txt file with size of word search (rows <space> columns) on the first row, then words on each row afterward.");
         Scanner sc1 = new Scanner(new File(sc.nextLine()));
         System.out.println("Now enter the name of the destination txt file:");
      	String dest = sc.nextLine();
      	sc.close();
         WordSearch harmony = new WordSearch(sc1.nextInt(), sc1.nextInt());
         sc1.nextLine();
         ArrayList<String> s = new ArrayList<String>();
         while(sc1.hasNextLine())
            s.add(sc1.nextLine());
         sc1.close();
         harmony.generate(s);
         harmony.print(dest);
         System.exit(1);	
      }
   }