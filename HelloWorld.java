
public class HelloWorld {
	public String getMessage(int n)
	{
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i<n; i++)
		{
			buffer.append("HelloWorld");
		}
		return buffer.toString();
	}
}
