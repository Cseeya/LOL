/**
 * JStatistics.java
 *
 * @author Created by wt
 */

package marauroa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JStatistics
  extends JPanel
{
  private JLabel lblBytes;
  private JLabel lblPackets;
  private JLabel lblMessages;
  private JLabel lblActions;
  private JLabel lblObjects;
  private JLabel lblPlayers;
  private Timer timer;
  
  public JStatistics()
  {
    super(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.anchor     = GridBagConstraints.WEST;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.weightx    = 0.1;
    gbc.weighty    = 0.1;
    gbc.insets = new Insets(2,2,2,10);
    
    JLabel lbl = new JLabel("Bytes Received/Sent");
    add(lbl,gbc);
    gbc.gridx++;
    lblBytes = new JLabel("Unknown");
    add(lblBytes,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    lbl = new JLabel("Packets Received/Sent");
    add(lbl,gbc);
    gbc.gridx++;
    lblPackets = new JLabel("Unknown");
    add(lblPackets,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    lbl = new JLabel("Messages Received/Sent/Incorrect");
    add(lbl,gbc);
    gbc.gridx++;
    lblMessages = new JLabel("Unknown");
    add(lblMessages,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    lbl = new JLabel("Actions Added/Invalid");
    add(lbl,gbc);
    gbc.gridx++;
    lblActions = new JLabel("Unknown");
    add(lblActions,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    lbl = new JLabel("Objects Added/Removed/Now");
    add(lbl,gbc);
    gbc.gridx++;
    lblObjects = new JLabel("Unknown");
    add(lblObjects,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    lbl = new JLabel("Players Login/Logout/Online/Invalid Login");
    add(lbl,gbc);
    gbc.gridx++;
    lblPlayers = new JLabel("Unknown");
    add(lblPlayers,gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    
    timer = new Timer(1000,new ActionListener()
                      {
          public void actionPerformed(ActionEvent e)
          {
            Statistics.addBytesRecv(1);
            updateStats(Statistics.getVariables());
          }
        });
    updateStats(Statistics.getVariables());
    timer.start();
  }
  
  public void updateStats(Statistics.GatheredVariables vars)
  {
    lblBytes.setText(vars.bytesRecv + "/"+ vars.bytesSend);
    lblPackets.setText("Not there yet");
    lblMessages.setText(vars.messagesRecv + "/" + vars.messagesSend + "/" + vars.messagesIncorrect);
    lblActions.setText(vars.actionsAdded + "/" + vars.actionsInvalid);
    lblObjects.setText(vars.objectsAdded + "/" + vars.objectsRemoved + "/" + vars.objectsNow);
    lblPlayers.setText(vars.playersLogin + "/" + vars.playersLogout + "/" + vars.playersOnline + "/" +vars.playersInvalidLogin);
  }
  
  public void setRefreshDelay(int delay)
  {
    timer.stop();
    timer.setDelay(delay);
    timer.setInitialDelay(delay);
    timer.start();
  }
  
  public static JStatistics showStatistics()
  {
    JFrame frm = new JFrame("marauroad statistics");
    frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frm.setResizable(false);
    JStatistics js = new JStatistics();
    frm.setContentPane(js);
    frm.pack();
    frm.show();
    return(js);
  }
  
  public static void main(String argv[])
  {
    JStatistics js = showStatistics();
    try
    {
      Thread.sleep(5000);
    }
    catch (InterruptedException e) {}
    js.setRefreshDelay(2000);
  }
  
}
