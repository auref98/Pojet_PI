import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class Principale extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Principale frame = new Principale();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principale()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int heightScreen = (int)dimension.getHeight();
		int widthScreen  = (int)dimension.getWidth();
		
		int width = (int)(widthScreen * 0.35);
		int height = (int) (heightScreen * 0.95);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Placer Bateaux");
		this.setSize(50, 50);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
	}

}
