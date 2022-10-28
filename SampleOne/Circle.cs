using System;
namespace SampleOne
{
    public class Circle
    {
        protected double radius;
        public Circle(double radius)
        {
            this.radius = radius;
        }
        public double Area()
        {
            return Math.PI * radius * radius;
        }
    }

    public class Cylinder : Circle
    {
        private double height;
        public Cylinder(double radius,double height) : base(radius)
        {
            this.height = height;
        }
        public double Volume()
        {
            return base.Area()*height;
        }
        public override string ToString()
        => $"radius: {radius}, height: {height}, area: {this.Area()}, volume:{this.Volume()}";
    }
}

