
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Materials;

import java.io.*;
import Utils.*;

public class FromFile
{

    public FromFile()
    {
    }

	static public void ReadFile( File f )
	{
		BufferedReader bf;
		Material m = null;

		if( f.exists() && f.canRead() ) try
		{
			bf = new BufferedReader( new FileReader( f ) );
			//String s = null;
			do
			{
				m = ReadMaterial( bf );
				if( m != null ) System.out.println( m.GetName() );
			} while( m != null );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	static private Material ReadMaterial( BufferedReader bf )
		throws IOException
	{
		String s = bf.readLine();
		
		if( s != null )
		{
			Material mat = new Material();
			mat.SetName( s );
	
			s = bf.readLine();
			while( s != null && !s.equals( "" ) )
			{
				try
				{
					mat.AddParameterSet( s );
				}
				catch( WrongArraySizeException e )
				{
					System.out.println( mat.GetName() + ": " + e.toString() );
				}
				catch( InvalidMethodTypeEx e )
				{
					System.out.println( "Unknown method: " + e.Name );
				}
				catch( NumberFormatException e )
				{
					System.out.println( mat.GetName() + ": " + "NumberFormatException" );
				}
				s = bf.readLine();
			} 
			return mat;
		}
		else return null;
	}

	static void main( String args[] )
	{
		File f = new File( (new File( "Materials.txt" )).getAbsolutePath() );

		ReadFile( f );
	}
}