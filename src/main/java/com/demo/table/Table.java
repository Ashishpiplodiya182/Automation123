package com.demo.table;

import com.aventstack.extentreports.markuputils.Markup;

public class Table implements Markup
{
	
	    private String[] expected;
	    private String[] actual;
	    
	    
	    public void setData(String[] actual , String[] expected) {
	        this.actual = actual;
	        this.expected = expected;
	        
	    }
	    public Object[] getData() { return new Object[]{actual, expected}; }    
	    
	    @Override
	    public String getMarkup() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<table  style=\"border:white; border-width:3px; border-style:solid; text-align:center;\">");
	        sb.append("<tr>");
	       
            sb.append("<th style=\"border:white; border-width:2px; border-style:solid; text-align:center;\"> <font size=\"2\"class=\"label white-text orange\">Expected Values</font></th> <th style=\"border:white; border-width:2px; border-style:solid; text-align:center;\"><font size=\"2\"class=\"label white-text orange\">Actual Values</font></th>");
            sb.append("</tr>");

            for(int i=0;i<expected.length;i++) {
            	sb.append("<tr >");
            	sb.append("<td style=\"border:white; border-width:2px; border-style:solid; text-align:center;\">"+  expected[i] +"</td>" + "<td style=\"border:white; border-width:2px; border-style:solid; text-align:center;\">"+  actual[i] +"</td>");
            	//sb.append("<tr>");
	    }
            
	        sb.append("</table>");
	       
	        return sb.toString();
	    }
	    

	    
	    

	    
	}


