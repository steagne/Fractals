package FractalsTypes;

import Main.*;

public class MandelbrotLeaf6 extends Mandelbrot
{
	public MandelbrotLeaf6(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 1);
		c = c.reciprocal();
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.sinh(z.multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}