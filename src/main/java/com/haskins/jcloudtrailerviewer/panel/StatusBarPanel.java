/*    
CloudTrail Log Viewer, is a Java desktop application for reading AWS CloudTrail
logs files.

Copyright (C) 2015  Mark P. Haskins

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.haskins.jcloudtrailerviewer.panel;

import com.haskins.jcloudtrailerviewer.event.EventLoaderListener;
import com.haskins.jcloudtrailerviewer.model.Event;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mark
 */
public class StatusBarPanel extends JPanel implements EventLoaderListener {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
    
    private final JLabel lblFirstEvent = new JLabel();
    private final JLabel lblLastEvent = new JLabel();
    
    private final JLabel lblMessage = new JLabel();
    
    private final JLabel lblEventsLoaded = new JLabel("0");
    
    private long firstEvent = 0;
    private long lastEvent = 0;
                    
    public  StatusBarPanel() {
        buildUI();
    }
       
    ////////////////////////////////////////////////////////////////////////////
    ///// EventLoaderListener implementation
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void newEvents(List<Event> events) {
        
        for (Event event : events) {
            
            long eventTime = event.getTimestamp();
            
            if (firstEvent == 0 || eventTime < firstEvent) {
                firstEvent = eventTime;
            }
            
            if (lastEvent == 0 || eventTime > lastEvent) {
                lastEvent = eventTime;
            }   
        }
        
        incrementEventsLoaded(events.size());
    }

    @Override
    public void finishedLoading() {
        
        lblFirstEvent.setText(sdf.format(firstEvent));
        lblLastEvent.setText(sdf.format(lastEvent));
        
        newMessage("Finished Loading Events");
    }

    @Override
    public void newMessage(String message) {
        lblMessage.setText(message);
        lblMessage.setToolTipText(message);
    }
    

    ////////////////////////////////////////////////////////////////////////////
    ///// private methods
    ////////////////////////////////////////////////////////////////////////////
    private void incrementEventsLoaded(int events) {
        
        String currentNumEvents = this.lblEventsLoaded.getText();
        int count = Integer.valueOf(currentNumEvents);
        count = count + events;
        
        this.lblEventsLoaded.setText(String.valueOf(count));
        lblEventsLoaded.setToolTipText(String.valueOf(count));
    }

    private void buildUI() {
  
        JPanel leftSection = new JPanel();
        leftSection.add(lblFirstEvent);
        leftSection.add(new JLabel(" - "));
        leftSection.add(lblLastEvent);
        
        JPanel middleSection = new JPanel();
        middleSection.add(lblMessage);
        
        JPanel rightSection = new JPanel();
        rightSection.add(new JLabel("Events Loaded :"));
        rightSection.add(lblEventsLoaded);
        
        this.setLayout(new GridLayout(0,3));
        
        this.add(leftSection);
        this.add(middleSection);
        this.add(rightSection);
        
        this.setVisible(true);
    }
}
