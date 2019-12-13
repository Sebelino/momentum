import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

class Simulation extends JFrame
                 implements Runnable
{
    private BufferedImage buffer;
    private Graphics2D bufferg;

    private Komponenter komps;

    public Simulation()
    {
        komps = new Komponenter();
    }

    public void run()
    {
        if(buffer == null)
            buffer = (BufferedImage)createImage(Consts.FRAMESIZEX,Consts.FRAMESIZEY);
        while(true)
        {
            rita();
            try
            {
                Thread.sleep(SimUtil.avrunda(1000/Consts.FPS));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            uppdatera();
        }
    }

    public void uppdatera()
    {
        komps.move();
    }

    public void rita()
    {
        if(bufferg == null)
        {
            bufferg = (Graphics2D)buffer.getGraphics();
        }
        radera(bufferg);
        draw(bufferg);
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.drawImage(buffer,0,0,this);
        g.dispose();
    }

    private void draw(Graphics2D g)
    {
        komps.draw(g);
    }

    private void radera(Graphics2D g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,Consts.FRAMESIZEX,Consts.FRAMESIZEY);
        g.setColor(Color.black);
    }
}
