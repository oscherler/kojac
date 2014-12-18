
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

public class Sellmeier2Parameters extends Parameter
{
	static final int nParameters = 5;
	private double	a = 0,
					b1 = 0,
					lambda1 = 0,
					b2 = 0,
					lambda2 = 0;

	/*public int type()
	{
		return Parameter.Sellmeier2;
	}*/

	public String type()
	{
		return "Sellmeier2";
	}
    public Sellmeier2Parameters()
    {
		super();
    }

	public Sellmeier2Parameters( double a, double b1, double lambda1, double b2, double lambda2 )
	{
		super();
		this.a = a;
		this.b1 = b1;
		this.lambda1 = lambda1;
		this.b2 = b2;
		this.lambda2 = lambda2;
	}

	public Sellmeier2Parameters( Sellmeier2Parameters p )
	{
		super();
		this.a = p.a;
		this.b1 = p.b1;
		this.lambda1 = p.lambda1;
		this.b2 = p.b2;
		this.lambda2 = p.lambda2;
	}

	public Sellmeier2Parameters( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		double w2 = w * w;
		return Math.sqrt( 1 + a +
			w2 * b1/(w2 - Math.pow(lambda1,2)) +
			b2/(w2 - Math.pow(lambda2,2) ) );
	}

	public double[] AsArray()
	{
		double[] arr = { a, b1, lambda1, b2, lambda2 };
		return arr;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException();
		this.a = n[0];
		this.b1 = n[1];
		this.lambda1 = n[2];
		this.b2 = n[3];
		this.lambda2 = n[4];
	}

	public double A()
	{
		return a;
	}

	public double B1()
	{
		return b1;
	}

	public double Lambda1()
	{
		return lambda1;
	}

	public double B2()
	{
		return b2;
	}

	public double Lambda2()
	{
		return lambda2;
	}

	public void SetA( double n )
	{
		this.a = n;
	}

	public void SetB1( double n )
	{
		this.b1 = n;
	}

	public void SetLambda1( double n )
	{
		this.lambda1 = n;
	}

	public void SetB2( double n )
	{
		this.b2 = n;
	}

	public void SetLambda2( double n )
	{
		this.lambda2 = n;
	}
}