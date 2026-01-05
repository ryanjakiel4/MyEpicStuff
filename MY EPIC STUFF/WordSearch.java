import java.util.*;
   import java.io.*;
   public class WordSearch
   {
   //   3   2   1 
   //    \  |  /
   //     \ | /
   // 4 ----- ----- 0
   //     / | \
   //    /  |  \
   //   5   6   7 
	private ArrayList<String> yippee;
      private String[][] words;
		private String randomLetters = "abcdefghijklmnopqrstuvwxyz";
      public WordSearch(int x, int y)
      {
         words = new String[x][y];
      }
      public void generate(ArrayList<String> stuff)
      {
		yippee = stuff;
		         for(int r = 0; r < stuff.size(); r++)
         {
            String w = stuff.get(r);
            Map<Coordinate, ArrayList<Direction>> awesome = getPossibleLocations(w);
            Set<Coordinate> wicked = awesome.keySet();
            Coordinate coor = chooseCoordinate(wicked);
				if(coor == null)
					System.out.println(w+" didnt fit into this puzzle... either adjust the size or put the larger words closer to the top of the list.");
					else
            placeWord(w,coor, chooseDirection(awesome.get(coor)));
         }
      }
      public void placeWord(String word, Coordinate c, Direction d)
      {
         int x = c.myX;
         int y = c.myY;
         for(int j = 0; j < word.length(); j++)
         {
            if(words[x][y] == null || words[x][y].equals(word.substring(j,j+1)))
               words[x][y] = word.substring(j,j+1);
            else
               System.out.println("Something went wrong with the placement of " + word + " at character " + j);
            x = d.incX(x);
            y = d.incY(y);
         }
			randomLetters+=word;
      }
      public Coordinate chooseCoordinate(Set<Coordinate> awesome)
      {
         int w = (int)(Math.random() * awesome.size());
         Coordinate c = null;
         Iterator<Coordinate> it = awesome.iterator();
         for (int q = w ; q > 0 && it.hasNext(); q--)
            c = it.next();
         return c;
      }
      public Direction chooseDirection(ArrayList<Direction> wicked)
      {
         return wicked.get((int)(Math.random() * wicked.size()));
      }
      public Map<Coordinate, ArrayList<Direction>> getPossibleLocations(String word)
      {
         Map<Coordinate, ArrayList<Direction>> locations = new HashMap<Coordinate, ArrayList<Direction>>();
         for(int x = 0; x < words.length; x++)
            for(int y = 0; y < words[0].length; y++)
            {
               Coordinate quin = new Coordinate(x,y);
               ArrayList<Direction> jake= getPossibleDirections(word, quin);
               if( jake.size() > 0)
                  locations.put(quin,jake); 
            }
         return locations;
      }
      public ArrayList<Direction> getPossibleDirections(String word, Coordinate c)
      {
         ArrayList<Direction> fin = new ArrayList<Direction>();
         for(int x = 0; x < 8; x++)
            if(checkDirection(word,c,new Direction(x)))
               fin.add(new Direction(x));
         return fin;
      }
      public boolean checkDirection(String word, Coordinate c, Direction d)
      {
         int x = c.myX;
         int y = c.myY;
         for(int a = 0; a < word.length(); a++)
         {
            if(x < 0 || x > words.length - 1 || y < 0 || y > words[0].length - 1)
               return false;
            else if(words[x][y] != null && !words[x][y].equals(word.substring(a,a+1)))
               return false;
            x = d.incX(x);
            y = d.incY(y);
         }
         return true;
      }
      public void print(String destination) throws IOException
      {
         String alph = randomLetters;
         PrintWriter pw = new PrintWriter(new FileWriter(destination));
         for(int x = 0; x < words.length; x++)
         {
            String line = "";
            for(int y = 0; y < words[0].length; y++)
            {
               if(words[x][y] != null)
                  line += words[x][y] + " ";
               else
               {
                  int r = (int)(Math.random() * alph.length());
                  line += alph.substring(r,r+1) + " ";
               }
            }
            pw.println(line.substring(0, line.length()-1));
         }
			pw.println();
			for(int o = 0; o < yippee.size(); o++)
				pw.println(yippee.get(o));
			pw.close();
      }
   }