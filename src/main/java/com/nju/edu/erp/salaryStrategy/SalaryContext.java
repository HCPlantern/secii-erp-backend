package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.enums.SalaryCalculationMethod;
import com.nju.edu.erp.enums.SalaryPaymentMethod;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.Impl.AnnuallyStrategy;
import com.nju.edu.erp.salaryStrategy.Impl.DeductStrategy;
import com.nju.edu.erp.salaryStrategy.Impl.MonthlyStrategy;
import com.nju.edu.erp.salaryStrategy.Impl.PostStrategy;

import java.util.Date;

public class SalaryContext {
    private SalaryCalculationStrategy salaryCalculationStrategy;
    private SalaryPaymentStrategy salaryPaymentStrategy;

    public void setSalaryStrategy(SalaryCalculationMethod calculationMethod,
                                  SalaryPaymentMethod paymentMethod) {
        switch (calculationMethod) {
            case DEDUCT:
                this.salaryCalculationStrategy = new DeductStrategy();
            case POST:
                this.salaryCalculationStrategy = new PostStrategy();
        }
        switch (paymentMethod) {
            case MONTHLY:
                this.salaryPaymentStrategy = new MonthlyStrategy();
            case ANNUALLY:
                this.salaryPaymentStrategy = new AnnuallyStrategy();
        }
    }

    /**
     * 计算扣前工资
     *
     * @param salarySheetPO 需更写入的工资单
     * @param jobPO
     * @param beginDate
     * @param endDate
     */
    public void getSalary(SalarySheetPO salarySheetPO
            , Date beginDate, Date endDate) {
        //先计算薪资
        salaryCalculationStrategy.calculate(salarySheetPO, beginDate, endDate);
        //再根据发放方式考虑年终奖等
        salaryPaymentStrategy.calculate(salarySheetPO, beginDate, endDate);
    }
}
