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

package com.haskins.jcloudtrailerviewer.table;

import com.haskins.jcloudtrailerviewer.model.Event;
import java.io.IOException;
import javax.swing.table.AbstractTableModel;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author mark.haskins
 */
public class EventDetailTableModel extends AbstractTableModel {

    private final ObjectMapper mapper = new ObjectMapper();

    private Event detailEvent = null;

    public void showDetail(Event event) {

        detailEvent = event;
        fireTableRowsInserted(1, 2);
    }

    ///////////////////////////////////////////////////////////////////////////
    // AbstractTableModel implementation
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        int retVal = 0;

        if (detailEvent != null) {
            retVal = 19;
        }

        return retVal;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";

        switch (rowIndex) {

            case 0:
                value = "Event Version";
                if (columnIndex == 1) {
                    value = detailEvent.getEventVersion();
                }
                break;
            case 1:
                value = "User Identity";
                if (columnIndex == 1) {

                    value = "";
                    if (detailEvent.getUserIdentity() != null) {
                        try {
                            value = mapper.defaultPrettyPrintingWriter().writeValueAsString(detailEvent.getUserIdentity());
                        } catch (IOException ex) {
                            
                        }
                    }
                }
                break;
            case 2:
                value = "Event Time";
                if (columnIndex == 1) {
                    value = detailEvent.getEventTime();
                }
                break;
            case 3:
                value = "Event Source";
                if (columnIndex == 1) {
                    value = detailEvent.getEventSource();
                }
                break;
            case 4:
                value = "Event Name";
                if (columnIndex == 1) {
                    value = detailEvent.getEventName();
                }
                break;
            case 5:
                value = "AWS Region";
                if (columnIndex == 1) {
                    value = detailEvent.getAwsRegion();
                }
                break;
            case 6:
                value = "Source IP";
                if (columnIndex == 1) {
                    value = detailEvent.getSourceIPAddress();
                }
                break;
            case 7:
                value = "User Agent";
                if (columnIndex == 1) {
                    value = detailEvent.getUserAgent();
                }
                break;
            case 8:
                value = "Request Parameters";
                if (columnIndex == 1) {
                    value = "";
                    if (detailEvent.getRequestParameters() != null) {
                        try {
                            value = mapper.defaultPrettyPrintingWriter().writeValueAsString(detailEvent.getRequestParameters());
                        } catch (IOException ex) {
                            
                        }
                    }
                }
                break;
            case 9:
                value = "Response Elements";
                if (columnIndex == 1) {
                    value = "";
                    if (detailEvent.getResponseElements() != null) {
                        try {
                            value = mapper.defaultPrettyPrintingWriter().writeValueAsString(detailEvent.getResponseElements());
                        } catch (IOException ex) {
                            
                        }
                    }
                }
                break;
            case 10:
                value = "Request Id";
                if (columnIndex == 1) {
                    value = detailEvent.getRequestId();
                }
                break;
            case 11:
                value = "Event Type";
                if (columnIndex == 1) {
                    value = detailEvent.getEventType();
                }
                break;
            case 12:
                value = "Recipient Account";
                if (columnIndex == 1) {
                    value = detailEvent.getRecipientAccountId();
                }
                break;
            case 13:
                value = "Error Code";
                if (columnIndex == 1) {
                    value = detailEvent.getErrorCode();
                }
                break;
            case 14:
                value = "Error Message";
                if (columnIndex == 1) {
                    value = detailEvent.getErrorMessage();
                }
                break;
            case 15:
                value = "Read Only";
                if (columnIndex == 1) {
                    value = detailEvent.getReadOnly();
                }
                break;
            case 16:
                value = "Resources";
                if (columnIndex == 1) {
                    value = "";
                    if (detailEvent.getResources() != null) {
                        try {
                            value = mapper.defaultPrettyPrintingWriter().writeValueAsString(detailEvent.getResources());
                        } catch (IOException ex) {
                            
                        }
                    }
                }
                break;
            case 17:
                value = "Event Id";
                if (columnIndex == 1) {
                    value = detailEvent.getEventId();
                }
                break;
            case 18:
                value = "API Version";
                if (columnIndex == 1) {
                    value = detailEvent.getApiVersion();
                }
                break;
        }

        return value;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Private methods
    ////////////////////////////////////////////////////////////////////////////
    private final static String[] columnNames = new String[]{
        "Property", "Value"
    };
}
