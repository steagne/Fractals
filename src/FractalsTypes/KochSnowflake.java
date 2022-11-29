package FractalsTypes;

import java.awt.*;
import java.awt.image.*;

import Main.*;

public class KochSnowflake extends Fractal
{
	public static final int MAX_ITERATIONS_DEFAULT = 10;
	public static final double DEFAULT_LENGTH_FACTOR = 1.25;
	
	public KochSnowflake(int pictureWidth, int pictureHeight)
	{
		defaultWidth = pictureWidth;
		zoom = 1;
		xCount = pictureWidth;
		yCount = pictureHeight;
		width = defaultWidth;
		height = (width * yCount) / xCount;
		xMin = -width / 2;
		yMin = -height / 2;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
		this.maxIterations = MAX_ITERATIONS_DEFAULT;
		image = new BufferedImage(xCount, yCount, BufferedImage.TYPE_INT_RGB);
	}
	
	class DrawingThread extends Thread
	{
		private double x;
		private double y;
		private double r;
		private double th;
		private Graphics2D gd;
		
		public DrawingThread(double x, double y, double r, double th, Graphics2D gd)
		{
			this.x = x;
			this.y = y;
			this.r = r;
			this.th = th;
			this.gd = gd;
			gd.setColor(Color.WHITE);
		}
		
		public void run()
		{
			draw(x, y, r, th, maxIterations, gd);
			THREADS_WORKING_COUNT--;
		}
		
		private void draw(double x, double y, double r, double th, int level, Graphics2D gd)
		{
			if (level > 0)
			{
				double next_th, next_r;
				
				next_th = th + Math.toRadians(60);
				next_r = r / 3;
				draw(x, y, next_r, th, level - 1, gd);
				draw(x + next_r * Math.cos(th),
					 y - next_r * Math.sin(th),
					 next_r, next_th, level - 1, gd);
				draw(x + next_r * Math.cos(th) + next_r * Math.cos(next_th),
					 y - next_r * Math.sin(th) - next_r * Math.sin(next_th),
					 next_r, th + Math.toRadians(300), level - 1, gd);
				draw(x + 2 * next_r * Math.cos(th), y - 2 * next_r * Math.sin(th), next_r, th, level - 1, gd);
			}
			else
				gd.drawLine((int)x, (int)y, (int)(x + r * Math.cos(th)), (int)(y - r * Math.sin(th)));
		}
	}
	
	@Override
	public void zoomIn(Rectangle rectangleZoom)
	{	
	}
	@Override
	public void zoomOut(Point pointRelative)
	{	
	}
	@Override
	public void draw()
	{
		Graphics2D gd;
		double len, h, offsetY;
		
		len = (double)yCount / DEFAULT_LENGTH_FACTOR;
		h = Math.cos(Math.toRadians(30)) * len;
		offsetY = 60;
		THREADS_WORKING_COUNT = 3;
		gd = image.createGraphics();
		gd.setColor(Color.BLACK);
		gd.fillRect(0, 0, image.getWidth(), image.getHeight());
		new DrawingThread((xCount - len) / 2, offsetY + (yCount - h) / 2, len, 0, gd).start();
		new DrawingThread((xCount - len) / 2 + len, offsetY + (yCount - h) / 2, len, -Math.toRadians(120), gd).start();
		new DrawingThread((xCount - len) / 2 + len / 2, offsetY + (yCount - h) / 2 + h, len, Math.toRadians(120), gd).start();
		while (THREADS_WORKING_COUNT > 0)
			try { Thread.sleep(5); } catch (Exception e) {}
	}
	public void setIterations(int maxIterations)
	{
		if (maxIterations > 10)
			this.maxIterations = 10;
		else
			this.maxIterations = maxIterations;
	}
	public void reset() {}
}