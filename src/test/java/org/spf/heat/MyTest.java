package org.spf.heat;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class MyTest {
	
	@Test
	public void time(){

		Calendar calendar  = Calendar.getInstance();
		calendar.set(2016, 7, 10, 18, 40, 00);
		
		for(int i=0;i<5;i++){	
			calendar.add(Calendar.MINUTE, 5);
			System.out.println(calendar.getTimeInMillis()/1000);
			
		}
		
	}
	@Test
	public void strTest(){
		String raw = "/themes/default/2ed_4_34_sdf2/ranks/Rank_10.gif";
		String regex = "\\d*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(raw);
		int result = 0;
		while (m.find()) {
			if (!"".equals(m.group())) {
				result = Integer.parseInt(m.group());
			}
			
		}
		
		//return result;
	}

}
