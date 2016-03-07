package rs.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.Border;

public class RoundBorder implements Border{

	public RoundBorder()
	{
		//this.radius = radius;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		Graphics2D g2 = (Graphics2D) g;
		
		RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(0, 0, width, height, width, height);
		Area area = new Area(roundRect);
		
        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
        }
        g2.setStroke(new BasicStroke(2));
        g2.draw(area);
		//g.drawRoundRect(0, 0, width-1, height-1, width, height);
		
		
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
		return false;
	}

}
