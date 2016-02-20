
import java.util.LinkedList;
import java.util.Random;


public class Brain {
 
    public int neuronAmmount;
    public int inputAmmount;
    public int hid1Ammount;
    public int hid2Ammount;
    public int outputAmmount;
    boolean firstGeneration = true;
    public LinkedList<Neuron> neurons = new LinkedList<Neuron>();
    
    public Brain(int inputAmmount, int hid1Ammount, int hid2Ammount,int outputAmmount,boolean firstGeneration) {
    
        this.inputAmmount = inputAmmount;
        this.hid1Ammount = hid1Ammount;
        this.hid2Ammount = hid2Ammount;
        this.outputAmmount = outputAmmount;
        this.firstGeneration = firstGeneration;
        
        neuronAmmount = inputAmmount + hid1Ammount + hid2Ammount + outputAmmount;
        
        if(firstGeneration == true) {
        for(int i = 0; i<neuronAmmount; i++) {
        
            if(i < inputAmmount) {
            
                Neuron tempNeuron = new Neuron();
                neurons.add(tempNeuron);
            
            } else if(i >= inputAmmount && i < inputAmmount + hid1Ammount) {
            
                Random rand = new Random();
                
                Neuron tempNeuron = new Neuron(rand.nextInt(3),rand.nextInt(inputAmmount),rand.nextInt(inputAmmount));
                
                if(rand.nextInt(100) < 10) {
                
                    tempNeuron.input1 = 1337;
                    if(rand.nextInt(100) < 10) {
                    
                        tempNeuron.input2 = 1337;
                    
                    }
                }
                neurons.add(tempNeuron);
            
            } else if(i >= hid1Ammount && i < inputAmmount + hid1Ammount + hid2Ammount) {
                Random rand = new Random();
                Neuron tempNeuron = new Neuron(rand.nextInt(3),rand.nextInt(hid1Ammount) + inputAmmount,rand.nextInt(hid1Ammount) + inputAmmount);
                
                        if(rand.nextInt(100) < 10) {
                
                    tempNeuron.input1 = 1337;
                    if(rand.nextInt(100) < 10) {
                    
                        tempNeuron.input2 = 1337;
                    
                    }
                }
                neurons.add(tempNeuron);
                
            } else if(i >= inputAmmount + hid1Ammount +hid2Ammount) {
                Random rand = new Random();

                Neuron tempNeuron = new Neuron(rand.nextInt(3),rand.nextInt(hid2Ammount) + inputAmmount + hid1Ammount,rand.nextInt(hid2Ammount) + inputAmmount + hid1Ammount);
                 if(rand.nextInt(100) < 10) {
                
                    tempNeuron.input1 = 1337;
                    if(rand.nextInt(100) < 10) {
                    
                        tempNeuron.input2 = 1337;
                    
                    }
                }
                neurons.add(tempNeuron);
            }
            
        }
        }
    
    }
    
    public void updateNeurons() {
    
        for(int i = 0; i < neuronAmmount; i++) {
            Neuron tempNeuron = neurons.get(i);
            if(i >= inputAmmount ) {
                
                if(tempNeuron.input1 == 1337 && tempNeuron.input2 == 1337) {
                
                    tempNeuron.value = 0;
                    
                } 
           
                else if(tempNeuron.input1 == 1337) {
                
                    tempNeuron.value = neurons.get(tempNeuron.input2).value;
                    
                } else if(tempNeuron.input2 == 1337) {
                     tempNeuron.value = neurons.get(tempNeuron.input1).value;
                } else {
            
                    switch(tempNeuron.mode) {
                
                        case 0:
                            tempNeuron.value = neurons.get(tempNeuron.input1).value * neurons.get(tempNeuron.input2).value;
                            if(neurons.get(tempNeuron.input1).value * neurons.get(tempNeuron.input2).value > 100) {
                            
                                tempNeuron.value = 100;
                            } else if(neurons.get(tempNeuron.input1).value * neurons.get(tempNeuron.input2).value < -100) {
                            
                                tempNeuron.value = 100;
                            
                            }
                        break;
                    
                        case 1:
                            tempNeuron.value = neurons.get(tempNeuron.input1).value + neurons.get(tempNeuron.input2).value;
                              if(neurons.get(tempNeuron.input1).value + neurons.get(tempNeuron.input2).value > 100) {
                            
                                tempNeuron.value = 100;
                            } else if(neurons.get(tempNeuron.input1).value + neurons.get(tempNeuron.input2).value < -100) {
                            
                                tempNeuron.value = 100;
                            
                            }
                        break;
                        
                        case 2:
                            tempNeuron.value = neurons.get(tempNeuron.input1).value - neurons.get(tempNeuron.input2).value;
                              if(neurons.get(tempNeuron.input1).value - neurons.get(tempNeuron.input2).value > 100) {
                            
                                tempNeuron.value = 100;
                            } else if(neurons.get(tempNeuron.input1).value - neurons.get(tempNeuron.input2).value < -100) {
                            
                                tempNeuron.value = 100;
                            
                            }
                        break;
                        
                        default:
                        
                       break;
                    }
                    
                }
            
            }
        
        }
    
    
    }
    
     public void mutate(int procent) {
    
        Random rand = new Random();
        
        for(int i = 0; i < neuronAmmount; i++) {
            if(rand.nextInt(100) <= procent) {
            
                Neuron tempNeuron = neurons.get(i);
                tempNeuron.mode = rand.nextInt(3);
                  
            }
            int input1or2 = rand.nextInt(2);
            if(rand.nextInt(100)< procent + 1){
                 if(i >= inputAmmount && i < inputAmmount + hid1Ammount) {
            
                    Neuron tempNeuron = neurons.get(i);
                    if(input1or2 == 0) {
                        tempNeuron.input1 = rand.nextInt(inputAmmount);
                    } else {
                    
                        tempNeuron.input2 = rand.nextInt(inputAmmount);

                    }
                    if(rand.nextInt(100) < 1 + procent) {
                    
                        if(input1or2 == 0) {
                        
                            tempNeuron.input1 = 1337;
                            
                        } else {
                        
                            tempNeuron.input2 = 1337;
                        
                        }
                    
                    }
            
                } else if(i >= hid1Ammount && i < inputAmmount + hid1Ammount + hid2Ammount) {
                
                    Neuron tempNeuron = neurons.get(i);
                    if(input1or2 == 0) {
                        tempNeuron.input1 = rand.nextInt(hid1Ammount) + inputAmmount;
                    } else {
                    
                        tempNeuron.input2 = rand.nextInt(hid1Ammount) + inputAmmount;
                        
                    }
                        if(rand.nextInt(100) < procent + 1) {
                    
                        if(input1or2 == 0) {
                        
                            tempNeuron.input1 = 1337;
                            
                        } else {
                        
                            tempNeuron.input1 = 1337;
                        
                        }
                    
                    }
                
                } else if(i >= inputAmmount + hid1Ammount + hid2Ammount) {

                    Neuron tempNeuron = neurons.get(i);
                    if(input1or2 == 0) {
                        tempNeuron.input1 = rand.nextInt(hid2Ammount) + inputAmmount + hid1Ammount;
                    }else if(input1or2 == 1) {
                    
                        tempNeuron.input2 = rand.nextInt(hid2Ammount) + inputAmmount + hid1Ammount;

                        
                    }
                        if(rand.nextInt(100) < procent + 1) {
                    
                        if(input1or2 == 0) {
                        
                            tempNeuron.input1 = 1337;
                            
                        } else {
                        
                            tempNeuron.input1 = 1337;
                        
                        }
                    
                    }
                }
                
            }
            
        }
    
    }
     
     
     public Brain cloneBrain(Brain brain) {
     
         Brain newBrain = new Brain(brain.inputAmmount,brain.hid1Ammount,brain.hid2Ammount,brain.outputAmmount, false);
         
         for(int i = 0; i < brain.neuronAmmount; i++) {
             
             Neuron tempNeuron = brain.neurons.get(i);
             
             Neuron newNeuron = new Neuron();

             for(int n = 0; n<brain.neuronAmmount; n++) {
                                   //System.out.println("hej");

                 if(n == tempNeuron.input1) {
                 
                     newNeuron.input1 = n;
                 
                 }
                  if(n == tempNeuron.input2) {
                 
                     newNeuron.input2 = n;
                 
                 }
                  if(n == tempNeuron.mode) {
                 
                     newNeuron.mode = n;
                 
                 }
                 newNeuron.value = 0;
             
             }
            
             newBrain.neurons.add(newNeuron);
            
         }

         
         
         
        LinkedList<LinkedList<Integer>> list = new  LinkedList<LinkedList<Integer>>();
        for(int i = 0; i < brain.neuronAmmount; i++) {
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(brain.neurons.get(i).mode);
            list2.add(brain.neurons.get(i).input1);
            list2.add(brain.neurons.get(i).input2);
            list2.add(brain.neurons.get(i).value);

            list.add(list2);
        }
         //System.out.println(list);
          LinkedList<LinkedList<Integer>> list3 = new  LinkedList<LinkedList<Integer>>();
        for(int i = 0; i < newBrain.neuronAmmount; i++) {
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(newBrain.neurons.get(i).mode);
            list2.add(newBrain.neurons.get(i).input1);
            list2.add(newBrain.neurons.get(i).input2);
            list2.add(newBrain.neurons.get(i).value);

            list3.add(list2);
        }
      //   System.out.println(list3);
         newBrain.mutate(10);
         return newBrain;
     
     }
     
}
