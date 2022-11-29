package Main;
public class Complex
{
	public double re;
	public double im;
	
	public Complex(double re, double im)
	{
		this.re = re;
		this.im = im;
	}
	
	public double mod() { return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2)); }
	
	public Complex reciprocal()
	{
		return new Complex(re / Math.pow(mod(), 2), -im / Math.pow(mod(), 2));
	}
	public Complex add(Complex c)
	{
		return new Complex(re + c.re, im + c.im);
	}
	public Complex subtract(Complex c)
	{
		return new Complex(re - c.re, im - c.im);
	}
	public Complex scale(double la)
	{
		return new Complex(la * re, la * im);
	}
	public Complex multiply(Complex c)
	{
		return new Complex(re * c.re - im * c.im,
				re * c.im + im * c.re);
	}
	public Complex divide(Complex c)
	{
		double _re, _im, div;

		div = Math.pow(c.mod(), 2);
		_re = (re * c.re + im * c.im) / div;
		_im = (im * c.re - re * c.im) / div;
		
		return new Complex(_re, _im);
	}
	public Complex pow(double exp)
	{
		if (re == 0 && im == 0)
			return this;
		
		double rh, th;
		
		rh = mod();
		th = Math.atan(im / re) + (re < 0 ? Math.toRadians(180) : 0);
		rh = Math.pow(rh, exp);
		th *= exp;
		
		return new Complex(rh * Math.cos(th), rh * Math.sin(th));
	}
	public Complex sqr(double exp)
	{
		if (re == 0 && im == 0)
			return this;
		
		double rh, th;
		
		rh = mod();
		th = Math.atan(im / re);
		rh = Math.pow(rh, (double)1 / exp);
		th /= exp;
		
		return new Complex(rh * Math.cos(th), rh * Math.sin(th));
	}
	public static Complex add(Complex z, Complex c)
	{
		return new Complex(z.re + c.re, z.im + c.im);
	}
	public static Complex subtract(Complex z, Complex c)
	{
		return new Complex(z.re - c.re, z.im - c.im);
	}
	public static Complex scale(Complex z, double la)
	{
		return new Complex(la * z.re, la * z.im);
	}
	public static Complex multiply(Complex z, Complex c)
	{
		return new Complex(z.re * c.re - z.im * c.im,
				z.re * c.im + z.im * c.re);
	}
	public static Complex divide(Complex z, Complex c)
	{
		double re, im, div;

		div = Math.pow(c.mod(), 2);
		re = (z.re * c.re + z.im * c.im) / div;
		im = (z.im * c.re - z.re * c.im) / div;
		
		return new Complex(re, im);
	}
	public static Complex pow(Complex z, double exp)
	{
		if (z.re == 0 && z.im == 0)
			return z;
		
		double rh, th;
		
		rh = z.mod();
		th = Math.atan(z.im / z.re) + (z.re < 0 ? Math.toRadians(180) : 0);
		rh = Math.pow(rh, exp);
		th *= exp;
		
		return new Complex(rh * Math.cos(th), rh * Math.sin(th));
	}
	public static Complex sqr(Complex z, double exp)
	{
		if (z.re == 0 && z.im == 0)
			return z;
		
		double rh, th;
		
		rh = z.mod();
		th = Math.atan(z.im / z.re);
		rh = Math.pow(rh, (double)1 / exp);
		th /= exp;
		
		return new Complex(rh * Math.cos(th), rh * Math.sin(th));
	}
	public static Complex exp(Complex z)
	{
		return new Complex(Math.exp(z.re) * Math.cos(z.im), Math.exp(z.re) * Math.sin(z.im));
	}
	public static Complex sin(Complex z)
	{
		double re, im;

		re = 0.5 * Math.sin(z.re) * (Math.exp(z.im) + Math.exp(-z.im));
		im = 0.5 * Math.cos(z.re) * (Math.exp(z.im) - Math.exp(-z.im));
		
		return new Complex(re, im);
	}
	public static Complex cos(Complex z)
	{
		double re, im;

		re = 0.5 * Math.cos(z.re) * (Math.exp(z.im) + Math.exp(-z.im));
		im = 0.5 * Math.sin(z.re) * (Math.exp(-z.im) - Math.exp(z.im));
		
		return new Complex(re, im);
	}
	public static Complex sinh(Complex z)
	{
		double re, im;

		re = 0.5 * Math.cos(z.im) * (Math.exp(z.re) - Math.exp(-z.re));
		im = 0.5 * Math.sin(z.im) * (Math.exp(z.re) + Math.exp(-z.re));
		
		return new Complex(re, im);
	}
	public static Complex cosh(Complex z)
	{
		double re, im;

		re = 0.5 * Math.cos(z.im) * (Math.exp(z.re) + Math.exp(-z.re));
		im = 0.5 * Math.sin(z.im) * (Math.exp(z.re) - Math.exp(-z.re));
		
		return new Complex(re, im);
	}
	
	public String toString()
	{
		return "Re: " + re + "; Im: " + im;
	}
}