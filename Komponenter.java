import java.awt.Graphics2D;
import java.awt.Color;

class Komponenter
{
    protected Boll[] balls;

    public Komponenter()
    {
        balls = new Boll[10];
        balls[0] = new Boll(new Punkt2D(100,170.7106781),100,new Vektor2D(1.0,-1),10000,Color.RED);
        balls[1] = new Boll(new Punkt2D(300,170.7106781),50,new Vektor2D(-1.0,0),2500,Color.BLUE);
        balls[2] = new Boll(new Punkt2D(300,370),50,new Vektor2D(6,1),2500,Color.GREEN);
        balls[3] = new Boll(new Punkt2D(135,370),50,new Vektor2D(7,0),2500,Color.ORANGE);
        balls[4] = new Boll(new Punkt2D(700,500),200,new Vektor2D(0,0),40000,Color.MAGENTA);
        balls[5] = new Boll(new Punkt2D(135,370),50,new Vektor2D(7,0),2500,Color.YELLOW);
        balls[6] = new Boll(new Punkt2D(135,370),50,new Vektor2D(7,0),2500,Color.PINK);
        balls[7] = new Boll(new Punkt2D(135,370),20,new Vektor2D(7,0),400,Color.GRAY);
        balls[8] = new Boll(new Punkt2D(135,370),10,new Vektor2D(7,0),100,Color.DARK_GRAY);
        balls[9] = new Boll(new Punkt2D(135,370),100,new Vektor2D(7,0),10000,Color.LIGHT_GRAY);
    }

    public void move()
    {
        collideFrame();
        collideBalls();
        for(int i = 0;i < balls.length;i++)
        {
            balls[i].move();
        }
    }

    public void collideFrame()
    {
        for(int i = 0;i < balls.length;i++)
        {
            if(balls[i].getX()-balls[i].getRadie() < 0)
            {
                balls[i].setSpeed(new Vektor2D(-balls[i].getSpeed().getX(),balls[i].getSpeed().getY()));
                balls[i].setX(balls[i].getRadie());
            }
            if(balls[i].getX()+balls[i].getRadie() > Consts.FRAMESIZEX)
            {
                balls[i].setSpeed(new Vektor2D(-balls[i].getSpeed().getX(),balls[i].getSpeed().getY()));
                balls[i].setX(Consts.FRAMESIZEX-balls[i].getRadie());
            }
            if(balls[i].getY()-balls[i].getRadie() < 0)
            {
                balls[i].setSpeed(new Vektor2D(balls[i].getSpeed().getX(),-balls[i].getSpeed().getY()));
                balls[i].setY(balls[i].getRadie());
            }
            if(balls[i].getY()+balls[i].getRadie() > Consts.FRAMESIZEY)
            {
                balls[i].setSpeed(new Vektor2D(balls[i].getSpeed().getX(),-balls[i].getSpeed().getY()));
                balls[i].setY(Consts.FRAMESIZEY-balls[i].getRadie());
            }
        }
    }

    public void collideBalls()
    {
        boolean[] checked = new boolean[balls.length];
        for(int i = 0;i < checked.length;i++)
        {
            checked[i] = false;
        }
        for(int i = 0;i < balls.length;i++)
        {
            for(int j = 0;j < balls.length;j++)
            {
                if(i != j && !checked[i] && !checked[j])
                {
                    if(Math.sqrt((balls[i].getX()-balls[j].getX())*(balls[i].getX()-balls[j].getX())+(balls[i].getY()-balls[j].getY())*(balls[i].getY()-balls[j].getY())) < balls[i].getRadie()+balls[j].getRadie())
                    {
                        Vektor2D riktA = new Vektor2D(balls[j].getX()-balls[i].getX(),balls[j].getY()-balls[i].getY());
                        Vektor2D riktB = new Vektor2D(balls[i].getX()-balls[j].getX(),balls[i].getY()-balls[j].getY());
                        riktA.normera();
                        riktB.normera();
                        while(Math.sqrt((balls[i].getX()-balls[j].getX())*(balls[i].getX()-balls[j].getX())+(balls[i].getY()-balls[j].getY())*(balls[i].getY()-balls[j].getY())) < balls[i].getRadie()+balls[j].getRadie())
                        {
                            if(riktA.getX() == 0 && riktA.getY() == 0)
                            {
                                break;
                            }
                            balls[i].addPunkt2D(new Punkt2D(riktB));
                            balls[j].addPunkt2D(new Punkt2D(riktA));
                        }
                        Vektor2D vertA = Vektor2D.projektion(balls[i].getSpeed(),riktA);
                        Vektor2D vertB = Vektor2D.projektion(balls[j].getSpeed(),riktB);
                        Vektor2D horiA = balls[i].getSpeed();
                        horiA.subtract(Vektor2D.projektion(horiA,riktA));
                        Vektor2D horiB = balls[j].getSpeed();
                        horiB.subtract(Vektor2D.projektion(horiB,riktB));

                        double oldspeedA = vertA.abs();
                        double oldspeedB = vertB.abs();

                        if(vertA.getArg() != null)
                        if(vertA.getArg() <= -3*Math.PI/4 || vertA.getArg() > Math.PI/4)
                        {
                            oldspeedA *= -1;
                        }
                        if(vertB.getArg() != null)
                        if(vertB.getArg() <= -3*Math.PI/4 || vertB.getArg() > Math.PI/4)
                        {
                            oldspeedB *= -1;
                        }

                        double speedA = (balls[i].getMassa()*oldspeedA+balls[j].getMassa()*oldspeedB+balls[j].getMassa()*(oldspeedB-oldspeedA))/(balls[i].getMassa()+balls[j].getMassa());
                        double speedB = (balls[j].getMassa()*oldspeedB+balls[i].getMassa()*oldspeedA+balls[i].getMassa()*(oldspeedA-oldspeedB))/(balls[j].getMassa()+balls[i].getMassa());

                        vertA = new Vektor2D(riktB.getX(),riktB.getY());
                        vertB = new Vektor2D(riktA.getX(),riktA.getY());
                        vertA.normera();
                        vertB.normera();

                        if(vertA.getArg() != null)
                        if(vertA.getArg() <= -3*Math.PI/4 || vertA.getArg() > Math.PI/4)
                        {
                            vertA.addArg(Math.PI);
                        }
                        if(vertB.getArg() != null)
                        if(vertB.getArg() <= -3*Math.PI/4 || vertB.getArg() > Math.PI/4)
                        {
                            vertB.addArg(Math.PI);
                        }

                        vertA.multiplicera(speedA);
                        vertB.multiplicera(speedB);

                        balls[i].setSpeed(new Vektor2D(vertA.getX()+horiA.getX(),vertA.getY()+horiA.getY()));
                        balls[j].setSpeed(new Vektor2D(vertB.getX()+horiB.getX(),vertB.getY()+horiB.getY()));
                        checked[i] = true;
                        checked[j] = true;
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g)
    {
        for(int i = 0;i < balls.length;i++)
        {
            balls[i].draw(g);
        }
        g.setColor(balls[0].getColor());
        g.drawString(""+(int)balls[0].getEnergi(),5,20);
        g.setColor(balls[1].getColor());
        g.drawString(""+(int)balls[1].getEnergi(),5,30);
        g.setColor(balls[2].getColor());
        g.drawString(""+(int)balls[2].getEnergi(),5,40);
        g.setColor(balls[3].getColor());
        g.drawString(""+(int)balls[3].getEnergi(),5,50);
        g.setColor(balls[4].getColor());
        g.drawString(""+(int)balls[4].getEnergi(),5,60);
        g.setColor(balls[5].getColor());
        g.drawString(""+(int)balls[5].getEnergi(),5,70);
        g.setColor(balls[6].getColor());
        g.drawString(""+(int)balls[6].getEnergi(),5,80);
        g.setColor(balls[7].getColor());
        g.drawString(""+(int)balls[7].getEnergi(),5,90);
        g.setColor(balls[8].getColor());
        g.drawString(""+(int)balls[8].getEnergi(),5,100);
        g.setColor(balls[9].getColor());
        g.drawString(""+(int)balls[9].getEnergi(),5,110);
        g.setColor(Color.BLACK);
        g.drawString(""+(int)(balls[0].getEnergi()+balls[1].getEnergi()+balls[2].getEnergi()+balls[3].getEnergi()+balls[4].getEnergi()+balls[5].getEnergi()+balls[6].getEnergi()+balls[7].getEnergi()+balls[8].getEnergi()+balls[9].getEnergi()),5,130);
    }
}
