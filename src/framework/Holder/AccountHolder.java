package framework.Holder;

public abstract class AccountHolder {

    private Address address;
    private String email;
    private String name;

    protected AccountHolder(Address address, String email, String name) {
        this.address = address;
        this.email = email;
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
