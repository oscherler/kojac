
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Rays;

import Optical.Materials.*;
import Utils.*;

import java.util.*;

public class ParallelRays extends RayCaster
{
	private double n, width, w;

    public ParallelRays()
    {
		super();
    }

	public ParallelRays( FPoint pos, FPoint dir, int n, double width, double w, Material mat )
	{
		super();

		this.n = n;
		this.width = width;
		this.w = w;
		this.mat = mat;

		Move( pos, dir );
	}

	public void SetWavelength( double w )
	{
		this.w = w;

		Enumeration	e = rays.elements();
		
		while( e.hasMoreElements() )
		{
			Ray o = (Ray)e.nextElement();
			o.SetWavelength( w );
		}
	}

	public double GetWavelength()
	{
		return w;
	}

	public void Move( FPoint pos, FPoint dir )
	{
		rays.setSize( 0 );
		FPoint top = new FPoint( pos.x, pos.y - width / 2, pos.z );

		if( n > 1 )
		{
			double interval = width / ( n - 1 );
	
			for( int i = 0; i < n; i++ )
			{
				FPoint p = new FPoint( top.x, top.y + i * interval, top.z );
				rays.addElement( new Ray( new RayPoint( p, dir, w, mat.IndexAtWavelength( w ) ) ) );
			}
		}
		else
		{
			rays.addElement( new Ray( new RayPoint( pos, dir, w, mat.IndexAtWavelength( w ) ) ) );
		}
	}
}