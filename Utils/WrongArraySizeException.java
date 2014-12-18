
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Utils;

public class WrongArraySizeException extends Exception
{
	public int	WrongSize,
				CorrectSize;

	public String	FaultyMethod;

    public WrongArraySizeException()
    {
    }

	public WrongArraySizeException( String f, int w, int c )
	{
		FaultyMethod = f; WrongSize = w; CorrectSize = c;
	}

	public String toString()
	{
		return super.toString() + " " + FaultyMethod
			 + ", " + Integer.toString( WrongSize ) + " IO " + Integer.toString( CorrectSize );
	}
}