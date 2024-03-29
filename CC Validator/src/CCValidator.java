import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CCValidator
	{
		public static void main(String[] args)
			{

				int totalValid = 0;
				int lineNumber = 0;

				try
					{
						Scanner file = new Scanner(new File("CCNumbers.txt"));

						while (file.hasNextLong())
							{
								lineNumber++;

								long ccn = file.nextLong();
								if (luhnCheck(ccn))
									{

										System.out.println(lineNumber + "::" + ccn);
										totalValid++;
									}

							}
					} catch (FileNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println("There are " + totalValid + " valid CC Numbers.");

				// System.out.println(luhnCheck((long) 5424180123456789.));
				// luhnCheck((long) 5424180123456789.);

			}

		private static boolean luhnCheck(long ccn)
			{
				int cardLength = 16;

				// strip into an array
				Integer[] digits = new Integer[cardLength];
				for (int i = cardLength - 1; i >= 0; i--)
					{
						digits[i] = (int) (ccn % 10);
						ccn /= 10;

					}

				// double every other + add double digits
				for (int i = 0; i < cardLength; i += 2)
					{
						int d = digits[i] * 2;

						if (d > 9)
							{
								d = (d % 10) + (d / 10);
							}
						digits[i] = d;
					}

				// add all the digits together
				int total = 0;
				for (Integer n : digits)
					{
						total += n;
					}

				// return whether it is divisible by 10
				return total % 10 == 0;

			}
	}
