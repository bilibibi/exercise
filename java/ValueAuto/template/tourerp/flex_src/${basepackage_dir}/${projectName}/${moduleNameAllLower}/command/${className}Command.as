<#include "/tableAlias.include">
<#include "/copyright.include">
package ${commandXmlns}
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.universalmind.cairngorm.commands.Command;
	import ${businessXmlns}.${className}Delegate;
	import ${voXmlns}.${className}VO;
	import com.youpeng.tourerp.core.vo.TourerpBaseVO;
	import com.youpeng.tourerp.core.event.OperationEvent;
	public class ${className}Command extends Command
	{
		override public function execute( event : CairngormEvent ) : void
		{
            super.execute(event);
			var operationEvent : OperationEvent = OperationEvent( event );  
			var delegate : ${className}Delegate = new ${className}Delegate(operationEvent.callbacks);
			var baseVO:TourerpBaseVO = TourerpBaseVO(operationEvent.param);
			switch(baseVO.optionType)  
            {  
                case TourerpBaseVO.GUI_ACTION_SINGLE_ADDED:  
                     delegate.add${className}(${className}VO(operationEvent.param));
                     break;  
                case TourerpBaseVO.GUI_ACTION_SINGLE_UPDATED:  
		             delegate.update${className}(${className}VO(operationEvent.param));
                     break;  
                case TourerpBaseVO.GUI_ACTION_SINGLE_DELETED:  
                	 delegate.delete${className}(${className}VO(operationEvent.param));
                     break;  
                case TourerpBaseVO.GUI_ACTION_LOADED:  
                	 delegate.load${className}();
                     break;  
	            default:  
	            break;  
            } 
		}
	}
}