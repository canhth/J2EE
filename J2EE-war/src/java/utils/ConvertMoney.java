/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.text.DecimalFormat;

/**
 *
 * @author Royal
 */
public class ConvertMoney {
    public String countCharge(double charge)
    {      
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(charge);       
    }
}
