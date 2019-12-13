class SimUtil
{
    public static boolean insideFrame(Punkt2D p)
    {
        return (p.getX() > 0 &&
                p.getY() > 0 &&
                p.getX() < Consts.FRAMESIZEX &&
                p.getY() < Consts.FRAMESIZEY);
    }

    public static int avrunda(double value)
    {
        if(value-(int)value >= 0.5)
        {
            return (int)value+1;
        }
        return (int)value;
    }
}
