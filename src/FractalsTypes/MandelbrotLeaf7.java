package FractalsTypes;

import Main.*;

public class MandelbrotLeaf7 extends Mandelbrot
{
	public MandelbrotLeaf7(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(5).add(c).reciprocal();
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.exp(z.pow(2).multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}