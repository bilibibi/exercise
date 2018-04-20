<#include "/tableAlias.include">
<#include "/copyright.include">
package ${businessXmlns}
{
	import com.universalmind.cairngorm.business.Delegate;
	import com.universalmind.cairngorm.events.Callbacks;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import ${voXmlns}.*;
	
	public class ${className}Delegate extends Delegate
	{
		private var command : IResponder;
		public function ${className}Delegate( responder : IResponder )
		{
			super(responder,"${classNameLower}Service");
		}
		
        public function add${className}( ${table.classNameFirstLower}VO : ${className}VO ): void
		{	
 			var intercept:Callbacks   = new Callbacks(result,this.onFault);
	        var token	 :AsyncToken = service.add${className}(${table.classNameFirstLower}VO);
	        prepareHandlers(token,intercept); 
		}
		public function update${className}( ${table.classNameFirstLower}VO : ${className}VO ): void
		{	
 			var intercept:Callbacks   = new Callbacks(result,this.onFault);
	        var token	 :AsyncToken = service.update${className}(${table.classNameFirstLower}VO);
	        prepareHandlers(token,intercept); 
		}
		public function delete${className}( ${table.classNameFirstLower}VO : ${className}VO ): void
		{
 			var intercept:Callbacks   = new Callbacks(result,this.onFault);
	        var token	 :AsyncToken = service.delete${className}(${table.classNameFirstLower}VO.id);
	        prepareHandlers(token,intercept); 
		}
		public function load${className}(): void
		{
 			var intercept:Callbacks   = new Callbacks(result,this.onFault);
	        var token	 :AsyncToken = service.get${className}s();
	        prepareHandlers(token,intercept);
		}
		
		private function result(event:*):void {

        	notifyCaller(event.result);
        }
	}
}