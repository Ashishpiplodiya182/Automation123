package com.demo.table;

import com.aventstack.extentreports.markuputils.Markup;

public class Helper 
{
	public static Table createTable(String[] actual , String[] expected) {
        Table t = new Table();
        t.setData(actual ,expected );
        return t;
    }

}
