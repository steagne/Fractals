package FractalsTypes;

import Main.*;

public class MandelbrotLeaf2 extends Mandelbrot
{
	public MandelbrotLeaf2(int pictureWidth, int pictureHeight)
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
			z = Complex.cos(z.multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}