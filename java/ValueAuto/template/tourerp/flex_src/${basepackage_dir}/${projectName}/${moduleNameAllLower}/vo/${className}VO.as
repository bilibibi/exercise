<#include "/tableAlias.include">
<#include "/copyright.include">
package ${voXmlns}{
	import com.adobe.cairngorm.vo.IValueObject;
	import com.youpeng.tourerp.core.vo.TourerpBaseVO;;
	<#list table.oneToMany as many>
	import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
	</#list>
	[RemoteClass(alias="${voXmlns}.${className}VO")]
	[Bindable]
	public class ${className}VO  extends TourerpBaseVO implements IValueObject{
		<#list table.fields as field>
		/**
		 * ${field.columnComment}
		 */
		public var ${field.javaNameLower}:${field.flexType};
		</#list>
		<#list table.oneToMany as many>
		private var ${many.tableNameFirstLower}List:Array;
		</#list>
		public function ${className}VO()
		{
		}
		public function equals(object:*):Boolean
		{
			if (object != null && object is ${className}VO) {
				if (id == (object as ${className}VO).id) 
				{
					return true;
				}
			}
			return false;
		}
		public function copyFrom(src:*):*{  
		}  
		public function clone():*{  
			return new ${className}VO();  
		} 
	}
}