package Exceptions;

public class SalaryNotSpecifiedException extends Exception{
    public SalaryNotSpecifiedException(String name){
        super("У пользователя " + name + " не указан месячный доход.");
    }
}
