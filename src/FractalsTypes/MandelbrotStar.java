package FractalsTypes;

import java.awt.image.*;

import Main.*;

public class MandelbrotStar extends Mandelbrot
{
	public MandelbrotStar(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);

		xCount = pictureWidth;
		yCount = pictureHeight;
		defaultWidth = 8;
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
		
		z = new Complex(1, 0);
		c = c.pow(2);
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.sin(z.multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}