package com.example.administrator.googleplay.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/16
 *   @desc     :
 *   @version  :   V 1.0.9
 */
public class UtilsTest {
    Utils utils;
    @Before
    public void setUp() throws Exception {
        utils = new Utils();
    }

    @Test
    public void isMaxTo() throws Exception {
        assertEquals(true,Utils.isMaxTo(8,4));
    }

    @Test
    public void gethalf() throws Exception {
        assertEquals(4,Utils.gethalf(8));
    }

}