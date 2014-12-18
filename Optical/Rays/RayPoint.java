
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Rays;

import Utils.*;

public class RayPoint implements Cloneable
{
	// Point de départ du raypoint.
	private double x, y, z;
	// Vecteur d'onde.
	private double kx, ky, kz;
	// Longueur d'onde.
	private double wavelength;
	// Polarisation.
	private double pol_x, pol_y, pol_z;
	// Amplitude, phase.
	private double amplitude, phase;
	// Le raypoint existe-t-il ?
	private boolean valid;

    public RayPoint()
    {
		x = y = z = 0;
		kx = ky = kz = 0;
		wavelength = 0;
		pol_x = pol_y = pol_z = 0;
		amplitude = phase = 0;
		valid = true;
    }

	public RayPoint( FPoint pos, FPoint k, double w )
	{
		this();
		FPoint k2 = new FPoint( k );

		SetPosition( pos );
		wavelength = w;
		k2.Normalize( ( 2 * Math.PI ) / w );
		SetKVector( k2 );
		valid = true;
	}

	public RayPoint( FPoint pos, FPoint k, double w, double d )
	{
		this();
		FPoint k2 = new FPoint( k );

		SetPosition( pos );
		wavelength = w;
		k2.Normalize( ( 2 * Math.PI * d ) / w );
		SetKVector( k2 );
		valid = true;
	}

	/*public RayPoint( RayPoint r )
	{
		SetPosition( new FPoint( r.GetPosition() ) );
		SetKVector( new FPoint( r.GetKVector() ) );
		wavelength = r.GetWavelength();
		SetPolarisation( new FPoint( r.GetPolarisation() ) );
		amplitude = r.GetAmplitude();
		phase = r.GetPhase();
		valid = r.IsValid();
	}*/

	public Object clone()
	{
		Object o = null;

		try
		{
			o = super.clone();
		}
		catch( CloneNotSupportedException e )
		{

		}
		return o;
	}
	
	public void GoStraight( double x_distance )
	{
		double mult = x_distance / kx;
		//x += mult * kx; // Erreur d'arrondi;
		x += x_distance;
		y += mult * ky;
		z += mult * kz;
	}

	public void Invalidate()
	{
		valid = false;
	}

	/*public void Validate()
	{
		valid = true;
	}*/

	public void SetPosition( FPoint pos )
	{
		x = pos.x; y = pos.y; z = pos.z;
	}

	public void Translate( double x, double y, double z )
	{
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void SetKVector( FPoint k )
	{
		kx = k.x; ky = k.y; kz = k.z;
	}

	public void SetWavelength( double w )
	{
		wavelength = w;
	}

	public void SetPolarisation( FPoint p )
	{
		pol_x = p.x; pol_y = p.y; pol_z = p.z;
	}

	public void SetAmplitude( double a )
	{
		amplitude = a;
	}

	public void SetPhase( double p )
	{
		phase = p;
	}

	public boolean IsValid()
	{
		return valid;
	}

	public FPoint GetPosition()
	{
		return new FPoint( x, y, z );
	}

	public FPoint GetKVector()
	{
		return new FPoint( kx, ky, kz );
	}

	public double GetWavelength()
	{
		return wavelength;
	}

	public FPoint GetPolarisation()
	{
		return new FPoint( pol_x, pol_y, pol_z );
	}

	public double GetAmplitude()
	{
		return amplitude;
	}

	public double GetPhase()
	{
		return phase;
	}
}