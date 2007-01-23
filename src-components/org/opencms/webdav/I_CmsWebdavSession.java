/*
 * File   : $Source: /alkacon/cvs/opencms/src-components/org/opencms/webdav/Attic/I_CmsWebdavSession.java,v $
 * Date   : $Date: 2007/01/23 16:58:11 $
 * Version: $Revision: 1.1.2.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.webdav;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * A session for WebDAV to handle the actions.
 * 
 * @author Peter Bonrad
 */
public interface I_CmsWebdavSession {

    /**
     * Copies the item found at the source path to the destination path.
     * 
     * @param src The path of the item which should be copied
     * @param dest The destination path where to copy to
     * @param overwrite Should any existing item be overwritten
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     * @throws CmsWebdavItemAlreadyExistsException if the resource already exists
     */
    void copy(String src, String dest, boolean overwrite)
    throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException, CmsWebdavItemAlreadyExistsException;

    /**
     * Creates a new item at the given path. In this case this should
     * be a collection (directory).
     * 
     * @param path The complete path of the new collection
     * @throws CmsWebdavItemAlreadyExistsException if the resource already exists
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    void create(String path) throws CmsWebdavItemAlreadyExistsException, CmsWebdavPermissionException;

    /**
     * Creates a new item at the given path. This creates a new single
     * item (file).
     * 
     * @param path The complete path of the new item
     * @param inputStream The content of the item
     * @param overwrite Should an existing item at the path be overwritten
     * @throws CmsWebdavItemAlreadyExistsException if the resource already exists and should not be overwritten
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    void create(String path, InputStream inputStream, boolean overwrite)
    throws CmsWebdavItemAlreadyExistsException, CmsWebdavPermissionException;

    /**
     * Deletes the item at the given path.
     * 
     * @param path The complete path of the item to delete
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    void delete(String path) throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException;

    /**
     * Returns if an item exists at the given path.
     * 
     * @param path The complete path of the item to check existance
     * @return true if the item exists otherwise false
     */
    boolean exists(String path);

    /**
     * Returns the item found at the given path. 
     * 
     * @param path The complete path of the item to return
     * @return the item found at the path
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    I_CmsWebdavItem getItem(String path) throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException;

    /**
     * Returns the lock for the resource at the given path.
     * 
     * @param path The complete path where to return the lock for
     * @return the found lock as CmsWebdavLockInfo or null if not found
     */
    CmsWebdavLockInfo getLock(String path);

    /**
     * Initialize the session with the servlet context.
     * 
     * @param servletContext The servlet context
     * @throws ServletException if something goes wrong
     */
    void init(ServletContext servletContext) throws ServletException;

    /**
     * Returns a list with all items found directly in the given path.
     * 
     * @param path The complete path from which to return the items
     * @return a list with (I_CmsWebdavItem) found in the path
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    List list(String path) throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException;

    /**
     * Creates a new lock on the item with the path with the given information 
     * in the lock info.
     * 
     * @param path The complete path of the item
     * @param lock The information about the lock to create
     * @return if the lock token was successfully added
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     */
    boolean lock(String path, CmsWebdavLockInfo lock)
    throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException;

    /**
     * Moves an item from a source path to a destination path.
     * 
     * @param src The complete path to the item which should be copied
     * @param dest The complete destination path where to copy to
     * @param overwrite Should any existing item should be overwritten
     * @throws CmsWebdavItemNotFoundException if the source path could not be found
     * @throws CmsWebdavPermissionException if there is a permission issue
     * @throws CmsWebdavItemAlreadyExistsException if the resource already exists and should not be overwritten
     */
    void move(String src, String dest, boolean overwrite)
    throws CmsWebdavItemNotFoundException, CmsWebdavPermissionException, CmsWebdavItemAlreadyExistsException;

    /**
     * Unlocks the item found at the path. Should remove the lock token
     * from the existing lock found at the path.
     * 
     * @param path The complete path of the item to unlock
     */
    void unlock(String path);
}
