import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DesktopClock extends JFrame 
{
    private JLabel time = new JLabel();

    private DesktopClock()
    {
        //window properties
        setBounds(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));  
        setUndecorated(true);
        setFocusable(false);
        setFocusableWindowState(false);
        com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.8f);
        setTitle("TestClock");
        
        //content
        add(time);
        Timer t = new Timer(1000, new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                time.setText(sdf.format(calendar.getTime()));
            }
        });
        t.start();
    }
    
    public static void main(String[] args) 
    {
        DesktopClock desktopClock = new DesktopClock();
        desktopClock.setVisible(true);
        desktopClock.toBack();
    }
}

