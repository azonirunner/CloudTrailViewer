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

package com.haskins.jcloudtrailerviewer.filter;

import com.haskins.jcloudtrailerviewer.model.Event;

/**
 *
 * @author mark
 */
public class AccountFilter extends AbstractEventFilter {
    
    private String account;
    
    @Override
    public void setNeedle(String account) {
        
        this.account = account;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///// Abstract implementations
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean passesFilter(Event event) {
        
        boolean passesFilter = false;
        
        if (account == null || account.trim().length() == 0) {
            
            passesFilter = true;
            
        } else {
            
            String needle = event.getUserIdentity().getAccountId();

            if (needle != null && needle.contains(this.account)) {
                passesFilter = true;
            }
        }
       
        return passesFilter;
    }
}
