/* $Id: INetworkServerManager.java,v 1.7 2007/02/05 18:37:43 arianne_rpg Exp $ */
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
package marauroa.server.net;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import marauroa.common.net.message.Message;
import marauroa.server.net.validator.ConnectionValidator;

/** A Network Server Manager is an active object ( a thread ) that send and recieve messages
 *  from clients. There is not transport or technology imposed.
 *  
 * @author miguel
 */
public interface INetworkServerManager {
	/**
	 * Register a listener that will be called when a disconnected event happens.
	 * It is up to the implementer if this call add or replace the actual listener.
	 */
	public abstract void registerDisconnectedListener(IDisconnectedListener listener);
	
	/**
	 * This method provides the connection validator object.
	 * You can use it to ban connection IP.
	 * @return validator.
	 */
	public abstract ConnectionValidator getValidator();
	
	/** 
	 * This method returns a Message from the list or block for timeout milliseconds
	 * until a message is available or null if timeout happens.
	 *
	 * @param timeout timeout time in milliseconds
	 * @return a Message or null if timeout happens
	 */
	public abstract Message getMessage(int timeout);

	/** 
	 * This method blocks until a message is available
	 *
	 * @return a Message
	 */
	public abstract Message getMessage();

	/**
	 * This method add a message to be delivered to the client the message
	 * is pointed to.
	 *
	 * @param msg the message to be delivered.
	 * @throws IOException 
	 */
	public abstract void sendMessage(Message msg);

	/** 
	 * This method disconnect a client or silently fails if client doesn't exists.
	 *  
	 * @param address
	 */
	public abstract void disconnectClient(SocketChannel channel);

	/**
	 * This method inits the active object 
	 */
	public abstract void start();
	
	/** 
	 * This method notify the active object to finish it execution
	 */
	public abstract void finish();


}