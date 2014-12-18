package Utils;

//import Optics.*;

// Class FPoint
public class FPoint
{
	public double x, y, z;

	public FPoint()
	{
		this( 0, 0, 0 );
	}

	public FPoint( double px, double py, double pz )
	{
		x = px;
		y = py;
		z = pz;
	}
	
	public FPoint( FPoint p )
	{
		this( p.x, p.y, p.z );
	}

	public double Norm()
	{
		return Math.sqrt( x * x + y * y + z * z );
	}
	
	public void Normalize()
	{
		Normalize( 1 );
	}
	
	public void Normalize( double n )
	{
		double norm = Norm();
		
		x = x * n / norm;
		y = y * n / norm;
		z = z * n / norm;
	}
	
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public void setX( double d )
	{
		x = d;
	}

	public void setY( double d )
	{
		y = d;
	}

	public void setZ( double d )
	{
		z = d;
	}
}
	
