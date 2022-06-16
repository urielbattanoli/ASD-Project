# ASD-Project

#### Banking framework for bank app and credit card app.

## How to use:

We provide almost all concrete classes as a default option to use our framework, but you can create you own concrete classes implementing our interfaces and use them instead.

1. You need to extend from the BankFrm(banking version) or CardFrm(credit card version).
```java
public class Application extends BankFrm
```

2. You also need to override the getFactory method, returning a IStrategyFactory for each type of account.
```java
 @Override
 public IStrategyFactory getFactory(AccountType type) {
     return switch (type) {
         case SAVING -> SavingFactory.getInstance();
         case CHECKING -> CheckingFactory.getInstance();
     };
 }
```

3. We provide a hook method to add new buttons to the screen also a hook for the listener
```java
 @Override
 public void addPanelHook(JPanel panel) {}
 @Override
 public void addListenerHook(SymAction action) {}
```

4. If you want to add an observer, you need to implement the IObserver and use the getService() method to use the service as your subject.
```java
 public class BankingObserver implements IObserver<IAccount>
```
```java
 private void setup() {
     new BankingObserver(getService());
 }
```

5. If you want to change the format for the accounts report, you need to create a class that implements the IGeneratorReport and use the setGenerator() method for the service.
```java
 public class CreditCardGenerator implements IReportGenerator
```
```java
 private void setup() {
     service.setGenerator(new CreditCardGenerator());
 }
```
