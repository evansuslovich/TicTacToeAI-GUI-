import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
	private static final long serialVersionUID = 1L;

	static int a = 0;
	ImageIcon X,O; 
	boolean hasX = false; 
	boolean hasO = false; 

	
	public XOButton(){
		X = new ImageIcon(this.getClass().getResource("X.png"));
		O = new ImageIcon(this.getClass().getResource("O.png"));
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		a++;
	}

	public void addA()
	{
		a++; 
	}

	public void setX()
    {    
		setIcon(X);
		hasX = true; 
		
    }
    public void setO()
    {
		setIcon(O); 
		hasO = true; 
	}
	
	public boolean hasX()
	{
		return hasX; 
	}

	public boolean hasO()
	{
		return hasO; 
	}

	

	

}