
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Rays;

import Optical.Elements.*;
import Optical.Materials.*;
import Utils.*;

import java.util.Vector;
import java.util.Enumeration;
import java.awt.*;

public class RayCaster
{
	protected Vector				rays;
	protected Material				mat;

    public RayCaster()
    {
		super();
		rays = new Vector( 5, 5 );
	}

	public void DrawRays( Graphics g, OpticalDevice d )
	{
		Enumeration	e = rays.elements();

		while( e.hasMoreElements() )
		{
			Ray o = (Ray)e.nextElement();
			o.Vide();
			d.Propagate( o );
			d.DrawRay( g, o, 0 );
		}
	}

	public void SetInitialMaterial( Material mat )
	{
		this.mat = mat;
	}
}