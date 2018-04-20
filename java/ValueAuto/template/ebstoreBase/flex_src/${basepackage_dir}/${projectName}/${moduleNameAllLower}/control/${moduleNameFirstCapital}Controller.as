<#include "/moduleAlias.include">
<#include "/java_copyright.include">

package ${commonPackage}.control
{
	import com.universalmind.cairngorm.control.FrontController;
	import ${commandXmlns}.*;
	
	public class ${moduleNameFirstCapital}Controller extends FrontController
	{
		public function ${moduleNameFirstCapital}Controller()
		{
			super();
			<#list tables as table>
			addCommand( ${moduleNameFirstCapital}Controller.EVENT_${table.classNameAllUpperCase}, ${table.className}Command );
			</#list>
		}
		<#list tables as table>
		public static const EVENT_${table.classNameAllUpperCase} : String = "${table.classNameFirstLower}Event";
		</#list>
		
	}
}