package com.example.android.testing.unittesting.BasicSample;

import org.junit.Assert;
import org.junit.Test;

/**
 * 邮箱验证case:1,没有名字@前面的内容2，null 3,没有@后面的内容 4，写两个点 5，完整的邮箱 6，有子域名的邮箱 7，
 */
public class ImitateEmailValidatorTest {

    @Test
    public void imitateEmailValidator_CorrectEmailSimple_ReturnTrue(){
        Assert.assertTrue(ImitateEmailValidator.isValidEmail("test@gmail.com"));
    }

    @Test
    public void imitateEmailValidator_CorrectEmailSubDomain_ReturnTrue(){
        Assert.assertTrue(ImitateEmailValidator.isValidEmail("test@gmail.com.cn"));
    }
    @Test
    public void imitateEmailValidator_InvalidEmailDoubleDot_ReturnFalse(){
        Assert.assertFalse(ImitateEmailValidator.isValidEmail("test@gmail..com"));
    }
    @Test
    public void imitateEmailValidator_NullEmail_ReturnFalse(){
        Assert.assertFalse(ImitateEmailValidator.isValidEmail(null));
    }

    @Test
    public void imitateEmailValidator_EmptyString_ReturnFalse(){
        Assert.assertFalse(ImitateEmailValidator.isValidEmail(""));
    }
    @Test
    public void imitateEmailValidator_InvalidEmailNoUserName_ReturnFalse(){
        Assert.assertFalse(ImitateEmailValidator.isValidEmail("@gmail.com"));
    }
    @Test
    public void imitateEmailValidator_InvalidEmailNoId_ReturnFalse(){
        Assert.assertFalse(ImitateEmailValidator.isValidEmail("test@gmail"));
    }
}