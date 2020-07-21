package com.wipro.ccbill.entity;
import java.util.Date;

import com.wipro.ccbill.exception.InputValidationException;
public class CreditCardBill {
	private String creditCardNo;
	private String customerId;
	private Date billDate;
	private Transaction monthTransactions[];

	public CreditCardBill () {}

	public CreditCardBill (String creditCardNo, String customerId, Date billDate, Transaction monthTransactions[])
	{
		this.creditCardNo=creditCardNo;
		this.customerId=customerId;
		this.billDate= billDate;
		this.monthTransactions=monthTransactions;
	}
	public double getTotalAmount(String transactionType)
	{
	   double totalAmount=0.0;
	   for(int i=0;i<monthTransactions.length;i++)
	   {
		   if(monthTransactions[i].getTransactionType().equals(transactionType))
		   {
			   totalAmount+=monthTransactions[i].getTransactionAmount();
		   }
	   }
	   return totalAmount;
	}
	public double calculateBillAmount()
	{
		try
		{
		   if(validateData().equals("valid")) 
		   {
			   if(monthTransactions==null || monthTransactions.length==0)
				   return 0.0;
			   else
			   {
				  double outStandingAmount=getTotalAmount("DB")-getTotalAmount("CR");
				  double interestCalculated=outStandingAmount*0.199/12.0;
				  return outStandingAmount+interestCalculated;
			   }
		   }
			   
			
		}catch(InputValidationException e)
		{
			return 0.0;
		}
	  return 0.0;
	}
	public String validateData() throws InputValidationException
	{
		if(creditCardNo==null || creditCardNo.length()!=16 || customerId==null || customerId.length()<6)
		         throw new InputValidationException();
		return "valid";
	}
	
}
