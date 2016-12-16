package com.aim.config.fo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.aim.config.cache.EnemyPlaneConfigCache;

public class EnemyPlaneConfig{
	public static String urlKey="enemyPlane.tbl";
	/**ID*/
	public int id;
	/**skin*/
	public String skin;
	/**speed*/
	public int speed;
	/**hp*/
	public int hp;
	/**reward*/
	public int reward;
	/**star*/
	public int star;
		
	public static void parse(ByteBuffer data){
		data.order(ByteOrder.LITTLE_ENDIAN);
		while(data.hasRemaining()){
			byte[] b = null;
			EnemyPlaneConfig config = new EnemyPlaneConfig();
			config.id=data.getInt();
			b = new byte[data.getShort()];data.get(b);config.skin=new String(b);
			config.speed=data.getInt();
			config.hp=data.getInt();
			config.reward=data.getInt();
			config.star=data.getInt();
			
			EnemyPlaneConfigCache.putConfig(config);
		}
	}
}
