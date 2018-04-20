<#include "/tableAlias.include">
<#include "/copyright.include">
package ${voXmlns}{
	import com.adobe.cairngorm.vo.IValueObject;
	import com.jiazi.flexframework.util.IComparable;
	<#list table.oneToMany as many>
	import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
	</#list>
	[RemoteClass(alias="${voXmlns}.${className}VO")]
	[Bindable]
	public class ${className}VO implements IValueObject, IComparable{
		//columns START
		<#list table.fields as field>
		public var ${field.javaNameLower}:${field.flexType};
		</#list>
		<#list table.oneToMany as many>
		private var ${many.tableNameFirstLower}List:Array;
		</#list>
		//columns END
		public function ${className}VO()
		{
		}
		public function equals(object:IComparable):Boolean
		{
			if (object != null && object is ${className}VO) {
				if (id == (object as ${className}VO).id) 
				{
					return true;
				}
			}
			return false;
		}
	}
}