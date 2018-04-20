<#include "/tableAlias.include">
<#include "/copyright.include">
package ${commandXmlns}
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.universalmind.cairngorm.commands.Command;
	import ${businessXmlns}.${className}Delegate;
	import ${voXmlns}.${className}VO;
	import ${frameworkXmlns}.BaseVO;
	import ${commonXmlns}.event.OperationEvent;
	public class ${className}Command extends Command
	{
		override public function execute( event : CairngormEvent ) : void
		{
            super.execute(event);
			var operationEvent : OperationEvent = OperationEvent( event );  
			var delegate : ${className}Delegate = new ${className}Delegate(operationEvent.callbacks);
			var baseVO:BaseVO = BaseVO(operationEvent.param);
			switch(baseVO.optionType)  
            {  
               case BaseVO.GUI_ACTION_SINGLE_ADDED:  
                     delegate.add${className}(${className}VO(operationEvent.param));
                     break;  
                case BaseVO.GUI_ACTION_SINGLE_UPDATED:  
		             delegate.update${className}(${className}VO(operationEvent.param));
                     break;  
                case BaseVO.GUI_ACTION_SINGLE_DELETED:  
                	 delegate.delete${className}(${className}VO(operationEvent.param));
                     break;  
                case BaseVO.GUI_ACTION_LOADED:  
                	 delegate.load${className}();
                     break;  
	            default:  
	            break;  
            } 
		}
	}
}