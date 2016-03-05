package rs.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.border.Border;

public class RoundBorder implements Border{

	private int radius;
	
	public RoundBorder()
	{
		//this.radius = radius;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		Rectangle rec = c.getBounds();
		int currWidth = (int)rec.getWidth();
		int currHeight = (int)rec.getHeight();
		g.drawRoundRect(0, 0, currWidth-1, currHeight-1, currWidth, currWidth);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		// TODO Auto-generated method stub
		Rectangle rec = c.getBounds();
		int currWidth = (int)rec.getWidth();
		int currHeight = (int)rec.getHeight();
		return new Insets(currHeight,currWidth,currHeight,currWidth);
	}

	@Override
	public boolean isBorderOpaque() {
		// TODO Auto-generated method stub
		return true;
	}

}
