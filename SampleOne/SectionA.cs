using System;
using System.Text;
using System.Text.RegularExpressions;

namespace SampleOne
{
    public class SectionA
    {
  
        private static int[] vrnMultipliers =
        {
            9,4,5,4,3,2
        };
        private static char[] checksumArray =
        {
            'A','Z','Y','X','U','T','S','R','P','M','L','K','J','H','G','E','D','C','B'
        };

        public static string VerifiedInputVRNString()
        {

            string? res = Console.ReadLine();
            if (String.IsNullOrEmpty(res))
                throw new ArgumentNullException("Input nothing.");
            res = res.Trim().ToUpper();

            // start with one S and two letter
            Regex rgx1 = new Regex("^S[A-Z]{2}.{4}[A-Z]$");
            Regex rgx2 = new Regex("/?");
            if (!rgx1.IsMatch(res)||!rgx2.IsMatch(res))
            {
                throw new Exception("Not right pattern");
            }
            return res;
        }

        public static int ConvertVRNCharacterIntoNumber(char c,int index)
        {
            if (Char.IsLetter(c))
            {
                return (c - 64) * vrnMultipliers[index-1];
                
            }
            else if(Char.IsDigit(c))
            {
                return (int)Char.GetNumericValue(c) * vrnMultipliers[index - 1];

            }
            else
            {
                throw new Exception("Wrong pattern");
            }
        }

        public static int GetMissingVRNDigit(string queryVRN)
        {
            int sumWithoutMissingDigit = 0;
            int missingDigitIndex = 0;
            for (int i = 1; i < 7; i++)
            {
                char currentCharacter = queryVRN[i];
                if (currentCharacter != '?')
                {
                    sumWithoutMissingDigit += ConvertVRNCharacterIntoNumber(currentCharacter, i);
                }
                else
                {
                    missingDigitIndex = i;
                }
            }
            int remainderForCheckSUm = GetChecksumRepresentedRemainder(queryVRN[7]);
            if(remainderForCheckSUm > -1)
            {
                return FindMissingDigitAtIndex(missingDigitIndex,sumWithoutMissingDigit,remainderForCheckSUm);
            }
            return -1;
            
        }

        public static int GetChecksumRepresentedRemainder(char c)
        {
            return checksumArray.Contains(c) ? Array.IndexOf(checksumArray, c): -1;
        }

        public static int FindMissingDigitAtIndex(int index, int partialSum, int remainder)
        {
            for (int i = 0; i < 10; i++)
            {
                if ((i * vrnMultipliers[index - 1] + partialSum) % 19 == remainder)
                {
                    return i;
                }
            }
            return -1;
        }

        public static void PrintCompleteVRN(string s, int n)
        {
            Console.WriteLine(s.Replace("?", n.ToString()));
        }
    }
}

