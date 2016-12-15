package com.youzu.topsango.config {
	import flash.utils.ByteArray;
	import flash.utils.Dictionary;

	/**
	 * hero.xlsx - 兵种进阶
	 * 自动生成，请务修改
	 */
	public class ArmyConfig {
		static public const urlKey:String = "army.tbl";
		/** 兵种 */
		public var id:String;
		/** 显示 */
		public var name:String;
		/** 美术资源 */
		public var asset:String;
		/** 类型 */
		public var type:String;
		/** 颜色 */
		public var color:int;
		/** 阶级 */
		public var phase:int;
		/** 攻击 */
		public var attackProp:int;
		/** 防御 */
		public var defenseProp:int;
		/** 兵力 */
		public var hpProp:int;
		/** 速度 */
		public var speedProp:int;
		/** 粮草消耗 */
		public var expendProp:int;
		/** 英雄等级 */
		public var part1NeedLevel:int;
		/** 升阶消耗银两 */
		public var upCostSilver:String;
		/** 军械1 */
		public var armament1:String;
		/** 军械2 */
		public var armament2:String;
		/** 军械3 */
		public var armament3:String;
		
		static public function parse(bytes:ByteArray):Vector.<ArmyConfig> {
			var dic:Vector.<ArmyConfig> = new Vector.<ArmyConfig>();
			while (bytes.bytesAvailable) {
				var vo:ArmyConfig = new ArmyConfig();
				vo.id = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.name = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.asset = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.type = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.color = bytes.readInt();
				vo.phase = bytes.readInt();
				vo.attackProp = bytes.readInt();
				vo.defenseProp = bytes.readInt();
				vo.hpProp = bytes.readInt();
				vo.speedProp = bytes.readInt();
				vo.expendProp = bytes.readInt();
				vo.part1NeedLevel = bytes.readInt();
				vo.upCostSilver = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.armament1 = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.armament2 = bytes.readUTFBytes(bytes.readUnsignedShort());
				vo.armament3 = bytes.readUTFBytes(bytes.readUnsignedShort());
				
				dic.push(vo);
			}
			return dic;
		}
	}
}