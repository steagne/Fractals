package FractalsTypes;

import Main.*;

public class MandelbrotCactus extends Mandelbrot
{
	public MandelbrotCactus(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(6);
		for (n = 0; n < maxIterations; n++)
		{
			z.re -= 1;
			z = z.pow(2).add(c);
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}