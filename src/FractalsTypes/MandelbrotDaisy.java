package FractalsTypes;

import java.awt.image.*;

import Main.*;

public class MandelbrotDaisy extends Mandelbrot
{
	public MandelbrotDaisy(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);

		xCount = pictureWidth;
		yCount = pictureHeight;
		defaultWidth = 6;
		width = defaultWidth;
		height = (width * pictureHeight) / pictureWidth;
		xMin = -width / 2;
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
		xMin = -width / 2;
		yMin = -height / 2;
		xMax = xMin + width;
		yMax = yMin + height;
		dx = (xMax - xMin) / xCount;
		dy = (yMax - yMin) / yCount;
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(3);
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.cos(z.multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}