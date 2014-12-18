
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

public class ConstantParameter extends Parameter implements Cloneable
{
	static final int nParameters = 1;
	private double n = 1;

	public String type()
	{
		return "Constant";
	}

    public ConstantParameter()
    {
		super();
    }
	
	public ConstantParameter( double n )
	{
		super();
		this.n = n;
	}

	public ConstantParameter( ConstantParameter p )
	{
		super();
		this.n = p.n;
	}

	public ConstantParameter( double[] n ) throws WrongArraySizeException
	{
		super();
		SetArray( n );
	}

	public double IndexAtWavelength( double w )
	{
		return n;
	}

	public double[] AsArray()
	{
		double[] a = { n };
		return a;
	}

	public void SetArray( double[] n ) throws WrongArraySizeException
	{
		if( n.length != nParameters ) throw new WrongArraySizeException( type(), n.length, nParameters );
		this.n = n[0];
	}

	public double N()
	{
		return n;
	}

	public void SetN( double n )
	{
		this.n = n;
	}
}