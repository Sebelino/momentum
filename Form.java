import java.awt.Graphics2D;

abstract public class Form
{
    protected Punkt2D mittpt;

    public Form(){}

    public Form(Punkt2D mittpt)
    {
        this.mittpt = new Punkt2D(mittpt);
    }

    abstract public void draw(Graphics2D g);

    public Punkt2D getPunkt2D()
    {
        return mittpt;
    }

    public double getX()
    {
        return mittpt.getX();
    }

    public double getY()
    {
        return mittpt.getY();
    }

    public void setPunkt2D(Punkt2D value)
    {
        mittpt = value;
    }

    public void addPunkt2D(Punkt2D value)
    {
        mittpt.add(value);
    }

    public void setX(double value)
    {
        mittpt.setX(value);
    }

    public void setY(double value)
    {
        mittpt.setY(value);
    }
}
