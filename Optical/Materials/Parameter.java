
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

abstract public class Parameter extends Object implements Cloneable
{

	// Every subclass must override this method returning its
	// method type. Ex: return Parameter.Sellmeier1;
	abstract public String type();

    public Parameter()
    {
    }

	public Parameter( double[] n ) throws WrongArraySizeException
	{
	}

	abstract public double[] AsArray();

	abstract public void SetArray( double[] n ) throws WrongArraySizeException;

	abstract public double IndexAtWavelength( double w );
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}