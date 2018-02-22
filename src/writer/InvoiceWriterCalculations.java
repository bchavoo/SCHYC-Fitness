package writer;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import entitites.Address;
import entitites.InvoiceProducts;
import product.DayMemberships;
import product.Equipment;
import product.ParkingPass;
import product.Product;
import product.YearMemberships;
import reader.Calculations;
import reader.FileReader;

public class InvoiceWriterCalculations {

	public static List<Calculations> calculateTotals(String invoiceNumber, String trainerLastName, String trainerFirstName, String memberName, String memberCode, String memberType, String personLastName, String personFirstName, Address memberAddress, List<InvoiceProducts> productList) {


		List<Product> productFileList = FileReader.createProductList();


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

		/**
		 * Complex algorithm to calculate the subTotal, taxes, discount, free parking passes,
		 * and the overall total, this takes all considerations into account and reads the
		 * invoice line by line
		 */
		for(int i = 0; i < productList.size(); i++) {
			for(int j = 0; j < productFileList.size(); j++) {
				
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

				
				quantity = productList.get(i).getQuantity();

				if(productList.get(i).getProductCode().equals(productFileList.get(j).getProductCode())) {

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
					} else {
						PPSubTotal = subTotal;
						PPTaxes = tax;
						PPTotal = totalCost;
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

