using System;
namespace SampleOne
{
    public class Stock
    {
        private string symbol = String.Empty;
        private string name = String.Empty;
        private double previousPrice;
        private double closingPrice;
        public Stock(string s = "", string n = "", double p = 0,double c=0)
        {
            Symbol = s;
            Name = n;
            PreviousPrice = p;
            ClosingPrice = c;
        }
        public string Symbol
        {
            get => symbol;
            set => symbol = value;
        }
        public string Name
        {
            get => name;
            set => name = value;
        }
        public double PreviousPrice
        {
            get => previousPrice;
            set => previousPrice = value;
        }
        public double ClosingPrice
        {
            get => closingPrice;
            set => closingPrice = value;
        }
        public double ComputePercentGain()
        {
            return (ClosingPrice - PreviousPrice) / PreviousPrice * 100;
        }
    }
}

