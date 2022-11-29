package Main;

import java.awt.*;
import java.awt.image.*;

public abstract class Fractal
{
	public static final int POWER_DEFAULT = 2;
	protected static int THREADS_WORKING_COUNT = 0;
	
	protected BufferedImage image;
	protected double zoom;
	protected int maxIterations;
	protected boolean grid;
	
	protected double defaultWidth;
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

	public BufferedImage getImage() { return image; }
	public void setIterations(int maxIterations) { this.maxIterations = maxIterations; }
	public void setGridVisible(boolean visible) { grid = visible; }

	public abstract void zoomIn(Rectangle rectangleZoom);
	public abstract void zoomOut(Point pointRelative);
	public abstract void draw();
	public void finalize()
	{
		if (grid)
		{
			Graphics2D gd;
			gd = image.createGraphics();
			gd.drawLine(image.getWidth() / 2, 0, image.getWidth() / 2, image.getHeight());
			gd.drawLine(0, image.getHeight() / 2, image.getWidth(), image.getHeight() / 2);
		}
	}
	public abstract void reset();
}