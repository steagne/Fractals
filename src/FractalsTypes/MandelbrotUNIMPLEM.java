package FractalsTypes;

import java.awt.image.*;

import Main.*;

//NON VAAAAAAA
public class MandelbrotUNIMPLEM extends Mandelbrot
{
	public MandelbrotUNIMPLEM(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);

		xCount = pictureWidth;
		yCount = pictureHeight;
		width = 6;
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
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(3);
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.exp(c.divide(z.pow(3)));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}