package assertions.helpers;

public enum Operation {
    PLUS {
        public double eval(double x, double y) { return x + y; }
    },
    MINUS {
        public double eval(double x, double y) { return x - y; }
    },
    TIMES {
        public double eval(double x, double y) { return x * y; }
    },
    DIVIDED_BY {
        public double eval(double x, double y) {
            //simplest epsilon check
            if(Math.abs(y) < 1e-12) throw new ArithmeticException("division by zero is not allowed!");
            return x / y; }
    },
    IS_MULTIPLE{
        @Override
        public double eval(double x, double y) {
            if (x % y == 0) {
                return x % y;
            }
            return 0;
        }
    };

    /* Each constant in Operation ENUM must support an arithmetic operation */
    public abstract double eval(double x, double y);
}
