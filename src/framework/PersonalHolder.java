package framework;

public class PersonalHolder extends AccountHolder {

    private String birthDate;

    public PersonalHolder(Address address, String email, String name, String birthDate) {
        super(address, email, name);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
