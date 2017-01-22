// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `tms.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.lpmas.tms.component;

public final class TmsServiceHolder extends Ice.ObjectHolderBase<TmsService>
{
    public
    TmsServiceHolder()
    {
    }

    public
    TmsServiceHolder(TmsService value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof TmsService)
        {
            value = (TmsService)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _TmsServiceDisp.ice_staticId();
    }
}
