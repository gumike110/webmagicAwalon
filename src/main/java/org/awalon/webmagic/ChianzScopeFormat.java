package org.awalon.webmagic;


import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public class ChianzScopeFormat implements ObjectFormatter<Integer>{
	
	//private Integer template;
	
	@Override
	public Integer format(String raw) throws Exception {
		// TODO Auto-generated method stub
		return  Integer.parseInt(raw.split(":")[1]);
	}

	@Override
	public Class<Integer> clazz() {
		// TODO Auto-generated method stub
		return Integer.class;
	}

	@Override
	public void initParam(String[] extra) {
		//System.out.println("extra==="+JsonUtil.toString(extra));
		//template = Integer.parseInt(extra[0].split(":")[1]);
		
	}

	
	

}
