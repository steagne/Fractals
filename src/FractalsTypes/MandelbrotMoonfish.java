package FractalsTypes;

import Main.*;

public class MandelbrotMoonfish extends Mandelbrot
{
	public MandelbrotMoonfish(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.reciprocal();
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.cos(z).add(c);
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}