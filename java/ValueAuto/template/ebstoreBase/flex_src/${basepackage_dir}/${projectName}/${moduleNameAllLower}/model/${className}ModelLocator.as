<#include "/tableAlias.include">
<#include "/copyright.include">
 package ${modelXmlns}
{	
	import com.adobe.cairngorm.model.IModelLocator;
	import ${voXmlns}.${className}VO;
	import mx.collections.ArrayCollection;
	[Bindable]
	public class ${className}ModelLocator implements IModelLocator
	{
	    private static var modelLocator:${className}ModelLocator = null;
	    
	    public static function getInstance():${className}ModelLocator
	    {
	    	if ( modelLocator == null )
	      	{
	      		modelLocator = new ${className}ModelLocator();
	      	}
	      	return modelLocator;
	    }
	    
		public function ${className}ModelLocator()
		{
			if (${className}ModelLocator.modelLocator != null)
			{
				throw new Error("Only one BrokerModelLocator instance should be instantiated");
			}
		}
		
		public var ${classNameLower}List : ArrayCollection;
		public var ${classNameLower}:${className}VO;

	}
}