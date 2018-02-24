package writer;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entitites.Address;
import entitites.Invoice;
import entitites.InvoiceProducts;
import entitites.Member;
import product.DayMemberships;
import product.Equipment;
import product.ParkingPass;
import product.Product;
import product.YearMemberships;
import reader.Calculations;
import reader.FileReader;

public class InvoiceWriter {


	public static void createInvoiceReport(List<Invoice> invoiceList)  {
		//Do all calculations and formatting here
		int i = 0;
		ArrayList<List<Calculations>> fullArray = new ArrayList<List<Calculations>>();

		/**
		 * Here we create a while loop that loops through the invoice list and we use 
		 * Variables to initialize parts of the invoice
		 */
//	We also see 
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


			List<Calculations> calcList = InvoiceWriterCalculations.calculateTotals(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
			fullArray.add(calcList);


			i++;
		}
		
		InvoiceWriter.createExcutiveReport(invoiceList, fullArray);
		fullArray.clear();
		i =0;
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


			List<Calculations> calcList = InvoiceWriterCalculations.calculateTotals(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
			fullArray.add(calcList);

			InvoiceWriter.createSingleInvoiceReport(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
			i++;
		}
		
	}


	public static void createExcutiveReport(List<Invoice> invoiceList, ArrayList<List<Calculations>> fullArray) {

		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.println("Invoice   Member                                            Personal Trainer                 Subtotal        Fees       Taxes    Discount       Total");

		/**
		 * Here we create a array and store the calculation for the subTotal
		 * student fees, taxes, discount and total, then use further below
		 * to display all of these values
		 */
		double[]totalArray = new double[fullArray.size()]; 


		for(int i = 0; i < fullArray.size(); i++) {
			totalArray[i] = fullArray.get(i).get(0).getSubTotal();
			totalArray[i] = fullArray.get(i).get(0).getStudentFees();
			totalArray[i] = fullArray.get(i).get(0).getTaxes();
			totalArray[i] = fullArray.get(i).get(0).getDiscount();
			totalArray[i] = fullArray.get(i).get(0).getFinalTotal();
		}
		
		double finalTotalSubTotal = 0; 
		double finalTotalFees = 0;
		double finalTotalTaxes = 0;
		double finalTotalDiscount = 0;
		double finalTotalTotal = 0;
		
		for(int j = 0; j < fullArray.size(); j++) {
			finalTotalSubTotal += fullArray.get(j).get(0).getSubTotal();
			finalTotalFees += fullArray.get(j).get(0).getStudentFees();
			finalTotalTaxes += fullArray.get(j).get(0).getTaxes();
			finalTotalDiscount += fullArray.get(j).get(0).getDiscount();
			finalTotalTotal += fullArray.get(j).get(0).getFinalTotal();		
			}


		/**
		 * Here we create a for loop to loop  the invoice list and we use variables
		 * and initalize it to the infomation we need
		 */
		for(int i = 0; i < invoiceList.size(); i++){
			String fullName = invoiceList.get(i).getPersonalTrainerCode().getLastName() + ", " + invoiceList.get(i).getPersonalTrainerCode().getFirstName();
			String memberType = invoiceList.get(i).getMemberCode().getMemberType();
			String memberNameType = "";

			/**
			 * If the type is G then we set it to General but 
			 * if the type is S then we set to Student
			 */
			if(memberType.equals("G")) {
				memberType = "General";
				memberNameType = invoiceList.get(i).getMemberCode().getName() + " [" + memberType + "] ";
			} else if (memberType.equals("S")) {
				memberType = "Student";
				memberNameType = invoiceList.get(i).getMemberCode().getName() + " [" + memberType + "] ";
			}


			//Here we format and display the information we have
			System.out.printf("%-9s %-49s %-29s $%10.2f $%10.2f $%10.2f $%9.2f $%10.2f\n", invoiceList.get(i).getInvoiceCode(), memberNameType, fullName, fullArray.get(i).get(0).getSubTotal(), fullArray.get(i).get(0).getStudentFees(), fullArray.get(i).get(0).getTaxes(), fullArray.get(i).get(0).getDiscount(), fullArray.get(i).get(0).getFinalTotal());
		}
		System.out.println("=====================================================================================================================================================");
		System.out.printf("%-89s $%10.2f $%10.2f $%10.2f $%9.2f $%10.2f", "TOTALS", finalTotalSubTotal, finalTotalFees, finalTotalTaxes, finalTotalDiscount, finalTotalTotal);

	}



	public static void createSingleInvoiceReport(String invoiceNumber, String trainerLastName, String trainerFirstName, String memberName, String memberCode, String memberType, String personLastName, String personFirstName, Address memberAddress, List<InvoiceProducts> productList) {
		/**
		 * Here we create a function/method that holds arguments of the invoice and print them out
		 */
		System.out.println("\n\nIndividual Invoice Detail Reports");
		System.out.println("==================================================");
		System.out.println("Invoice  " + invoiceNumber);
		System.out.println("========================");
		System.out.println("Personal Trainer: " + trainerLastName + ", " + trainerFirstName);
		System.out.println("Member Info:");
		System.out.println("  " + memberName + "  (" + memberCode + ")");
		System.out.println("  [" + memberType + "]");
		System.out.println("  " + personLastName + "," + personFirstName);
		System.out.println("  " + memberAddress.getStreet());
		System.out.println("  " + memberAddress.getCity() + " " + memberAddress.getState() + " " + memberAddress.getZip() + " "  + memberAddress.getCountry());
		System.out.println("------------------------------------------");
		System.out.println("Code      Item                                                                      SubTotal        Tax       Total");

		List<Product> productFileList = FileReader.createProductList();


		//System.out.println(productFileList.get(0).getProductCode();
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
		// Here we initalize many variables for our calculation and format
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
								DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
								String sDate = dateOutput.print(startDate);
								String eDate = dateOutput.print(endDate);

								if(startDate.getMonthOfYear() == 1) {
									//They get a 15% discount if the purchase falls within the first month
									costPerUnit = yProduct.getCost() * yProduct.getDiscount();	
									subTotal = yProduct.getSubTotal(costPerUnit, quantity);
									tax = yProduct.getTax(subTotal);
									totalCost = yProduct.getTotal(subTotal, tax);
									String s1 = productType;
									String s2 = "'" + productName + "' @ ";
									String s3 = address;
									String all = s1 + " " + s2 + " " + s3;
									System.out.printf("%-9s %-70s $%10.2f $%9.2f $%10.2f\n", productCode, all, subTotal, tax, totalCost);
									System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + " with %%15 off)\n", "", sDate, eDate, quantity, costPerUnit/yProduct.getDiscount());
									YMSubTotal = subTotal;
									YMTaxes = tax;
									YMTotal = totalCost;
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
									String s1 = productType;
									String s2 = "'" + productName + "' @ ";
									String s3 = address;
									String all = s1 + " " + s2 + " " + s3;
									System.out.printf("%-9s %-70s $%10.2f $%9.2f $%10.2f\n", productCode, all, subTotal, tax, totalCost);
									System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + ")\n", "", sDate, eDate, quantity, costPerUnit);
									YMSubTotal = subTotal;
									YMTaxes = tax;
									YMTotal = totalCost;
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
								System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
								System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + "w/ %%50 off)\n", "", sDate, quantity, costPerUnit);
								DMSubTotal = subTotal;
								DMTaxes = tax;
								DMTotal = totalCost;
								break;
							} else if (dProduct.getStartDate().getMonthOfYear() != 1) {
								costPerUnit = dProduct.getCost();
								subTotal = dProduct.getSubTotal(costPerUnit, quantity);
								tax = dProduct.getTax(subTotal);
								totalCost = dProduct.getTotal(subTotal, tax);
								//No Discount
								System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
								System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + ")\n", "", sDate, quantity, costPerUnit);
								//Here we initalize variables to their actual cost info
								DMSubTotal = subTotal;
								DMTaxes = tax;
								DMTotal = totalCost;
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
								System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%.2f" + ")" + "%39s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, "", subTotal, tax, totalCost);
								PPSubTotal = subTotal;
								PPTaxes = tax;
								PPTotal = totalCost;
								break; 
							} else if (personCode.equals(YearMembershipFromInvoice)) {
								if(quantity < 365) {
									//if the amount bought is less than 365 then they get a all passes free
									subTotal = 0;
									tax = 0;
									totalCost = 0;
									System.out.printf("%-9s %-12s %-4s (" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " w/ %-2.0f free)" + "%23s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, quantity, "", subTotal, tax, totalCost);
									PPSubTotal = 0;
									PPTaxes = 0;
									PPTotal = 0;
									break;
								} else if (quantity > 365) {
									//If they actually buy more than 365 then they will only get 365 free
									System.out.printf("%-9s %-12s %-4s (" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " w/ %-2.0f free)" + "%23s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, quantity, "", subTotal-(quantity*costPerUnit), tax-((quantity*costPerUnit)*0.04), totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04)));
									PPSubTotal = subTotal-(quantity*costPerUnit);
									PPTaxes = tax-((quantity*costPerUnit)*0.04);
									PPTotal = totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
									break;
								} 
							} else if (personCode.equals(DayMembershipFromInvoice)) {
								//They get 1 free parking pass if they buy a DayMembership
								quantity = quantity - 1;
								costPerUnit = pProduct.getCost();
								subTotal = pProduct.getSubTotal(costPerUnit, quantity);
								tax = pProduct.getTax(subTotal);
								totalCost = pProduct.getTotal(subTotal, tax);
								System.out.printf("%-9s %-12s %-4s (" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " w/ %-2.0f free)" + "%23s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity+1, costPerUnit, 1.00, "", subTotal, tax, totalCost);
								PPSubTotal = subTotal;
								PPTaxes = tax;
								PPTotal = totalCost;
								break;
							} else {
								System.out.printf("%-9s %-12s (" + "%-2.0f"+ " units @ " + "$" + "%.2f" + ")" + "%39s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, "", subTotal, tax, totalCost);
								PPSubTotal = subTotal;
								PPTaxes = tax;
								PPTotal = totalCost;
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
									System.out.printf("%-9s %-13s - %-51s $%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, subTotal, tax, totalCost);
									System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n", "", quantity, costPerUnit);
									RESubTotal = subTotal;
									RETaxes = tax;
									RETotal = totalCost;
									break;
								} else if(personCode.equals(YearMembershipFromInvoice)) {
									//THEY GET A 5% DISCOUNT if the person code is connected to a year membership
									System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal*0.95, (subTotal*0.95)*0.04, (subTotal*0.95) + ((subTotal*0.95)*0.04));
									System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
									RESubTotal =  subTotal*0.95;
									RETaxes = (subTotal*0.95)*0.04;
									RETotal = (subTotal*0.95) + ((subTotal*0.95)*0.04);
									break;
								} else {
									//They do NOT get a discount
									System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal, tax, totalCost);
									System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
									// Here we initialize varibles to the actual cost info of the rentals
									RESubTotal = subTotal;
									RETaxes = tax;
									RETotal = totalCost;
									break;
								}
							}
						}
					}
				}
			}
		}


		System.out.println("                                                                                ===================================");
		double allSubTotals = YMSubTotal + DMSubTotal + PPSubTotal + RESubTotal;
		double allTaxes = YMTaxes + DMTaxes + PPTaxes + RETaxes;
		double allTotals = YMTotal + DMTotal + PPTotal + RETotal;

		System.out.printf("SUB-TOTALS  %68s $%10.2f $%9.2f $%10.2f\n","", allSubTotals, allTaxes, allTotals);

		double discount = 0;
		double additionalStudentFee = 0;
		if(memberType.equals("Student")) {
			discount = (((allSubTotals * 0.08) + allTaxes) * -1);
			additionalStudentFee = 10.50;
			System.out.printf("DISCOUNT (8 STUDENT & NO TAX) %73s $%10.2f\n", "", discount);
			System.out.printf("ADDITIONAL FEE (Student) %78s $%10.2f\n","" , additionalStudentFee);
		}

		System.out.printf("TOTAL %97s $%10.2f\n","" , allTotals + discount + additionalStudentFee);
		System.out.printf("\n\n                                       Thank you for your purchase! :)\n");

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

	}




}
