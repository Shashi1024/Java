package Codes.Generics;

public class BoundedTypeParams {
    static class NumericBox<T extends Number>{
        private T val;

        public NumericBox(T v){
            this.val = v;
        }
        public T getVal(){
            return this.val;
        }
        public double doubleVal(){
            return this.val.doubleValue();
        }

    }

    public static void main(String[] args){
        NumericBox<Integer> nb = new NumericBox<>(10);
        System.out.println("Integer Box Value: " + nb.getVal() + ", Double Value: " + nb.doubleVal());
    }
}
