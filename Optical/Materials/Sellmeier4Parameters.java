
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Materials;

import Utils.*;

public class Sellmeier4Parameters extends Parameter
{
	static final int nParameters = 5;
	private double	a = 1,
					b = 0,
					c = 0,
					d = 0,
					e = 0;

	/*public int type()
	{
		return Parameter.Sellmeier4;
	}*/

 	public String type()
	{
		return "Sellmeier4";
	}

    public Sellmeier4Parameters()
    {
		super();
    }

	public Sellmeier4Parameters( double a, double b, double c, double d, double e )
	{
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}

	public Sellmeier4Parameters( Sellmeier4Parameters p )
	{
		super();
		this.a = p.a;
		this.b = p.b;
		this.c = p.c;
		this.d = p.d;
		this.e = p.e;
	}

	public Sellmeier4Parameters( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		double w2 = w * w;
		return Math.sqrt( a + w2*(b/(w2 - c) + d/(w2 - e) ) );
	}

	public double[] AsArray()
	{
		double[] arr = { a, b, c, d, e };
		return arr;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException();
		this.a = n[0];
		this.b = n[1];
		this.c = n[2];
		this.d = n[3];
		this.e = n[4];
	}

	public double A()
	{
		return a;
	}

	public double B()
	{
		return b;
	}

	public double C()
	{
		return c;
	}

	public double D()
	{
		return d;
	}

	public double E()
	{
		return e;
	}

	public void SetA( double n )
	{
		this.a = n;
	}

	public void SetB( double n )
	{
		this.b = n;
	}

	public void SetC( double n )
	{
		this.c = n;
	}

	public void SetD( double n )
	{
		this.d = n;
	}

	public void SetE( double n )
	{
		this.e = n;
	}
}