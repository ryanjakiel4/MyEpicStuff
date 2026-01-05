public class CircleShape extends MyShape
{
   private int radius;
   public CircleShape(int x, int y, Color c, int r)
   {
      super(x, y, c);
      radius = r;
   }
   public int getRadius()
   {
      return radius;
   }
   public void setRadius(int newRad)
   {
      radius = newRad;
   }
   public void draw(Graphics g)
   {
      g.setColor(this.getColor());
      g.drawOval(x + radius, y + radius, radius, radius);
   }
}