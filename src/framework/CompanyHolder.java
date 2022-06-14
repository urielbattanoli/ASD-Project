package framework;

public class CompanyHolder extends AccountHolder{

    private int employeesNumber;

    public CompanyHolder(Address address, String email, String name, int employeesNumber) {
        super(address, email, name);
        this.employeesNumber = employeesNumber;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}
