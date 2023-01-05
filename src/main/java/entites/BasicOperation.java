package entites;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BasicOperation implements MyOperation{
    PLUS("+","加法"){
        @Override
        public Double operation(Double x, Double y) {
            return x+y;
        }
    },
    MINUS("-","减法"){
        @Override
        public Double operation(Double x, Double y) {
            return x-y;
        }
    };
    @Getter
    private String symbol;
    @Getter
    private String name;

    public void whateverMethod(){
        System.out.println(123);
    }
    public static void main(String[] args) {
        System.out.println(BasicOperation.PLUS.operation(1.0,2.0));
        System.out.println(BasicOperation.MINUS.operation(2.0,1.0));
        System.out.println(BasicOperation.valueOf("PLUS").operation(2.0,2.0));
        BasicOperation.PLUS.whateverMethod();
    }

}
