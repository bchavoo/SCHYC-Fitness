package writer;

import java.util.ArrayList;
//import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entitites.Address;
import entitites.Invoice;
import entitites.InvoiceProducts;
import entitites.Member;
import entitites.Person;
import product.DayMemberships;
import product.Equipment;
import product.Membership;
import product.ParkingPass;
import product.Product;
import product.Service;
import product.YearMemberships;
import reader.Calculations;
import reader.FileReader;

public class InvoiceWriterCALC {


	public static void createInvoiceReport(List<Invoice> invoiceList)  {
		//Do all calculations and formatting here
		int i = 0;
		ArrayList<List<Calculations>> fullArray = new ArrayList<List<Calculations>>();

		while(i < invoiceList.size()) {
			String invoiceNumber = invoiceList.get(i).getInvoiceCode();
			String trainerLastName = invoiceList.get(i).getPersonalTrainerCode().getLastName();
			String trainerFirstName = invoiceList.get(i).getPersonalTrainerCode().getFirstName();
			Member temp = invoiceList.get(i).getMemberCode();
			String memberName = temp.getName();
			String memberCode = temp.getMemberCode();
			String memberType = temp.getMemberType();
			if(memberType.equals("G")) {
				memberType = "General";
			} else if (memberType.equals("S")) {
				memberType = "Student";
			}
			String personLastName = invoiceList.get(i).getMemberCode().contact.getLastName();
			String personFirstName = invoiceList.get(i).getMemberCode().contact.getFirstName();

			Address memberAddress = invoiceList.get(i).getMemberCode().getAddress();

			List<InvoiceProducts> productList = invoiceList.get(i).getProductsList();


			List<Calculations> calcList = InvoiceWriterCALC.calculateTotals(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
			fullArray.add(calcList);
			i++;
		}

		InvoiceWriterCALC.createExcutiveReport(invoiceList, fullArray);

	}




	public static void createExcutiveReport(List<Invoice> invoiceList, ArrayList<List<Calculations>> fullArray) {

		System.out.println("Executive Summary Report");
		System.out.println("===========================");
		System.out.println("Invoice   Member                                            Personal Trainer                 Subtotal        Fees       Taxes    Discount       Total");

		double[]totalArray = new double[fullArray.size()]; 


		for(int i = 0; i < fullArray.size(); i++) {
			totalArray[i] = fullArray.get(i).get(0).getSubTotal();
			totalArray[i] = fullArray.get(i).get(0).getStudentFees();
			totalArray[i] = fullArray.get(i).get(0).getTaxes();
			totalArray[i] = fullArray.get(i).get(0).getDiscount();
			totalArray[i] = fullArray.get(i).get(0).getFinalTotal();
		}

		for(int i = 0; i < invoiceList.size(); i++){
			String fullName = invoiceList.get(i).getPersonalTrainerCode().getLastName() + ", " + invoiceList.get(i).getPersonalTrainerCode().getFirstName();
			String memberType = invoiceList.get(i).getMemberCode().getMemberType();
			String memberNameType = "";
			double subTotal = 0;
			double tax = invoiceList.get(i).getMemberCode().getTax();
			double studentFee = 0;

			if(memberType.equals("G")) {
				memberType = "General";
				memberNameType = invoiceList.get(i).getMemberCode().getName() + " [" + memberType + "] ";
				studentFee = 0;
			} else if (memberType.equals("S")) {
				memberType = "Student";
				memberNameType = invoiceList.get(i).getMemberCode().getName() + " [" + memberType + "] ";
				studentFee = 10.50;
			}




			System.out.printf("%-9s %-49s %-29s $%10.2f $%10.2f $%10.2f $%9.2f $%10.2f\n", invoiceList.get(i).getInvoiceCode(), memberNameType, fullName, fullArray.get(i).get(0).getSubTotal(), fullArray.get(i).get(0).getStudentFees(), fullArray.get(i).get(0).getTaxes(), fullArray.get(i).get(0).getDiscount(), fullArray.get(i).get(0).getFinalTotal());
		}
		System.out.println("========================================================================================================================================================");
		System.out.printf("%-89s $%10.2f $%10.2f $%10.2f $%9.2f $%10.2f", "TOTALS", 0.00, 0.00, 0.00, 0.00, 0.00);

	}




	public static List<Calculations> calculateTotals(String invoiceNumber, String trainerLastName, String trainerFirstName, String memberName, String memberCode, String memberType, String personLastName, String personFirstName, Address memberAddress, List<InvoiceProducts> productList) {


		List<Product> productFileList = FileReader.createProductList();


		String productCode = "";
		String productType = "";
		String personCode = "";
		String productName = "";
		DateTime startDate = null;
		DateTime endDate = null;
		String address = "";
		double quantity = 0;
		double costPerUnit = 0;
		double totalCost = 0;
		double tax = 0;
		double subTotal = 0;
		String YearMembershipFromInvoice = "";
		String DayMembershipFromInvoice = "";

		double YMSubTotal = 0;
		double DMSubTotal = 0;
		double PPSubTotal = 0;
		double RESubTotal = 0;

		double YMTaxes = 0;
		double DMTaxes = 0;
		double PPTaxes = 0;
		double RETaxes = 0;

		double YMTotal = 0;
		double DMTotal = 0;
		double PPTotal = 0;
		double RETotal = 0;


		for(int i = 0; i < productList.size(); i++) {
			for(int j = 0; j < productFileList.size(); j++) {
				quantity = productList.get(i).getQuantity();

				if(productList.get(i).getProductCode().equals(productFileList.get(j).getProductCode())) {

					productType = "";
					if(productFileList.get(j) instanceof YearMemberships) {
						YearMemberships yProduct = (YearMemberships)productFileList.get(j);
						if(yProduct.getProductType().equals("Y")) {
							productCode = yProduct.getProductCode();
							productType = "Year-long membership";
							productName = yProduct.membershipName;							
							startDate = yProduct.getStartDate();
							endDate = yProduct.getEndDate();
							address = yProduct.getAddress().getStreet();
							YearMembershipFromInvoice = yProduct.getProductCode();


							if (yProduct.getStartDate().getMonthOfYear() == 1) {
								costPerUnit = yProduct.getCost() * yProduct.getDiscount();	
								subTotal = yProduct.getSubTotal(costPerUnit, quantity);
								tax = yProduct.getTax(subTotal);
								totalCost = yProduct.getTotal(subTotal, tax);

							} else if (yProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = yProduct.getCost();
								subTotal = yProduct.getSubTotal(costPerUnit, quantity);
								tax = yProduct.getTax(subTotal);
								totalCost = yProduct.getTotal(subTotal, tax);
							}

						}

					} else if (productFileList.get(j) instanceof DayMemberships) {
						DayMemberships dProduct = (DayMemberships)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();



							if (dProduct.getStartDate().getMonthOfYear() == 1) {
								costPerUnit = dProduct.getCost() * dProduct.getDiscount();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);

							} else if (dProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = dProduct.getCost();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
							}



						}

					} else if (productFileList.get(j) instanceof ParkingPass) {
						ParkingPass pProduct = (ParkingPass)productFileList.get(j);
						if (pProduct.getProductType().equals("P")) {

							productCode = pProduct.getProductCode();
							productType = "Parking Pass";
							personCode = productList.get(i).getPersonCode();
							costPerUnit = pProduct.getCost();
							subTotal = pProduct.getSubTotal(costPerUnit, quantity);
							tax = pProduct.getTax(subTotal);
							totalCost = pProduct.getTotal(subTotal, tax);

						}

					} else if (productFileList.get(j) instanceof Equipment) {
						Equipment eProduct = (Equipment)productFileList.get(j);
						if (eProduct.getProductType().equals("R")) {
							productCode = eProduct.getProductCode();
							productType = "Rental Equipment";
							personCode = productList.get(i).getPersonCode();
							productName = eProduct.getEquipment();

							costPerUnit = eProduct.getCost();
							subTotal = eProduct.getSubTotal(costPerUnit, quantity);
							tax = eProduct.getTax(subTotal);
							totalCost = eProduct.getTotal(subTotal, tax);

						}
					}
				}





			}

			if(productType.equals("Year-long membership")) {

				if(startDate.getMonthOfYear() == 1) {

					YMSubTotal = subTotal;
					YMTaxes = tax;
					YMTotal = totalCost;

				} else if (startDate.getMonthOfYear() != 1) {

					YMSubTotal = subTotal;
					YMTaxes = tax;
					YMTotal = totalCost;
				}


			} else if (productType.equals("Day-long membership")) {

				if(startDate.getMonthOfYear() == 1) {

					DMSubTotal = subTotal;
					DMTaxes = tax;
					DMTotal = totalCost;
				}else if (startDate.getMonthOfYear() != 1){

					DMSubTotal = subTotal;
					DMTaxes = tax;
					DMTotal = totalCost;
				}

			} else if (productType.equals("Parking Pass")) {




				if(personCode.equals("")) {
					PPSubTotal = subTotal;
					PPTaxes = tax;
					PPTotal = totalCost;
				} else if (personCode.equals(DayMembershipFromInvoice)) {
					PPSubTotal = subTotal-(1*costPerUnit);
					PPTaxes = tax-(costPerUnit*0.04);
					PPTotal = totalCost-((costPerUnit) + ((costPerUnit)*0.04));
				} else if (personCode.equals(YearMembershipFromInvoice)) {
					if(quantity < 365) {
						PPSubTotal = subTotal-(quantity*costPerUnit);
						PPTaxes = tax-((quantity*costPerUnit)*0.04);
						PPTotal = totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
					} else if (quantity > 365) {
						PPSubTotal = subTotal-(quantity*costPerUnit);
						PPTaxes = tax-((quantity*costPerUnit)*0.04);
						PPTotal = totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
					}
				} 



			} else if (productType.equals("Rental Equipment")) {
				if(personCode.equals("")) {

					RESubTotal = subTotal;
					RETaxes = tax;
					RETotal = totalCost;
				} else {
					if(personCode.equals(YearMembershipFromInvoice)) {

						RESubTotal =  subTotal*0.95;
						RETaxes = (subTotal*0.95)*0.04;
						RETotal = (subTotal*0.95) + ((subTotal*0.95)*0.04);
					} else {

						RESubTotal = subTotal;
						RETaxes = tax;
						RETotal = totalCost;
					}
				}
			}
		}






		double allSubTotals = YMSubTotal + DMSubTotal + PPSubTotal + RESubTotal;
		double allTaxes = YMTaxes + DMTaxes + PPTaxes + RETaxes;

		double discount = 0;
		double additionalStudentFee = 0;
		if(memberType.equals("Student")) {
			discount = (((allSubTotals * 0.08) + allTaxes) * -1);
			additionalStudentFee = 10.50;
		}
		
		double allTotals = YMTotal + DMTotal + PPTotal + RETotal + discount + additionalStudentFee;


		ArrayList<Calculations> totalArray = new ArrayList<Calculations>();

		Calculations calc = new Calculations(allSubTotals, additionalStudentFee, allTaxes, discount, allTotals);

		totalArray.add(calc);

		//All totals are set back to zero
		YMSubTotal = 0;
		DMSubTotal = 0;
		PPSubTotal = 0;
		RESubTotal = 0;
		YMTaxes = 0;
		DMTaxes = 0;
		PPTaxes = 0;
		RETaxes = 0;
		YMTotal = 0;
		DMTotal = 0;
		PPTotal = 0;
		RETotal = 0;
		allSubTotals = 0;
		allTaxes = 0;
		allTotals = 0;

		return totalArray;

	}




}