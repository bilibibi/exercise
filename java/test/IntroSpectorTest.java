import java.beans.*;
import java.lang.reflect.Method;

/**
* 内省
*/
class IntroSpectorTest {
	public static void main(String[] args) throws Exception{
		TestVO test = new TestVO(2,5,"F");
		
		String propertyName = "y";
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, test.getClass());
		Method methodGetX = pd.getReadMethod();
		Object retVal = methodGetX.invoke(test);
		System.out.println(retVal);
		
		Method methodSetX=pd.getWriteMethod();
		methodSetX.invoke(test, 3);
		System.out.println(test.getX());
		
		BeanInfo beanInfo = Introspector.getBeanInfo(test.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd1 : pds){
			System.out.println(pd1.getName());
			if(pd1.getName().equals(propertyName)){
				methodGetX = pd1.getReadMethod();
				retVal = methodGetX.invoke(test);
				System.out.println(retVal);
				break;
			}
		}
	}
}