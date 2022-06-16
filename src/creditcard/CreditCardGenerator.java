package creditcard;

import framework.Account;
import framework.Entry;
import framework.IAccount;
import framework.ReportGenerator.IReportGenerator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CreditCardGenerator implements IReportGenerator {

    public String generateReport(List<IAccount> list) {
        String report="";
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        for(IAccount a : list){
            double previousBalance=a.getEntryList()
                    .stream()
                    //.filter(ac->ac.getDate().before(date))
                    .reduce(0.0, (subtotal, entry) -> subtotal + entry.getAmount(), Double::sum);

            double totalCharges=a.getEntryList()
                    .stream()
                    .filter(ac->ac.getAmount()<0)
                    .reduce(0.0, (subtotal, entry) -> subtotal + entry.getAmount(), Double::sum);
            double totalCredit=a.getEntryList()
                    .stream()
                    .filter(ac->ac.getAmount()>0)
                    .reduce(0.0, (subtotal, entry) -> subtotal + entry.getAmount(), Double::sum);

            double MI=a.getMonthlyInterest();
            double MP=a.getMinimumPayment();
            double newBalance=previousBalance-totalCredit+totalCharges+MI*(previousBalance-totalCredit);
            double totalDue=MP*newBalance;

            report+="\n"+a.getId()+" Billing Report ("+LocalDate.now().getMonth()+"-"+LocalDate.now().getYear()+")";
            report+="\n--------------------------------------------------";
            for(Entry e: a.getEntryList()){
                report+="\n"+e.getDate()+" - "+e.getDescription()+" "+e.getAmount();
            }
            report+="\n--------------------------------------------------";
            report+="\nTotal charges: "+totalCharges;
            report+="\nTotal credits: "+totalCredit;
            report+="\nPrevious Balance: "+previousBalance;
            report+="\nNew Balance: "+newBalance;
            report+="\nTotal Due: "+totalDue;
            report+="\n==================================================";
        }
        System.out.println(report);
        return report;
    }
}
