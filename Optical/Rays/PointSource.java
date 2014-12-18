
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

public class PointSource extends RayCaster
{
	private double n, width, w;
	private FPoint	s, d1, d2;

    public PointSource()
    {
		super();
    }

	public PointSource( FPoint src, FPoint dest1, FPoint dest2, int n, double w )
	{
		super();

		this.n = n;
		//this.width = width;
		this.w = w;

		Move( src, dest1, dest2 );
	}

	public void Move( FPoint src, FPoint dest1, FPoint dest2 )
	{
		s = new FPoint( src );
		if( dest1 != null ) d1 = new FPoint( dest1 );
		if( dest2 != null ) d2 = new FPoint( dest2 );

		rays.setSize( 0 );
		double	diffX = (d2.x - d1.x),
				diffY = (d2.y - d1.y);

		for( int i = 0; i < n; i++ )
		{
			FPoint newdir = new FPoint(
				d1.x - s.x + (i * diffX / (n-1)),
				d1.y - s.y + (i * diffY / (n-1)),
				d1.z - s.z );
			rays.addElement( new Ray( new RayPoint( s, newdir, w ) ) );
		}
	}

	public void Move( FPoint src )
	{
		Move( src, null, null );
	}
}