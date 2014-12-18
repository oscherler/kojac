
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

public class RayCastersCollection extends RayCaster
{
	protected Vector				raycasters;
	protected Material				mat;

    public RayCastersCollection()
    {
		super();
		raycasters = new Vector( 5, 5 );
	}

	public void DrawRays( Graphics g, OpticalDevice d )
	{
		Enumeration	e = raycasters.elements();

		while( e.hasMoreElements() )
		{
			RayCaster o = (RayCaster)e.nextElement();
			o.DrawRays( g, d );
		}
	}

	public void Append( RayCaster rc )
	{
		raycasters.addElement( rc );
		rc.SetInitialMaterial( mat );
	}

	public void SetInitialMaterial( Material mat )
	{
		this.mat = mat;
		
		Enumeration	e = raycasters.elements();

		while( e.hasMoreElements() )
		{
			RayCaster o = (RayCaster)e.nextElement();
			o.SetInitialMaterial( mat );
		}
	}
}