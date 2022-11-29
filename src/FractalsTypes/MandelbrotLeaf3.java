package FractalsTypes;

import java.awt.image.*;

import Main.*;

public class MandelbrotLeaf3 extends Mandelbrot
{
	public MandelbrotLeaf3(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);

		xCount = pictureWidth;
		yCount = pictureHeight;
		defaultWidth = 2;
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
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.add(Complex.add(z.pow(2), c.pow(2).divide(z.pow(2).add(c))), c);
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}