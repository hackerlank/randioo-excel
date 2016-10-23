package com.randioo.config.randioo_excel.po;

import com.randioo.config.randioo_excel.Data;
import com.randioo.config.randioo_excel.cache.ParamConfigCache;

public class ParamConfig{
	public static final String urlKey="parameter.tbl";
	/**参数项*/
	public String param;
	/**性别*/
	public int value;
	/**颜色*/
	public int color;
		
	public static void parse(Data data){
		while(data.hasRemaining()){
			ParamConfig config = new ParamConfig();
			config.param=data.getString();
			config.value=data.getInt();
			config.color=data.getInt();
			
			ParamConfigCache.putConfig(config);
		}
	}
}
