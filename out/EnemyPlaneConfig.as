package com.youzu.topsango.config {
	import flash.utils.ByteArray;
	import flash.utils.Dictionary;

	public class EnemyPlaneConfig{
		public static const urlKey:String="enemyPlane.tbl";
		/**ID*/
		public var {0}:int;
		/**skin*/
		public var {0}:String;
		/**speed*/
		public var {0}:int;
		/**hp*/
		public var {0}:int;
		/**reward*/
		public var {0}:int;
		/**star*/
		public var {0}:int;
			
		static public function parse(data:ByteArray):Vector.<EnemyPlaneConfig>{
			var dic:Vector.<EnemyPlaneConfig>=new Vector.<EnemyPlaneConfig>();
			while(data.bytesAvailable){
				var config:EnemyPlaneConfig = new EnemyPlaneConfig();
				config.id=data.readInt();
				config.skin=data.readUTFBytes(data.readUnsignedShort());
				config.speed=data.readInt();
				config.hp=data.readInt();
				config.reward=data.readInt();
				config.star=data.readInt();
				
				dic.push(config);
			}
			return dic;
		}
}
