
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

public class ThreeRays extends RayCaster
{
	private double w;
	private FPoint pos;

    public ThreeRays()
    {
		super();
    }

	public ThreeRays( FPoint pos, FPoint inter1, FPoint inter2, double w )
	{
		super();
		SetIntersections( pos, inter1, inter2, w );
	}

	public void SetIntersections( FPoint pos, FPoint inter1, FPoint inter2, double w )
	{
		FPoint	dir1 = new FPoint( inter1.x - pos.x, inter1.y - pos.y, inter1.z - pos.z ),
				dir2 = new FPoint( inter2.x - pos.x, inter2.y - pos.y, inter2.z - pos.z ),
				dir3 = new FPoint( 1.0, 0.0, 0.0 );

		this.pos = new FPoint( pos );
		this.w = w;
		RayPoint r;

		rays.setSize( 0 );
		r = new RayPoint( pos, dir1, w );
		if( dir1.x == 0 ) r.Invalidate();
		rays.addElement( new Ray( r ) );
		r = new RayPoint( pos, dir2, w );
		if( dir2.x == 0 ) r.Invalidate();
		rays.addElement( new Ray( r ) );
		r = new RayPoint( pos, dir3, w );
		rays.addElement( new Ray( r ) );
	}

	public void SetIntersections( FPoint inter1, FPoint inter2 )
	{
		FPoint	dir1 = new FPoint( inter1.x - pos.x, inter1.y - pos.y, inter1.z - pos.z ),
				dir2 = new FPoint( inter2.x - pos.x, inter2.y - pos.y, inter2.z - pos.z ),
				dir3 = new FPoint( 1.0, 0.0, 0.0 );

		rays.setSize( 0 );
		rays.addElement( new Ray( new RayPoint( pos, dir1, w ) ) );
		rays.addElement( new Ray( new RayPoint( pos, dir2, w ) ) );	
		rays.addElement( new Ray( new RayPoint( pos, dir3, w ) ) );
	}

}