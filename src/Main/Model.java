package Main;

import java.awt.*;
import java.awt.image.*;

import FractalsTypes.*;

public class Model
{
	private int pictureWidth;
	private int pictureHeight;
	private Fractal fractal;
	
	public Model(int pictureWidth, int pictureHeight)
	{
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;
		requestFractal(FractalType.MANDELBROT_STANDARD);
	}
	
	public void requestFractal(FractalType fractalType)
	{
		switch (fractalType)
		{
		case MANDELBROT_STANDARD:
			fractal = new MandelbrotStandard(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF:
			fractal = new MandelbrotLeaf(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_CACTUS:
			fractal = new MandelbrotCactus(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_MOONFISH:
			fractal = new MandelbrotMoonfish(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_MILL:
			fractal = new MandelbrotMill(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_ARROWS:
			fractal = new MandelbrotArrows(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_BALL:
			fractal = new MandelbrotBall(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_STAR:
			fractal = new MandelbrotStar(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF2:
			fractal = new MandelbrotLeaf2(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_DAISY:
			fractal = new MandelbrotDaisy(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_FLAKE:
			fractal = new MandelbrotFlake(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_FLAKE2:
			fractal = new MandelbrotFlake2(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF3:
			fractal = new MandelbrotLeaf3(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF4:
			fractal = new MandelbrotLeaf5(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF5:
			fractal = new MandelbrotLeaf6(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF6:
			fractal = new MandelbrotLeaf6(pictureWidth, pictureHeight);
			break;
		case MANDELBROT_LEAF7:
			fractal = new MandelbrotLeaf7(pictureWidth, pictureHeight);
			break;
		case KOCH_SNOWFLAKE:
			fractal = new KochSnowflake(pictureWidth, pictureHeight);
			break;
		default:
			break;
		}
	}
	public void generateFractal() { fractal.draw(); }
	public boolean isPowerEnabled() { return fractal instanceof MandelbrotStandard || fractal instanceof MandelbrotLeaf; }
	public void setPower(int power)
	{
		if (fractal instanceof MandelbrotStandard)
			((MandelbrotStandard)fractal).setPower(power);
		else if (fractal instanceof MandelbrotLeaf)
			((MandelbrotLeaf)fractal).setPower(power);
	}
	public void setGridVisible(boolean visible)
	{
		fractal.setGridVisible(visible);
		fractal.finalize();
	}
	public void resetValues() { fractal.reset(); }
	public void setIterations(int maxIterations) { fractal.setIterations(maxIterations); }
	public void zoomIn(Rectangle rectangleZoom) { fractal.zoomIn(rectangleZoom); }
	public void zoomOut(Point pointRelative) { fractal.zoomOut(pointRelative); }
	public BufferedImage getFractalImage() { return fractal.getImage(); }
}