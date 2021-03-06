package org.avp.util;

import com.arisux.amdxlib.lib.util.Math;

public enum XenomorphJawState
{
    CLOSED(0, 43, 40),
    OPEN(1.5F, 65, 60);
    
    public final float jawOffset;
    public final float lowerJawAngle;
    public final float innerJawAngle;
    
    XenomorphJawState(float jawOffset, float lowerJawAngle, float innerJawAngle)
    {
        this.jawOffset = jawOffset;
        this.lowerJawAngle = lowerJawAngle;
        this.innerJawAngle = innerJawAngle;
    }
    
    public static float interpolateLowerJawAngle(float progress)
    {
        return Math.interpolateRotation(CLOSED.lowerJawAngle, OPEN.lowerJawAngle, progress);
    }
    
    public static float interpolateInnerJawAngle(float progress)
    {
        return Math.interpolateRotation(CLOSED.innerJawAngle, OPEN.innerJawAngle, progress);
    }
    
    public static float calculateJawOffset(float progress)
    {
        return progress * OPEN.jawOffset;
    }
}