import java.awt.Color;

class Boll extends Cirkel implements Sprite
{
    private Vektor2D speed;
    private double massa;

    public Boll(Punkt2D p,double radie,Vektor2D speed,double massa,Color color)
    {
        super(p,radie,color);
        this.speed = new Vektor2D(speed);
        this.massa = massa;
    }

    public void move()
    {
        mittpt.add(new Punkt2D(speed));
    }

    public double getMassa()
    {
        return massa;
    }

    public Vektor2D getMomentum()
    {
        Vektor2D mom = new Vektor2D(massa*speed.getX(),massa*speed.getY());
        return mom;
    }

    public double getEnergi()
    {
        return 0.5*massa*speed.abs()*speed.abs();
    }

    public Vektor2D getSpeed()
    {
        return speed;
    }

    public void setSpeed(Vektor2D value)
    {
        speed = value;
    }

    public void setRiktning(Vektor2D value)
    {
        value.normera();
        value.multiplicera(speed.abs());
        speed = value;
    }

    public void multipliceraSpeed(double value)
    {
        speed.multiplicera(value);
    }

    public void normeraSpeed()
    {
        speed.normera();
    }
}
