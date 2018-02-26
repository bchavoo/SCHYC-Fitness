package writer;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

		/**
		 * Complex algorithm to calculate the subTotal, taxes, discount, free parking passes,
		 * and the overall total, this takes all considerations into account and reads the
		 * invoice line by line
		 */
		for(int i = 0; i < productList.size(); i++) {
			for(int j = 0; j < productFileList.size(); j++) {

				if(productList.get(i).getProductCode().equals(productFileList.get(j).getProductCode())) {
					personCode = "";
					productType = "";
					quantity = productList.get(i).getQuantity();
					/**
					 * BONUS: Here we create an instanceof method that helps us initalize
					 * variables to what they need to be. And we do it for each product
					 * This also shows dynamic polymorphism.
					 */
			//YEARMEMBERSHIP ------------------------------------------------------------------------------------------------------------------------------>
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

							if(productType.equals("Year-long membership")) {
	

								if(startDate.getMonthOfYear() == 1) {
									//They get a 15% discount if the purchase falls within the first month
									costPerUnit = yProduct.getCost() * yProduct.getDiscount();	
									subTotal = yProduct.getSubTotal(costPerUnit, quantity);
									tax = yProduct.getTax(subTotal);
									totalCost = yProduct.getTotal(subTotal, tax);

									YMSubTotal += subTotal;
									YMTaxes += tax;
									YMTotal += totalCost;
									break;
								} else if (startDate.getMonthOfYear() != 1) {
									//No discount
									/**
									 * Here we create three strings and these strings include the products info
									 * so then we use this to store it in one big string which helps with format.
									 */
									costPerUnit = yProduct.getCost();
									subTotal = yProduct.getSubTotal(costPerUnit, quantity);
									tax = yProduct.getTax(subTotal);
									totalCost = yProduct.getTotal(subTotal, tax);

									YMSubTotal += subTotal;
									YMTaxes += tax;
									YMTotal += totalCost;
									break;
								}


							}


						}
			//DAYMEMBERSHIP -------------------------------------------------------------------------------------------------------------------------------------------------->
					} else if (productFileList.get(j) instanceof DayMemberships) {
						DayMemberships dProduct = (DayMemberships)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();
							DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
							String sDate = dateOutput.print(startDate);

							// if the month is 1 we give discount if not than we don't

							if (dProduct.getStartDate().getMonthOfYear() == 1) {
								costPerUnit = dProduct.getCost() * dProduct.getDiscount();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
								//They get a 50% discount if it falls in the month of January

								DMSubTotal += subTotal;
								DMTaxes += tax;
								DMTotal += totalCost;
								break;
							} else if (dProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = dProduct.getCost();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
								//No Discount
								//Here we initalize variables to their actual cost info
								DMSubTotal += subTotal;
								DMTaxes += tax;
								DMTotal += totalCost;
								break;
							}



						}
			//PARKING PASS ------------------------------------------------------------------------------------------------------------------------------------------------->
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

							if(personCode.equals("")) {
								/**
								 * Here we see if the person code equals to null than we will give it no free
								 * parking passes 
								 */
								//THEY GET NO FREE PARKING PASSES
								PPSubTotal += subTotal;
								PPTaxes += tax;
								PPTotal += totalCost;
								break; 
							} else if (personCode.equals(YearMembershipFromInvoice)) {
								if(quantity < 365) {
									//if the amount bought is less than 365 then they get a all passes free
									subTotal = 0;
									tax = 0;
									totalCost = 0;
									
									PPSubTotal += 0;
									PPTaxes += 0;
									PPTotal += 0;
									break;
								} else if (quantity > 365) {
									//If they actually buy more than 365 then they will only get 365 free
									PPSubTotal += subTotal-(quantity*costPerUnit);
									PPTaxes += tax-((quantity*costPerUnit)*0.04);
									PPTotal += totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
									break;
								} 
							} else if (personCode.equals(DayMembershipFromInvoice)) {
								//They get 1 free parking pass if they buy a DayMembership
								quantity = quantity - 1;
								costPerUnit = pProduct.getCost();
								subTotal = pProduct.getSubTotal(costPerUnit, quantity);
								tax = pProduct.getTax(subTotal);
								totalCost = pProduct.getTotal(subTotal, tax);
								
								PPSubTotal += subTotal;
								PPTaxes += tax;
								PPTotal += totalCost;
								break;
							} else {
								PPSubTotal += subTotal;
								PPTaxes += tax;
								PPTotal += totalCost;
								break;
							}


						}
			//RENTAL EQUIPMENT ------------------------------------------------------------------------------------------------------------------------------------>
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

							if (productType.equals("Rental Equipment")) {
								if(personCode.equals("")) {
									//They do NOT get a discount (because there is no year membership tied to)
									RESubTotal += subTotal;
									RETaxes += tax;
									RETotal += totalCost;
									break;
								} else if(personCode.equals(YearMembershipFromInvoice)) {
									//THEY GET A 5% DISCOUNT if the person code is connected to a year membership
									RESubTotal +=  subTotal*0.95;
									RETaxes += (subTotal*0.95)*0.04;
									RETotal += (subTotal*0.95) + ((subTotal*0.95)*0.04);
									break;
								} else {
									//They do NOT get a discount
									// Here we initialize varibles to the actual cost info of the rentals
									RESubTotal += subTotal;
									RETaxes += tax;
									RETotal += totalCost;
									break;
								}
							}
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


		
		/**
		 * Here we store the calculation inside a variable of array list
		 */
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