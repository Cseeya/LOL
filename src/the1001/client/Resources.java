/* $Id: Resources.java,v 1.1 2004/02/15 19:21:06 root777 Exp $ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

package the1001.client;

import java.net.URL;

/**
 *@author Waldemar Tribus
 */
public class Resources
{
	/* this loader can be later exchanged with some caching network loader,
	 which can check if some resource already available local and if not
	 then load it into local cache
	 */
	private static ClassLoader loader = Resources.class.getClassLoader();
	
	
	/**
	 * looks in <classpath>/images/ for <resource>
	 * f.e. getSoundUrl("SkyDome.jpg") will look for SkyDome.jpg
	 * in <classpath>/images/SkyDome.jpg
	 **/
	public static URL getImageUrl(String resource)
	{
		if(resource!=null)
		{
			return(loader.getResource("images/"+resource));
		}
		return(null);
	}
	
	/**
	 * looks in <classpath>/sounds/ for <resource>
	 * f.e. getSoundUrl("ambient.wav") will look for ambient.wav
	 * in <classpath>/sounds/ambient.wav
	 **/
	public static URL getSoundUrl(String resource)
	{
		if(resource!=null)
		{
			return(loader.getResource("sounds/"+resource));
		}
		return(null);
	}
	
	/**
	 * looks in <classpath>/models/<modelname>/ for <resource>
	 * modelname is the <resource> without extension
	 * f.e. getModelUrl("orc.pcx") will look for orc.pcx
	 * in <classpath>/models/orc/orc.pcx
	 **/
	public static URL getModelUrl(String resource)
	{
		if(resource!=null)
		{
			String modelname = resource.substring(0,resource.lastIndexOf("."));
			return(loader.getResource("models/"+modelname+"/"+resource));
		}
		return(null);
	}
}