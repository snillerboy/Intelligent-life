public class Neuron {
    
    protected int mode = 0;
    protected int input1 = 0;
    protected int input2 = 0;
    protected int value = 2;

    //hidden && output
    public Neuron(int mode, int input1, int input2) {
        
        this.mode = mode;
        this.input1 = input1;
        this.input2 = input2;
       
        
    }
    //input
    public Neuron() {
    
       

    }
    
}
