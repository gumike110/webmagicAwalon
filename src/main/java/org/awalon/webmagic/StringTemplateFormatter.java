package org.awalon.webmagic;

import org.awalon.utils.JsonUtil;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public class StringTemplateFormatter implements ObjectFormatter<String> {

	private String template;

	@Override
	public String format(String raw) throws Exception {
		System.out.println(raw);
		return String.format(template, raw);
	}

	@Override
	public Class<String> clazz() {
		return String.class;
	}

	@Override
	public void initParam(String[] extra) {
		System.out.println("extra:::::"+ JsonUtil.toString(extra));
		template = extra[0];
	}

}
