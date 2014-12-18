
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

public class SchottParameters extends Parameter
{
	static final int nParameters = 6;
	private double	a0 = 1,
					a1 = 0,
					a2 = 0,
					a3 = 0,
					a4 = 0,
					a5 = 0;

	public String type()
	{
		return "Schott";
	}

    public SchottParameters()
    {
		super();
    }

	public SchottParameters( double a0, double a1, double a2, double a3, double a4, double a5 )
	{
		super();
		this.a0 = a0;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a5 = a5;
	}

	/*public SchottParameters( SchottParameters p )
	{
		super();
		this.a0 = p.a0;
		this.a1 = p.a1;
		this.a2 = p.a2;
		this.a3 = p.a3;
		this.a4 = p.a4;
		this.a5 = p.a5;
	}*/

	public SchottParameters( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		return Math.sqrt( a0 + a1 * w * w +
			a2 * Math.pow( w, -2 ) + a3 * Math.pow( w, -4 ) +
			a4 * Math.pow( w, -6 ) + a5 * Math.pow( w, -8 ) );
	}

	public double[] AsArray()
	{
		double[] a = { a0, a1, a2, a3, a4, a5 };
		return a;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException();
		this.a0 = n[0];
		this.a1 = n[1];
		this.a2 = n[2];
		this.a3 = n[3];
		this.a4 = n[4];
		this.a5 = n[5];
	}

	public double A0()
	{
		return a0;
	}

	public double A1()
	{
		return a1;
	}

	public double A2()
	{
		return a2;
	}

	public double A3()
	{
		return a3;
	}

	public double A4()
	{
		return a4;
	}

	public double A5()
	{
		return a5;
	}

	public void SetA0( double n )
	{
		this.a0 = n;
	}

	public void SetA1( double n )
	{
		this.a1 = n;
	}

	public void SetA2( double n )
	{
		this.a2 = n;
	}

	public void SetA3( double n )
	{
		this.a3 = n;
	}

	public void SetA4( double n )
	{
		this.a4 = n;
	}

	public void SetA5( double n )
	{
		this.a5 = n;
	}
}