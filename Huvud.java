import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

class Huvud
{
    public Huvud()
    {
        play();
    }

    public void play()
    {
        Simulation frame;
        frame = new Simulation();
        initiera(frame);
        frame.run();
        destroy(frame);
    }

    public void initiera(JFrame frame)
    {
        frame.setTitle("Rörelsemängd");
        frame.addWindowListener(new WindowAdapter()
                                    {
                                        public void windowClosing(WindowEvent event)
                                        {
                                            System.exit(0);
                                        }
                                    });
        frame.setSize(Consts.FRAMESIZEX,Consts.FRAMESIZEY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public void destroy(JFrame frame)
    {
        frame.setVisible(false);
        frame.dispose();
    }
}
