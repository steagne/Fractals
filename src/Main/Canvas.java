package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class Canvas extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private static boolean FINISHED_DRAWING_PROCESS = true;
	
	private BufferedImage picture;
	
	//INTERACTION
	private boolean pressed;
	private Point pointPressed;
	private Rectangle rectangleZoom;
	
	public Canvas()
	{
		super();

		rectangleZoom = new Rectangle();
		addMouseListener(this);
	}
	
	public BufferedImage getPicture() { return picture; }
	public Rectangle getRectangleZoom() { return rectangleZoom; }
	public void setPicture(BufferedImage picture)
	{
		this.picture = picture;
		if (picture != null)
			setBounds(getX(), getY(), picture.getWidth(), picture.getHeight());
		repaint();
	}

    public void addActionListener(ActionListener listener) { listenerList.add(ActionListener.class, listener); }
    public void removeActionListener(ActionListener listener) { listenerList.remove(ActionListener.class, listener); }
	private void fireActionPerformed()
	{
        ActionListener[] listeners;
        
        listeners = listenerList.getListeners(ActionListener.class);
        if (listeners != null)
			if (listeners.length > 0)
				listeners[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
    }
	private void fireActionPerformed(int input)
	{
        ActionListener[] listeners;
        
        listeners = listenerList.getListeners(ActionListener.class);
        if (listeners != null)
			if (listeners.length > 0)
				listeners[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "" + input));
    }
	
	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		
		if (picture != null)
			picture.getGraphics().drawImage(picture.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
	}
	@Override
	public void paintComponent(Graphics gd)
	{
		super.paintComponent(gd);
		
		if (picture != null)
		{
			gd.drawImage(picture, 0, 0, getWidth(), getHeight(), this);
		}
		if (pressed)
		{
			gd.setColor(Color.WHITE);
			gd.drawRect(rectangleZoom.x, rectangleZoom.y, rectangleZoom.width, rectangleZoom.height);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			pointPressed = e.getPoint();
			fireActionPerformed(2);
			return;
		}
		pressed = true;
		new Thread()
		{
			public void run()
			{
				Point pointCurrentMouse;
				double theta;

				FINISHED_DRAWING_PROCESS = false;
				pointPressed = e.getPoint();
				theta = Math.atan((double)View.FRACTAL_H / View.FRACTAL_W);
				while (pressed)
				{
					pointCurrentMouse = getMousePosition();
					if (pointCurrentMouse != null)
					{
						rectangleZoom.width = Math.abs(pointCurrentMouse.x - pointPressed.x);
						rectangleZoom.height = (int)(Math.abs(pointCurrentMouse.x - pointPressed.x) * Math.tan(theta));
						rectangleZoom.x = pointPressed.x < pointCurrentMouse.x ? pointPressed.x : pointPressed.x - rectangleZoom.width;
						rectangleZoom.y = pointPressed.y < pointCurrentMouse.y ? pointPressed.y : pointPressed.y - rectangleZoom.height;
						
						repaint();
					}
				}
				FINISHED_DRAWING_PROCESS = true;
			}
		}.start();
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		Point pointReleased, temp;
		
		pressed = false;
		pointReleased = e.getPoint();
		if (!pointPressed.equals(pointReleased))
		{
			if (pointPressed.x > pointReleased.x)
			{
				temp = pointPressed;
				pointPressed = pointReleased;
				pointReleased = temp;
			}
			while (!FINISHED_DRAWING_PROCESS) { try { Thread.sleep(5); } catch (Exception exception) {} }
			rectangleZoom.width = Math.abs(pointReleased.x - pointPressed.x);
			rectangleZoom.height = (int)(Math.abs(pointReleased.x - pointPressed.x) * Math.tan((double)View.FRACTAL_H / View.FRACTAL_W));
			rectangleZoom.x = pointPressed.x < pointReleased.x ? pointPressed.x : pointPressed.x - rectangleZoom.width;
			rectangleZoom.y = pointPressed.y < pointReleased.y ? pointPressed.y : pointPressed.y - rectangleZoom.height;
			fireActionPerformed(1);
		}
	}
}