/*package FractalsTypes;

import java.awt.*;
import java.awt.image.*;

import Main.*;

public abstract class Mandelbrot extends Fractal
{
	private static final int THREADS_POWER = 80; //DEFAULT 80
	private static Graphics2D gd = null;
	private static int THREADS_WORKING_COUNT = 0;
	
	protected int xCount;
	protected int yCount;
	protected double width;
	protected double height;
	protected double xMin;
	protected double yMin;
	protected double xMax;
	protected double yMax;
	protected double dx;
	protected double dy;
	
	public Mandelbrot(int pictureWidth, int pictureHeight)
	{
		zoom = 1;
		xCount = pictureWidth;
		yCount = pictureHeight;
		width = 4;
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

	public void reset()
	{
		zoom = 1;
		width = 4;
		height = (width * yCount) / xCount;
		xMin = -width / 2;
		yMin = -height / 2;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
	}
	public void zoomIn(Rectangle rectangleZoom)
	{
		zoom *= rectangleZoom.getWidth() / xCount;
		xMin += (rectangleZoom.getX() / xCount) * width;
		yMin += (rectangleZoom.getY() / yCount) * height;
		width = 4 * zoom;
		height = (width * yCount) / xCount;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
	}
	public void zoomOut(Point pointRelative)
	{
		zoom *= 2;
		width = 4 * zoom;
		xMin -= width / 2;
		yMin -= height / 2;
		height = (width * yCount) / xCount;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
	}
	private class DrawerSquaresThread extends Thread
	{
		private Graphics2D gd;
		private int offsetX;
		private int offsetY;
		
		public DrawerSquaresThread(Graphics2D gd, int offsetX, int offsetY)
		{
			this.gd = gd;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}
		
		public void run()
		{
			int iterations, i, j;
			double x, y;
			Complex c;
			
			c = new Complex(0, 0);
			x = xMin + offsetX * dx;
			for (i = offsetX; i < xCount; i += THREADS_POWER)
			{
				y = yMin + offsetY * dy;
				for (j = offsetY; j < yCount; j += THREADS_POWER)
				{
					c.re = x;
					c.im = y;
					iterations = doIterations(c);
					if (iterations != maxIterations)
					{
						gd.setColor(Color.getHSBColor((float)iterations / maxIterations, 1, 1));
						gd.drawRect(i, j, 1, 1);
					}
					y += THREADS_POWER * dy;
				}
				x += THREADS_POWER * dx;
			}
			THREADS_WORKING_COUNT--;
		}
	}
	
	public void drawSquares()
	{
		int i, j, c;
		DrawerSquaresThread[] threads;
		
		gd = image.createGraphics();
		gd.setColor(Color.BLACK);
		gd.clearRect(0, 0, xCount, yCount);
		THREADS_WORKING_COUNT = THREADS_POWER * THREADS_POWER;
		threads = new DrawerSquaresThread[THREADS_WORKING_COUNT];
		c = 0;
		for (i = 0; i < THREADS_POWER; i++)
			for (j = 0; j < THREADS_POWER; j++)
			{
				threads[c++] = new DrawerSquaresThread(image.createGraphics(), i, j);
				threads[c - 1].start();
			}
		while (THREADS_WORKING_COUNT > 0)
			try { Thread.sleep(5); } catch (Exception e) {}
	}
	
	private class DrawerRectanglesThread extends Thread
	{
		private Graphics2D gd;
		private int offset;
		
		public DrawerRectanglesThread(Graphics2D gd, int offset)
		{
			this.gd = gd;
			this.offset = offset;
		}
		
		public void run()
		{
			int iterations, i, j;
			double x, y;
			Complex c;
			
			c = new Complex(0, 0);
			x = xMin + offset * dx;
			for (i = offset; i < xCount; i += THREADS_POWER)
			{
				y = yMin;
				for (j = 0; j < yCount; j++)
				{
					c.re = x;
					c.im = y;
					iterations = doIterations(c);
					if (iterations != maxIterations)
					{
						gd.setColor(Color.getHSBColor(1 - (float)iterations / maxIterations, 1, 1));
						gd.drawRect(i, j, 1, 1);
					}
					y += dy;
				}
				x += THREADS_POWER * dx;
			}
			THREADS_WORKING_COUNT--;
		}
	}
	public void drawRectangles()
	{
		int i;
		DrawerRectanglesThread[] threads;
		
		gd = image.createGraphics();
		gd.setColor(Color.BLACK);
		gd.clearRect(0, 0, xCount, yCount);
		THREADS_WORKING_COUNT = THREADS_POWER;
		threads = new DrawerRectanglesThread[THREADS_WORKING_COUNT];
		for (i = 0; i < THREADS_POWER; i++)
		{
			threads[i] = new DrawerRectanglesThread(image.createGraphics(), i);
			threads[i].start();
		}
		while (THREADS_WORKING_COUNT > 0)
			try { Thread.sleep(5); } catch (Exception e) {}
	}
	
	public void drawSequential()
	{
		int i, j, iterations;
		double x, y;
		Complex c;
		Graphics2D gd;
		
		gd = image.createGraphics();
		gd.setColor(Color.BLACK);
		gd.clearRect(0, 0, xCount, yCount);
		c = new Complex(0, 0);
		x = xMin;
		for (i = 0; i < xCount; i++)
		{
			y = yMin;
			for (j = 0; j < yCount; j++)
			{
				c.re = x;
				c.im = y;
				iterations = doIterations(c);
				if (iterations != maxIterations)
				{
					gd.setColor(Color.getHSBColor((float)iterations / maxIterations, 1, 1));
					gd.drawRect(i, j, 1, 1);
				}
				y += dy;
			}
			x += dx;
		}
	}
	public void draw()
	{
		//long measuredTime;
		
		//measuredTime = System.currentTimeMillis();
		drawRectangles();
		//measuredTime = System.currentTimeMillis() - measuredTime;
		//System.out.println(measuredTime);
	}
	
	public abstract int doIterations(Complex c);
}*/