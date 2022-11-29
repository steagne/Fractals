package FractalsTypes;

import Main.*;

public class MandelbrotFlake2 extends Mandelbrot
{
	public MandelbrotFlake2(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(-4);
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.exp(z.multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}