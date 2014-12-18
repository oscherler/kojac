package Optical.Gui;

import Optical.Elements.*;
import Optical.Materials.*;
import Utils.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.EventListener;

public class OpticalApp extends Applet implements RespondToEvents
{
	public void init()
	{
	}

	public void Scrollbar( String name, int value )
	{
	}

	public void Checkbox( String name, boolean value )
	{
	}

	public void Button( String name )
	{
	}

	public void destroy()
	{
	}

	public void start()
	{
	}

	public void stop()
	{
	}

	public void processEvent( AWTEvent e )
	{
		if (e.getID() == Event.WINDOW_DESTROY)
		{
			System.exit(0);
		}
	}

	public String getAppletInfo()
	{
		// Too funny to destroy
		// return "An interactive test of the Graphics.drawArc and \nGraphics.fillArc routines. Can be run \neither as a standalone application by typing 'java ArcTest' \nor as an applet in the AppletViewer.";
		return "";
	}

	public OpticalApp()
	{
		try
		{
			jbInit();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		this.setBackground( new Color( 255, 255, 255 ) );
	}
}