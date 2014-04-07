package basis;


public class HelloWorld
{
	public static String getMessage(int n)
	{
		StringBuffer buff  = new StringBuffer();
		
		for(int i = 0; i < n; i++)
		{
			buff.append("Hello World\n");
		}
		
		return buff.toString();
	}
}
