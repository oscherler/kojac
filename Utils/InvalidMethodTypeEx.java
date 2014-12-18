
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Utils;

public class InvalidMethodTypeEx extends Exception
{
	public String Name = "";

    public InvalidMethodTypeEx()
    {
    }

	public InvalidMethodTypeEx( String s )
    {
		Name = new String( s );
    }
}