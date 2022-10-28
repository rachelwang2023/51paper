using System;
using System.Text.RegularExpressions;
namespace SampleOne
{
    class Program
    {
        static void Main(string[] args)
        {
            /*
            Console.WriteLine("Please type a vehicle registration number for query, use ? for missing digit.");
            string queryVRN = SectionA.VerifiedInputVRNString();
            Console.WriteLine(queryVRN);
            int missingDigit = SectionA.GetMissingVRNDigit(queryVRN);

            if(missingDigit > -1)
            {
                Console.WriteLine("This is the complete vehicle registration number:");
                SectionA.PrintCompleteVRN(queryVRN, missingDigit);
            }
            else
            {
                Console.WriteLine("No valid vehicle registration number for above combination.");
            }
            Console.ReadKey();
            */

            Cylinder cylinder = new Cylinder(1, 2);
            Console.WriteLine(cylinder);


            try
            {
                Console.WriteLine("Input a number(>=0):");
                int num = Convert.ToInt32(Console.ReadLine());
                TextException.Method3(num);
                TextException.Method4(num);


            }
            catch (MyException e)
            {
                Console.WriteLine(e.Message);
            }
            catch (DivideByZeroException e)
            {
                Console.WriteLine(e.Message);
            }
            catch (FormatException e)
            {
                Console.WriteLine(e.Message);

            }
            catch(IndexOutOfRangeException e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
    
}