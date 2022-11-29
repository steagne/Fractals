package FractalsTypes;

import Main.*;

public class MandelbrotBall extends Mandelbrot
{
	public MandelbrotBall(int pictureWidth, int pictureHeight)
	{
		super(pictureWidth, pictureHeight);
	}

	@Override
	public int doIterations(Complex c)
	{
		int n;
		Complex z;
		
		z = new Complex(0, 0);
		c = c.pow(-3);
		for (n = 0; n < maxIterations; n++)
		{
			z = Complex.exp(Complex.add(z.pow(2), z.scale(-1.00001)).multiply(c));
			if (z.mod() > maxIterations)
				return n;
		}
		
		return n;
	}
}