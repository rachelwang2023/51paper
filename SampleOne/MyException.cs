using System;
namespace SampleOne
{
    public class MyException:Exception
    {
        public MyException() : base() { }
        public MyException(string message):base(message)
        {
        }
    }

    public static class TextException
    {
        public static void Method1(int num)
        {
            Console.WriteLine(100 / num);
        }

        public static void Method2()
        {
            throw new NullReferenceException();
        }

        public static void Method3(int num)
        {
            Method1(num);
            int[] arr = { 1, 2, 3, 4 };
            for(int i = 0; i < num; i++)
            {
                Console.WriteLine(arr[i]);
            }
        }
        public static void Method4(int num)
        {
            if (num < 0)
            {
                throw new MyException("Number cannot be negative");
            }
            Console.WriteLine(num);
        }
    }
}

