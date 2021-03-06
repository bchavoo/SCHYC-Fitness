package writer;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sf.ext.DBReader;

import entities.Address;
import entities.InvoiceProducts;
import product.DayMembership;
import product.RentalEquipment;
import product.ParkingPass;
import product.Product;
import product.YearMembership;
import reader.Calculations;
import reader.FileReader;

public class InvoiceCalculator {
	
	/**
	 * Methods to calulate a single invoices subTotal, tax, and total
	 */

	//Calculate all totals --------------------------------------------------------------------------------------------------------------------------------------------->
	//Invoice Total Algorithm
	public static List<Calculations> calculateTotals(String memberType, List<InvoiceProducts> productList) {

		List<Product> productFileList = DBReader.getAllProducts();


		/**
		 * Although some of these varibles (on Eclipse) say they are not "used"
		 * we do use them throughout the algorithm to calculate the totals
		 */
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
		 * Here we created array lists so that the prices of some of the year and day memberships do not replace previous products
		 * (for example, if a person buys two kinds of DayMembership, the data from the first product is not replaced)
		 * and so the information would be stored in them to receive the right calculations.
		 */
		ArrayList<String> ymCodes = new ArrayList<String>();
		ArrayList<String> dmCodes = new ArrayList<String>();

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
					if(productFileList.get(j) instanceof YearMembership) {
						YearMembership yProduct = (YearMembership)productFileList.get(j);
						if(yProduct.getProductType().equals("Y")) {
							productCode = yProduct.getProductCode();
							productType = "Year-long membership";
							productName = yProduct.membershipName;							
							startDate = yProduct.getStartDate();
							endDate = yProduct.getEndDate();
							address = yProduct.getAddress().getStreet();
							YearMembershipFromInvoice = yProduct.getProductCode();
							ymCodes.add(YearMembershipFromInvoice);

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
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
					} else if (productFileList.get(j) instanceof DayMembership) {
						DayMembership dProduct = (DayMembership)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();
							DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
							String sDate = dateOutput.print(startDate);
							dmCodes.add(DayMembershipFromInvoice);


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
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
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
							String freeType = "";

							/**
							 * Here we use two different for loops to check the list of Codes from the products
							 * We store into an array so we check each indext to see if a parking pass is tied to 
							 * a stored product code, then they get a discount and we flag that in a string
							 */
							for(int k = 0; k < ymCodes.size(); k++) {
								if(personCode.equals(ymCodes.get(k))) {
									freeType = "365";
								}
							} 

							for(int m = 0; m < dmCodes.size(); m++) {
								if(personCode.equals(dmCodes.get(m))) {
									freeType = "1";
								}
							}


							if(freeType.equals("")) {
								/**
								 * Here we see if the person code equals to null than we will give it no free
								 * parking passes 
								 */
								//THEY GET NO FREE PARKING PASSES
								PPSubTotal += subTotal;
								PPTaxes += tax;
								PPTotal += totalCost;
								break; 
							} else if (freeType.equals("365")) {
								if(quantity < 365) {
									//If the amount bought is less than 365 then they get a all passes free
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
							} else if (freeType.equals("1")) {
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
						/**
						 * BONUS: Here we create an instance of method that helps us initialize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */} else if (productFileList.get(j) instanceof RentalEquipment) {
							 RentalEquipment eProduct = (RentalEquipment)productFileList.get(j);
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
		 * Here we store the calculation inside a variable of array list to be used for the Executive Report
		 * This class is just a calculator to calculate the invoices before processing all of them
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


	//Calculate subtotal of a single invoice -------------------------------------------------------------------------------------------------------------------------->
	public static double calculateSubTotal(String memberType, ArrayList<InvoiceProducts> productList) {
		List<Product> productFileList = DBReader.getAllProducts();


		/**
		 * Although some of these varibles (on Eclipse) say they are not "used"
		 * we do use them throughout the algorithm to calculate the totals
		 */
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

		/** 
		 * Here we created array lists so that the prices of some of the year and day memberships do not replace previous products
		 * (for example, if a person buys two kinds of DayMembership, the data from the first product is not replaced)
		 * and so the information would be stored in them to receive the right calculations.
		 */
		ArrayList<String> ymCodes = new ArrayList<String>();
		ArrayList<String> dmCodes = new ArrayList<String>();

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
					if(productFileList.get(j) instanceof YearMembership) {
						YearMembership yProduct = (YearMembership)productFileList.get(j);
						if(yProduct.getProductType().equals("Y")) {
							productCode = yProduct.getProductCode();
							productType = "Year-long membership";
							productName = yProduct.membershipName;							
							startDate = yProduct.getStartDate();
							endDate = yProduct.getEndDate();
							address = yProduct.getAddress().getStreet();
							YearMembershipFromInvoice = yProduct.getProductCode();
							ymCodes.add(YearMembershipFromInvoice);

							if(productType.equals("Year-long membership")) {


								if(startDate.getMonthOfYear() == 1) {
									//They get a 15% discount if the purchase falls within the first month
									costPerUnit = yProduct.getCost() * yProduct.getDiscount();	
									subTotal = yProduct.getSubTotal(costPerUnit, quantity);
									tax = yProduct.getTax(subTotal);
									totalCost = yProduct.getTotal(subTotal, tax);

									YMSubTotal += subTotal;
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
									break;
								}


							}


						}
						//DAYMEMBERSHIP -------------------------------------------------------------------------------------------------------------------------------------------------->
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
					} else if (productFileList.get(j) instanceof DayMembership) {
						DayMembership dProduct = (DayMembership)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();
							dmCodes.add(DayMembershipFromInvoice);


							// if the month is 1 we give discount if not than we don't

							if (dProduct.getStartDate().getMonthOfYear() == 1) {
								costPerUnit = dProduct.getCost() * dProduct.getDiscount();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								//They get a 50% discount if it falls in the month of January

								DMSubTotal += subTotal;
								break;
							} else if (dProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = dProduct.getCost();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								//No Discount
								//Here we initalize variables to their actual cost info
								DMSubTotal += subTotal;
								break;
							}



						}


						//PARKING PASS ------------------------------------------------------------------------------------------------------------------------------------------------->
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
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
							String freeType = "";

							/**
							 * Here we use two different for loops to check the list of Codes from the products
							 * We store into an array so we check each indext to see if a parking pass is tied to 
							 * a stored product code, then they get a discount and we flag that in a string
							 */
							for(int k = 0; k < ymCodes.size(); k++) {
								if(personCode.equals(ymCodes.get(k))) {
									freeType = "365";
								}
							} 

							for(int m = 0; m < dmCodes.size(); m++) {
								if(personCode.equals(dmCodes.get(m))) {
									freeType = "1";
								}
							}


							if(freeType.equals("")) {
								/**
								 * Here we see if the person code equals to null than we will give it no free
								 * parking passes 
								 */
								//THEY GET NO FREE PARKING PASSES
								PPSubTotal += subTotal;
								break; 
							} else if (freeType.equals("365")) {
								if(quantity < 365) {
									//If the amount bought is less than 365 then they get a all passes free
									subTotal = 0;

									PPSubTotal += 0;
									break;
								} else if (quantity > 365) {
									//If they actually buy more than 365 then they will only get 365 free
									PPSubTotal += subTotal-(quantity*costPerUnit);
									break;
								} 
							} else if (freeType.equals("1")) {
								//They get 1 free parking pass if they buy a DayMembership
								quantity = quantity - 1;
								costPerUnit = pProduct.getCost();
								subTotal = pProduct.getSubTotal(costPerUnit, quantity);

								PPSubTotal += subTotal;
								break;
							} else {
								PPSubTotal += subTotal;
								break;
							}


						}
						//RENTAL EQUIPMENT ------------------------------------------------------------------------------------------------------------------------------------>
						/**
						 * BONUS: Here we create an instance of method that helps us initialize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */} else if (productFileList.get(j) instanceof RentalEquipment) {
							 RentalEquipment eProduct = (RentalEquipment)productFileList.get(j);
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
										 break;
									 } else if(personCode.equals(YearMembershipFromInvoice)) {
										 //THEY GET A 5% DISCOUNT if the person code is connected to a year membership
										 RESubTotal +=  subTotal*0.95;
										 break;
									 } else {
										 //They do NOT get a discount
										 // Here we initialize varibles to the actual cost info of the rentals
										 RESubTotal += subTotal;
										 break;
									 }
								 }
							 }
						 }
				}
			}
		}


		double allSubTotals = YMSubTotal + DMSubTotal + PPSubTotal + RESubTotal;
		return allSubTotals;		
	}


	//Calculate tax of a single invoice -------------------------------------------------------------------------------------------------------------------------->
	public static double calculateTax(String memberType, List<InvoiceProducts> productList) {

		List<Product> productFileList = DBReader.getAllProducts();


		/**
		 * Although some of these varibles (on Eclipse) say they are not "used"
		 * we do use them throughout the algorithm to calculate the totals
		 */
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

		double YMTaxes = 0;
		double DMTaxes = 0;
		double PPTaxes = 0;
		double RETaxes = 0;


		/** 
		 * Here we created array lists so that the prices of some of the year and day memberships do not replace previous products
		 * (for example, if a person buys two kinds of DayMembership, the data from the first product is not replaced)
		 * and so the information would be stored in them to receive the right calculations.
		 */
		ArrayList<String> ymCodes = new ArrayList<String>();
		ArrayList<String> dmCodes = new ArrayList<String>();

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
					if(productFileList.get(j) instanceof YearMembership) {
						YearMembership yProduct = (YearMembership)productFileList.get(j);
						if(yProduct.getProductType().equals("Y")) {
							productCode = yProduct.getProductCode();
							productType = "Year-long membership";
							productName = yProduct.membershipName;							
							startDate = yProduct.getStartDate();
							endDate = yProduct.getEndDate();
							address = yProduct.getAddress().getStreet();
							YearMembershipFromInvoice = yProduct.getProductCode();
							ymCodes.add(YearMembershipFromInvoice);

							if(productType.equals("Year-long membership")) {


								if(startDate.getMonthOfYear() == 1) {
									//They get a 15% discount if the purchase falls within the first month
									costPerUnit = yProduct.getCost() * yProduct.getDiscount();	
									subTotal = yProduct.getSubTotal(costPerUnit, quantity);
									tax = yProduct.getTax(subTotal);
									totalCost = yProduct.getTotal(subTotal, tax);

									YMTaxes += tax;
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

									YMTaxes += tax;
									break;
								}


							}


						}
						//DAYMEMBERSHIP -------------------------------------------------------------------------------------------------------------------------------------------------->
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
					} else if (productFileList.get(j) instanceof DayMembership) {
						DayMembership dProduct = (DayMembership)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();
							DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
							String sDate = dateOutput.print(startDate);
							dmCodes.add(DayMembershipFromInvoice);


							// if the month is 1 we give discount if not than we don't

							if (dProduct.getStartDate().getMonthOfYear() == 1) {
								costPerUnit = dProduct.getCost() * dProduct.getDiscount();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
								//They get a 50% discount if it falls in the month of January

								DMTaxes += tax;
								break;
							} else if (dProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = dProduct.getCost();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
								//No Discount
								//Here we initalize variables to their actual cost info
								DMTaxes += tax;
								break;
							}



						}


						//PARKING PASS ------------------------------------------------------------------------------------------------------------------------------------------------->
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
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
							String freeType = "";

							/**
							 * Here we use two different for loops to check the list of Codes from the products
							 * We store into an array so we check each indext to see if a parking pass is tied to 
							 * a stored product code, then they get a discount and we flag that in a string
							 */
							for(int k = 0; k < ymCodes.size(); k++) {
								if(personCode.equals(ymCodes.get(k))) {
									freeType = "365";
								}
							} 

							for(int m = 0; m < dmCodes.size(); m++) {
								if(personCode.equals(dmCodes.get(m))) {
									freeType = "1";
								}
							}


							if(freeType.equals("")) {
								/**
								 * Here we see if the person code equals to null than we will give it no free
								 * parking passes 
								 */
								//THEY GET NO FREE PARKING PASSES
								PPTaxes += tax;
								break; 
							} else if (freeType.equals("365")) {
								if(quantity < 365) {
									//If the amount bought is less than 365 then they get a all passes free
									subTotal = 0;
									tax = 0;
									totalCost = 0;

									PPTaxes += 0;
									break;
								} else if (quantity > 365) {
									//If they actually buy more than 365 then they will only get 365 free
									PPTaxes += tax-((quantity*costPerUnit)*0.04);
									break;
								} 
							} else if (freeType.equals("1")) {
								//They get 1 free parking pass if they buy a DayMembership
								quantity = quantity - 1;
								costPerUnit = pProduct.getCost();
								subTotal = pProduct.getSubTotal(costPerUnit, quantity);
								tax = pProduct.getTax(subTotal);
								totalCost = pProduct.getTotal(subTotal, tax);

								PPTaxes += tax;
								break;
							} else {
								PPTaxes += tax;
								break;
							}


						}
						//RENTAL EQUIPMENT ------------------------------------------------------------------------------------------------------------------------------------>
						/**
						 * BONUS: Here we create an instance of method that helps us initialize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */} else if (productFileList.get(j) instanceof RentalEquipment) {
							 RentalEquipment eProduct = (RentalEquipment)productFileList.get(j);
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
										 RETaxes += tax;
										 break;
									 } else if(personCode.equals(YearMembershipFromInvoice)) {
										 //THEY GET A 5% DISCOUNT if the person code is connected to a year membership
										 RETaxes += (subTotal*0.95)*0.04;
										 break;
									 } else {
										 //They do NOT get a discount
										 // Here we initialize varibles to the actual cost info of the rentals
										 RETaxes += tax;
										 break;
									 }
								 }
							 }
						 }
				}
			}
		}


		double allTaxes = YMTaxes + DMTaxes + PPTaxes + RETaxes;
		return allTaxes;

	}


	//Calculate final total of a single invoice -------------------------------------------------------------------------------------------------------------------------->	
	public static double calculateTotal(String memberType, List<InvoiceProducts> productList) {

		List<Product> productFileList = DBReader.getAllProducts();


		/**
		 * Although some of these varibles (on Eclipse) say they are not "used"
		 * we do use them throughout the algorithm to calculate the totals
		 */
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
		 * Here we created array lists so that the prices of some of the year and day memberships do not replace previous products
		 * (for example, if a person buys two kinds of DayMembership, the data from the first product is not replaced)
		 * and so the information would be stored in them to receive the right calculations.
		 */
		ArrayList<String> ymCodes = new ArrayList<String>();
		ArrayList<String> dmCodes = new ArrayList<String>();

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
					if(productFileList.get(j) instanceof YearMembership) {
						YearMembership yProduct = (YearMembership)productFileList.get(j);
						if(yProduct.getProductType().equals("Y")) {
							productCode = yProduct.getProductCode();
							productType = "Year-long membership";
							productName = yProduct.membershipName;							
							startDate = yProduct.getStartDate();
							endDate = yProduct.getEndDate();
							address = yProduct.getAddress().getStreet();
							YearMembershipFromInvoice = yProduct.getProductCode();
							ymCodes.add(YearMembershipFromInvoice);

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
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
					} else if (productFileList.get(j) instanceof DayMembership) {
						DayMembership dProduct = (DayMembership)productFileList.get(j);
						if(dProduct.getProductType().equals("D")) {
							productCode = dProduct.getProductCode();
							productType = "Day-long membership";
							startDate = dProduct.getStartDate();
							address = dProduct.getAddress().getStreet();
							DayMembershipFromInvoice = dProduct.getProductCode();
							DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
							String sDate = dateOutput.print(startDate);
							dmCodes.add(DayMembershipFromInvoice);


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
						/**
						 * BONUS: Here we create an instanceof method that helps us initalize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */
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
							String freeType = "";

							/**
							 * Here we use two different for loops to check the list of Codes from the products
							 * We store into an array so we check each indext to see if a parking pass is tied to 
							 * a stored product code, then they get a discount and we flag that in a string
							 */
							for(int k = 0; k < ymCodes.size(); k++) {
								if(personCode.equals(ymCodes.get(k))) {
									freeType = "365";
								}
							} 

							for(int m = 0; m < dmCodes.size(); m++) {
								if(personCode.equals(dmCodes.get(m))) {
									freeType = "1";
								}
							}


							if(freeType.equals("")) {
								/**
								 * Here we see if the person code equals to null than we will give it no free
								 * parking passes 
								 */
								//THEY GET NO FREE PARKING PASSES
								PPSubTotal += subTotal;
								PPTaxes += tax;
								PPTotal += totalCost;
								break; 
							} else if (freeType.equals("365")) {
								if(quantity < 365) {
									//If the amount bought is less than 365 then they get a all passes free
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
							} else if (freeType.equals("1")) {
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
						/**
						 * BONUS: Here we create an instance of method that helps us initialize
						 * variables to what they need to be. And we do it for each product
						 * This also shows dynamic polymorphism.
						 */} else if (productFileList.get(j) instanceof RentalEquipment) {
							 RentalEquipment eProduct = (RentalEquipment)productFileList.get(j);
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
		return allTotals;

	}
}