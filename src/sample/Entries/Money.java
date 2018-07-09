package sample.Entries;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Money {

    private ObservableList<Income> list;

    public Money(ObservableList<Income> list) {
        this.list = list;
    }

    public ObservableList<Income> getList() {
        return list;
    }

    public void setList(ObservableList<Income> list) {
        this.list = list;
    }

    public ArrayList<Double> getIncome() {
        double sumRub = 0.0;
        double sumUah = 0.0;
        double sumUsd = 0.0;
        double sumEur = 0.0;

        for (Income income : list) {
            if(income.isPositive()) {
                switch (income.getCurrency()) {
                    case RUB:
                        sumRub += income.getSum();
                        break;
                    case UAH:
                        sumUah += income.getSum();
                        break;
                    case USD:
                        sumUsd += income.getSum();
                        break;
                    case EUR:
                        sumEur += income.getSum();
                        break;
                    case UNKNOWN:
                        break;
                }
            } else {
                switch (income.getCurrency()) {
                    case RUB:
                        sumRub -= income.getSum();
                        break;
                    case UAH:
                        sumUah -= income.getSum();
                        break;
                    case USD:
                        sumUsd -= income.getSum();
                        break;
                    case EUR:
                        sumEur -= income.getSum();
                        break;
                    case UNKNOWN:
                        break;
                }
            }
        }

        ArrayList<Double> arrayList = new ArrayList<Double>();
        arrayList.add(sumRub);
        arrayList.add(sumUah);
        arrayList.add(sumUsd);
        arrayList.add(sumEur);

        return arrayList;
    }


}
