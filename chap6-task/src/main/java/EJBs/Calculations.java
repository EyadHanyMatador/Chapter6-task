package EJBs;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity 
public class Calculations {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int number1;
    private int number2;
    private String operation;
    
    
    public Calculations() {
    }

    public Calculations(int num1, int num2, String operation) {
        this.number1 = num1;
        this.number2 = num2;
        setOperation(operation);
    }
    
    
    public void setNumber1(int number1) {
    	this.number1 = number1;
    }
    
    public void setNumber2(int number2) {
    	this.number2 = number2;
    }
    
    public int getNumber1() 
    {
    	
    	return this.number1;
    }
    public int getNumber2() 
    {
    	
    	return this.number2;
    }
       
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getOperation()
    {
    	return this.operation;
    }
    
    
    public int calculate() {
        int result = 0;

        if (operation == null) {
            return result;
        }

        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 != 0) {
                    return number1 / number2;
                } else {
                    System.out.println("Cannot divide by zero");
                    return 0;
                }
            default:
                System.out.println("Invalid operation");
        }

        return result;
    }

    

    
   
    //this function from video in chapter 6, this function is responsible for 
    //persisting the current Calculations object to the database using JPA
    void saveOperations()
    {
       EntityManagerFactory EMF = Persistence.createEntityManagerFactory("persistence");
       EntityManager EM = EMF.createEntityManager();

       try
       {
           EM.getTransaction().begin();
           EM.persist(this);
           EM.getTransaction().commit();
       }
       catch(Exception x)
       {
           EM.getTransaction().rollback();
       }
       EM.close();
    }

     
    

}

