/* $Id: MessageS2CTransferREQ.java,v 1.1 2004/08/29 11:12:42 arianne_rpg Exp $ */
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
package marauroa.net;

import java.util.*;
import java.net.*;
import java.io.*;

public class MessageS2CTransferREQ extends Message
  {
  private List contents;
  
  /** Constructor for allowing creation of an empty message */
  public MessageS2CTransferREQ()
    {
    super(null);
    type=TYPE_S2C_TRANSFER_REQ;
    }
  
  public MessageS2CTransferREQ(InetSocketAddress source,List contents)
    {
    super(source);
    type=TYPE_S2C_TRANSFER_REQ;
    
    this.contents=contents;
    }
  
  public List getContents()
    {
    return contents;
    }
  
  public String toString()
    {
    return "Message (S2C Transfer REQ) from ("+source.getAddress().getHostAddress()+") CONTENTS: ("+contents.size()+")";
    }

  public void writeObject(marauroa.net.OutputSerializer out) throws IOException
    {
    super.writeObject(out);
    
    int size=contents.size();
    out.write(size);
    
    Iterator it=contents.iterator();
    while(it.hasNext())
      {
      TransferContent content=(TransferContent)it.next();
      content.writeREQ(out);
      }    
    }
  
  public void readObject(marauroa.net.InputSerializer in) throws IOException, ClassNotFoundException
    {
    super.readObject(in);
    
    int size=in.readInt();
    contents=new LinkedList();
      
    for(int i=0;i<size;i++)
      {
      TransferContent content=new TransferContent();
      content.readREQ(in);
      contents.add(content);
      }

    if(type!=TYPE_S2C_TRANSFER_REQ)
      {
      throw new java.lang.ClassNotFoundException();
      }
    }
  }