package org.awalon.webmagic;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public class RankConvertFormat implements ObjectFormatter<Integer> {

	private static final String REGEX = "\\d*";
	
	@Override
	public Integer format(String raw) throws Exception {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(raw);
		int result = 0;
		//无论有几组数字被取到只返回最后一组；因为需要的数字永远在字符窜最后面位置
		while (m.find()) {
			if (!"".equals(m.group())) {
				result = Integer.parseInt(m.group());
			}
		}
		return result;	
	}

	@Override
	public Class<Integer> clazz() {
		return Integer.class;
	}

	@Override
	public void initParam(String[] extra) {
		
	}

}
