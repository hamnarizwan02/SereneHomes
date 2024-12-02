package businessLogicLayer;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class Customer extends Users
{	
    public Customer(String name, String email, String password, String phoneNumber) {
        super(name, email, password, phoneNumber) ;
    }
}
