package com.youzu.topsango.config {
	import flash.utils.ByteArray;
	import flash.utils.Dictionary;

	public class ParamConfig{
		public static final String urlKey="parameter.tbl";
		/**参数项*/
		public var param:String;
		/**性别*/
		public var value:String;
		/**颜色*/
		public var color:int;
			
		static public function parse(data:ByteArray):void{
			while(data.hasRemaining()){
				var config:ParamConfig = new ParamConfig();
				config.param=data.readUTFBytes(bytes.readUnsignedShort());
				config.value=data.readUTFBytes(bytes.readUnsignedShort());
				config.color=data.readInt();
				
				ParamConfigCache.putConfig(config);
			}
		}
}
