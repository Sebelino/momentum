public class Vektor2D
{
    private double x;
    private double y;

    public Vektor2D(){}

    public Vektor2D(Vektor2D vekt)
    {
        x = vekt.getX();
        y = vekt.getY();
    }

    public Vektor2D(Punkt2D p)
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    public Vektor2D(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vektor2D(double skalar,Vektor2D vekt)
    {
        this.x = vekt.getX();
        this.y = vekt.getY();
        normera();
        multiplicera(skalar);
    }

    public Vektor2D(double skalar,double x,double y)
    {
        this.x = x;
        this.y = y;
        normera();
        multiplicera(skalar);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX(double value)
    {
        this.x = x;
    }

    public void setY(double value)
    {
        this.y = y;
    }

    public double abs() //Absolutbelopp.
    {
        return Math.sqrt(x*x+y*y);
    }

    public static Vektor2D add(Vektor2D v1,Vektor2D v2)
    {
        return new Vektor2D((v1.getX()+v2.getX()),(v1.getY()+v2.getY()));
    }

    public void add(Vektor2D v)
    {
        x += v.getX();
        y += v.getY();
    }

    public static Vektor2D subtract(Vektor2D v1,Vektor2D v2)
    {
        return new Vektor2D((v1.getX()-v2.getX()),(v1.getY()-v2.getY()));
    }

    public void subtract(Vektor2D v)
    {
        x -= v.getX();
        y -= v.getY();
    }

    public void multiplicera(double skalar)
    {
        x *= skalar;
        y *= skalar;
    }

    public void normera()
    {
        if(abs() != 0)
        {
            multiplicera(1/abs());
        }
    }

    public static Vektor2D normera(Vektor2D v)
    {
        if(v.abs() != 0)
        {
            v.multiplicera(1/v.abs());
        }
        return v;
    }

    public static double skalarprodukt(Vektor2D v1,Vektor2D v2)
    {
        return v1.getX()*v2.getX()+v1.getY()*v2.getY();
    }

    public static Vektor2D projektion(Vektor2D v,Vektor2D riktning)
    {
        riktning.normera();
        return new Vektor2D(skalarprodukt(v,riktning),riktning.getX(),riktning.getY());
    }

    public void projektion(Vektor2D riktning)
    {
        riktning.normera();
        double skalar = x*riktning.getX()+y*riktning.getY();
        x = riktning.getX();
        y = riktning.getY();
        multiplicera(skalar);
    }

    public void setArg(double value)
    {
        double abs = abs();
        x = Math.cos(value);
        y = Math.sin(value);
        multiplicera(abs);
    }

    public void addArg(double value)
    {
        setArg(getArg()+value);
    }

    public Double getArg()
    {
        if(getArgRadians() == null)
        {
            return null;
        }
        return new Double(getArgRadians());
    }

    public Double getArgRadians()
    {
        if(x == 0 &&
           y > 0)
        {
            return new Double(Math.PI/2);
        }
        else if(x == 0 &&
                y < 0)
        {
            return new Double(-Math.PI/2);
        }
        else if(x > 0 &&
                y == 0)
        {
            return new Double(0);
        }
        else if(x < 0 &&
                y == 0)
        {
            return new Double(Math.PI);
        }
        else if(x > 0 &&
                y > 0)
        {
            return new Double(Math.atan(y/x));
        }
        else if(x < 0 &&
                y > 0)
        {
            return new Double(Math.PI+Math.atan(y/x));
        }
        else if(x < 0 &&
                y < 0)
        {
            return new Double(-Math.PI+Math.atan(y/x));
        }
        else if(x > 0 &&
                y < 0)
        {
            return new Double(Math.atan(y/x));
        }
        return null;
    }

    public Double getArgDegrees()
    {
        if(getArgRadians() == null)
        {
            return null;
        }
        return new Double(180/Math.PI*getArgRadians());
    }
}
