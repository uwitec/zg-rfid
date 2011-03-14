package com.boco.frame.cache;

/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2006 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 */
/**
 * Revision Information:
 * 
 *@version 1.0 2009-5-12 release(zhangyinghui.Bright)
 */

import java.util.regex.Pattern;

public abstract interface ICacheService
{
  public abstract void putElement(String paramString, Object paramObject);

  public abstract void removeElement(String paramString);

  public abstract void removeAll();

  public abstract void removeElementByReg(Pattern paramPattern);

  public abstract String assemblyKey(String[] paramArrayOfString);

  public abstract boolean isExistKey(String paramString);

  public abstract Object getValue(String paramString);

  public abstract void setTemstap(long paramLong);

  public abstract long getTemstap();
  
  public void fulsh();
}
