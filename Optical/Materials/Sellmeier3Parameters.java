
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

public class Sellmeier3Parameters extends Parameter
{
	static final int nParameters = 8;
	private double	k1 = 0,
					l1 = 0,
					k2 = 0,
					l2 = 0,
					k3 = 0,
					l3 = 0,
					k4 = 0,
					l4 = 0;

	/*public int type()
	{
		return Parameter.Sellmeier3;
	}*/

	public String type()
	{
		return "Sellmeier3";
	}
    public Sellmeier3Parameters()
    {
		super();
    }

	public Sellmeier3Parameters( double k1, double l1, double k2, double l2,
		double k3, double l3, double k4, double l4 )
	{
		super();
		this.k1 = k1;
		this.l1 = l1;
		this.k2 = k2;
		this.l2 = l2;
		this.k3 = k3;
		this.l3 = l3;
		this.k4 = k4;
		this.l4 = l4;
	}

	public Sellmeier3Parameters( Sellmeier3Parameters p )
	{
		super();
		this.k1 = p.k1;
		this.l1 = p.l1;
		this.k2 = p.k2;
		this.l2 = p.l2;
		this.k3 = p.k3;
		this.l3 = p.l3;
		this.k4 = p.k4;
		this.l4 = p.l4;
	}

	public Sellmeier3Parameters( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		double w2 = w * w;
		return Math.sqrt(1 + w2 * (k1/(w2 - l1) +
								   k2/(w2 - l2) +
								   k3/(w2 - l3) +
								   k4/(w2 - l4) ));
	}

	public double[] AsArray()
	{
		double[] a = { k1, l1, k2, l2, k3, l3, k4, l4 };
		return a;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException();
		this.k1 = n[0];
		this.l1 = n[1];
		this.k2 = n[2];
		this.l2 = n[3];
		this.k3 = n[4];
		this.l3 = n[5];
		this.k4 = n[6];
		this.l4 = n[7];
	}

	public double K1()
	{
		return k1;
	}

	public double L1()
	{
		return l1;
	}

	public double K2()
	{
		return k2;
	}

	public double L2()
	{
		return l2;
	}

	public double K3()
	{
		return k3;
	}

	public double L3()
	{
		return l3;
	}

	public double K4()
	{
		return k4;
	}

	public double L4()
	{
		return l4;
	}

	public void SetK1( double n )
	{
		this.k1 = n;
	}

	public void SetL1( double n )
	{
		this.l1 = n;
	}

	public void SetK2( double n )
	{
		this.k2 = n;
	}

	public void SetL2( double n )
	{
		this.l2 = n;
	}

	public void SetK3( double n )
	{
		this.k3 = n;
	}

	public void SetL3( double n )
	{
		this.l3 = n;
	}

	public void SetK4( double n )
	{
		this.k4 = n;
	}

	public void SetL4( double n )
	{
		this.l4 = n;
	}
}