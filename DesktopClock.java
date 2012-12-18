import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.UIManager;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DesktopClock extends JFrame 
{
    private JLabel time = new JLabel();
    private TrayIcon trayIcon;
    private SystemTray tray;

    private DesktopClock()
    {    
        //window properties
        super("TestClock");
        setSize(100, 50);
        setUndecorated(true);
        Rectangle bounds = getGraphicsConfiguration().getBounds();
        Dimension size = getPreferredSize();
        setLocation
        (
            (int)(bounds.width/2 - size.getWidth()/2), 
            (int)(bounds.height/2 - size.getHeight()/2)
        );
        com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5f);
        addWindowFocusListener(new WindowAdapter()
        {
            public void windowGainedFocus(WindowEvent e)
            {
                //setUndecorated(false);
                //toBack();
                System.out.println("Gained focus");
            }
            public void windowLostFocus(WindowEvent e)
            {
                //setUndecorated(true);
                //toBack();
                System.out.println("Lost focus");
            }
        });
        
        
        //system tray properties
        Image image = Toolkit.getDefaultToolkit().getImage("./icon.jpg");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
        
        PopupMenu popup = new PopupMenu();
        
        MenuItem menuItemExit = new MenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Exiting....");
                System.exit(0);
            }
        });
        popup.add(menuItemExit);
        
        MenuItem menuItemOpen = new MenuItem("Open");
        menuItemOpen.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(true);
                setExtendedState(JFrame.NORMAL);
            }
        });
        popup.add(menuItemOpen);
        
        tray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(image, "DesktopClock", popup);
        trayIcon.setImageAutoSize(true);
        addWindowStateListener(new WindowStateListener() 
        {
            public void windowStateChanged(WindowEvent e) 
            {
                if(e.getNewState()==ICONIFIED)
                {
                    try 
                    {
                        tray.add(trayIcon);
                        setVisible(false);
                        System.out.println("added to SystemTray");
                    } 
                    catch (AWTException ex) 
                    {
                        System.out.println("unable to add to tray");
                    }
                }
                if(e.getNewState()==7)
                {
                    try
                    {
                        tray.add(trayIcon);
                        setVisible(false);
                        System.out.println("added to SystemTray");
                    }
                    catch(AWTException ex)
                    {
                        System.out.println("unable to add to system tray");
                    }
                }
                if(e.getNewState()==NORMAL)
                {
                    tray.remove(trayIcon);
                    setVisible(true);
                    System.out.println("Tray icon removed");
                }
            }
        });
        
        
        //content
        add(time);
        Timer timer = new Timer(1000, new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                time.setText(sdf.format(calendar.getTime()));
            }
        });
        timer.start();
    }
    
    public static void main(String[] args) 
    {
        new DesktopClock().setVisible(true);
    }
}

