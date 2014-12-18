
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

public class ConradyParameters extends Parameter implements Cloneable
{
	static final int nParameters = 3;
	private double	n0 = 1,
					a = 0,
					b = 0;

	public String type()
	{
		return "Conrady";
	}

    public ConradyParameters()
    {
		super();
    }

	public ConradyParameters( double n0, double a, double b )
	{
		super();
		this.n0 = n0;
		this.a = a;
		this.b = b;
	}

	public ConradyParameters( ConradyParameters p )
	{
		super();
		this.n0 = p.n0;
		this.a = p.a;
		this.b = p.b;
	}

	public ConradyParameters( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		return n0 + a/w + b/Math.pow( w, 3.5 );
	}

	public double[] AsArray()
	{
		double[] arr = { n0, a, b };
		return arr;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException( type(), n.length, nParameters );
		this.n0 = n[0];
		this.a = n[1];
		this.b = n[2];
	}

	public double N0()
	{
		return n0;
	}

	public double A()
	{
		return a;
	}

	public double B()
	{
		return b;
	}

	public void SetN0( double n )
	{
		this.n0 = n;
	}

	public void SetA( double n )
	{
		this.a = n;
	}

	public void SetB( double n )
	{
		this.b = n;
	}
}