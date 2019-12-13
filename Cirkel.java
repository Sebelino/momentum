import java.awt.Graphics2D;
import java.awt.Color;

class Cirkel extends Form
{
    protected double radie;
    protected Color color;

    public Cirkel(Punkt2D p,double radie,Color color)
    {
        super(p);
        this.radie = radie;
        this.color = color;
    }

    public double getRadie()
    {
        return radie;
    }

    public boolean inside(Punkt2D p)
    {
        Punkt2D delta = Punkt2D.subtract(p,mittpt);
        return (delta.abs() < radie);
    }

    public boolean inside(Cirkel cirkel)
    {
        Punkt2D delta = Punkt2D.subtract(cirkel.getPunkt2D(),mittpt);
        return (delta.abs() < cirkel.getRadie()-radie);
    }

    public boolean touches(Cirkel cirkel)
    {
        Punkt2D delta = Punkt2D.subtract(cirkel.getPunkt2D(),mittpt);
        return (delta.abs() < radie+cirkel.getRadie());
    }

    public Color getColor()
    {
        return color;
    }

    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.fillOval((int)(mittpt.getX()-radie),(int)(mittpt.getY()-radie),2*(int)radie,2*(int)radie);
        g.setColor(Color.BLACK);
    }
}
