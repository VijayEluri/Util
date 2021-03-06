/*
 * Copyright (c) 2006-2017 DMDirc Developers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.dmdirc.util.validators;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringLengthValidatorTest {

    @Test
    public void testUnlimited() {
        final Validator<String> slv = new StringLengthValidator(-1, 10);
        
        assertFalse(slv.validate("123").isFailure());
        assertFalse(slv.validate("").isFailure());
        assertFalse(slv.validate("123456789").isFailure());
        
        final Validator<String> slv2 = new StringLengthValidator(0, -1);
        
        assertFalse(slv2.validate("123456789").isFailure());
        assertFalse(slv2.validate("1").isFailure());
    }
    
    @Test
    public void testMinimum() {
        final Validator<String> slv = new StringLengthValidator(5, 100);
        
        assertTrue(slv.validate("").isFailure());
        assertTrue(slv.validate("123").isFailure());
        assertTrue(slv.validate("1234").isFailure());
        assertTrue(slv.validate("1234").getFailureReason().contains("at least 5"));
        assertFalse(slv.validate("12345").isFailure());
        assertFalse(slv.validate("12345789").isFailure());
    }
    
    @Test
    public void testMaximum() {
        final Validator<String> slv = new StringLengthValidator(0, 3);
        
        assertFalse(slv.validate("").isFailure());
        assertFalse(slv.validate("12").isFailure());
        assertFalse(slv.validate("123").isFailure());
        assertTrue(slv.validate("1234").isFailure());
        assertTrue(slv.validate("1234").getFailureReason().contains("at most 3"));
        assertTrue(slv.validate("12345").isFailure());
    }    

}