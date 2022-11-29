package FractalsTypes;

import java.awt.image.*;

import Main.*;

public class MandelbrotLeaf extends Mandelbrot
{
	protected int power;
	
	public MandelbrotLeaf(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);

		this.power = POWER_DEFAULT;
		xCount = pictureWidth;
		yCount = pictureHeight;
		defaultWidth = 6;
		width = defaultWidth;
		height = (width * pictureHeight) / pictureWidth;
		xMin = -width / 2 + (power == 2 ? 1 : 0);
		yMin = -height / 2;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / pictureWidth;
		dy = (yMax - yMin) / pictureHeight;
		this.maxIterations = MAX_ITERATIONS_DEFAULT;
		image = new BufferedImage(pictureWidth, pictureHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void reset()
	{
		zoom = 1;
		width = defaultWidth;
		height = (width * yCount) / xCount;
		xMin = -width / 2 + (power == 2 ? 1 : 0);
		yMin = -height / 2;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
	}

	public int getPower() { return power; }
	public void setPower(int power) { this.power = power; }
	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.reciprocal();
		for (n = 0; n < maxIterations; n++)
		{
			z = z.pow(power).add(c);
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}