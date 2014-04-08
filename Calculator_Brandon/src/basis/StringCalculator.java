package basis;

public class StringCalculator 
{
	public int add(String numbers)
	{
		String[] nums = numbers.split(",");
		return nums.length;
	}
}
