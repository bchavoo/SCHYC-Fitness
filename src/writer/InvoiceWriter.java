package writer;

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
import reader.FileReader;

public class InvoiceWriter {


	public static void createInvoiceReport(List<Invoice> invoiceList)  {
		//Do all calculations and formatting here
		int i = 0;
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



			//InvoiceWriter.createExcutiveReport(invoiceNumber, memberName, memberType, trainerLastName, trainerFirstName );
			InvoiceWriter.createSingleInvoiceReport(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
			i++;
		}
	}




	public static void createExcutiveReport(String invoiceNumber, String memberName, String memberType, String trainerLastName, String trainerFistName) {

		System.out.println("Executive Summary Report");
		System.out.println("===========================");
		System.out.println("Invoice            Member                                              Personal Trainer               Subtotal           Fees          Taxes           Discount        Total");
		System.out.println(invoiceNumber + "             " + memberName + " [" + memberType + "]                          "  +  trainerLastName + "," + trainerFistName);
	}


	public static void createSingleInvoiceReport(String invoiceNumber, String trainerLastName, String trainerFirstName, String memberName, String memberCode, String memberType, String personLastName, String personFirstName, Address memberAddress, List<InvoiceProducts> productList) {
		System.out.println("Invoice  " + invoiceNumber);
		System.out.println("==================================================");
		System.out.println("Personal Trainer: " + trainerLastName + ", " + trainerFirstName);
		System.out.println("Member Info:");
		System.out.println("  " + memberName + "  (" + memberCode + ")");
		System.out.println("  [" + memberType + "]");
		System.out.println("  " + personLastName + ", " + personFirstName);
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
		boolean freeParkingPassesYM = false;
		boolean freeParkingPassesDM = false;
		boolean YearMembership = false;


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
							freeParkingPassesYM = true;
							YearMembership = true;
								
							
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
							freeParkingPassesDM = true;


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
				if (productName.equals("Bronze Fit")) {
					DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
					String sDate = dateOutput.print(startDate);
					String eDate = dateOutput.print(endDate);

					if(startDate.getMonthOfYear() == 1) {
						//They get a 15% discount
						System.out.printf("%-9s %-10s '%-10s'" + " @ " + "%-35s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + " with %%15 off)\n", "", sDate, eDate, quantity, costPerUnit);
					} else if (startDate.getMonthOfYear() != 1) {
						//No discount
						System.out.printf("%-9s %-10s '%-10s'" + " @ " + "%-35s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + ")\n", "", sDate, eDate, quantity, costPerUnit);
					}
				} else if (productName.equals("Gold Package")) {
					DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
					String sDate = dateOutput.print(startDate);
					String eDate = dateOutput.print(endDate);

					if(startDate.getMonthOfYear() == 1) {
						//They get a 15% discount
						System.out.printf("%-9s %-10s '%-10s'" + " @ " + "%-33s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + " with %%15 off)\n", "", sDate, eDate, quantity, costPerUnit);
					} else if (startDate.getMonthOfYear() != 1) {
						//No discount
						System.out.printf("%-9s %-10s '%-10s'" + " @ " + "%-35s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + ")\n", "", sDate, eDate, quantity, costPerUnit);
					}
				} else if (productName.equals("Ultimate Workout")){
					DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
					String sDate = dateOutput.print(startDate);
					String eDate = dateOutput.print(endDate);
					if(startDate.getMonthOfYear() == 1) {
						//They get a 15% discount
						System.out.printf("%-9s %-10s '%-16s'" + " @ " + "%-29s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %8s - %8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + " with%%15 off)\n", "", sDate, eDate, quantity, costPerUnit);
					} else if (startDate.getMonthOfYear() != 1) {
						//No discount
						System.out.printf("%-9s %-10s '%-16s'" + " @ " + "%-29s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, address, subTotal, tax, totalCost);
						System.out.printf("%9s %8s - %8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + ")\n", "", sDate, eDate, quantity, costPerUnit);
					}
				}
			} else if (productType.equals("Day-long membership")) {
				DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
				String sDate = dateOutput.print(startDate);
				if(startDate.getMonthOfYear() == 1) {
					//They get a 50% discount
					System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
					System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + ")\n", "", sDate, quantity, costPerUnit);
				}else if (startDate.getMonthOfYear() != 1){
					//No Discount
					System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
					System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + ")\n", "", sDate, quantity, costPerUnit);

				}

			} else if (productType.equals("Parking Pass")) {
				if(personCode.equals("")) {
					if(freeParkingPassesYM == true) {
						//THEY GET 365 FREE PARKING PASSES
						System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-2.0 free)" + "%26s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, quantity, "", subTotal-(quantity*costPerUnit), tax-((quantity*costPerUnit)*0.04), totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04)));
					} else if (freeParkingPassesDM == true) {
						//THEY GET 1 FREE PARKING PASSES
						System.out.printf("%-9s %-12s %-4s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-2.0f free)" + "%25s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, quantity, "", subTotal-(1*costPerUnit), tax-(costPerUnit*0.04), totalCost-((costPerUnit) + ((costPerUnit)*0.04)));
					} else {
						//THEY GET NO FREE PARKING PASSES
						System.out.printf("%-9s %-12s %-4s " + "(" + "%-2.0f"+ " units @ " + "$" + "%.2f" + ")" + "%34s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, "", subTotal, tax, totalCost);
					}

				} else {
					if(freeParkingPassesYM == true) {
						//THEY GET 365 FREE PARKING PASSES
						System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-2.0f free)" + "%29s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, quantity, "", subTotal-(quantity*costPerUnit), tax-((quantity*costPerUnit)*0.04), totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04)));
					} else if (freeParkingPassesDM == true) {
						//THEY GET 1 FREE PARKING PASSES
						System.out.printf("%-9s %-12s %-4s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-2.0f free)" + "%24s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, quantity, "", subTotal-(1*costPerUnit), tax-(costPerUnit*0.04), totalCost-((costPerUnit) + ((costPerUnit)*0.04)));
					} else {
						//THEY GET NO FREE PARKING PASSES
						System.out.printf("%-9s %-12s %-4s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + ")" + "%34s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, "", subTotal, tax, totalCost);
					}
				}

			} else if (productType.equals("Rental Equipment")) {
				if(personCode.equals("")) {
					if(YearMembership == true) {
						//Calculate discount somewhere here
						System.out.println("THEY GET A 5% DISCOUNT");
						System.out.printf("%-9s %-13s - %-51s $%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, subTotal, tax, totalCost);
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n", "", quantity, costPerUnit);
					} else if (YearMembership == false) {
						//No Discount
						System.out.println("They do NOT get a discount");
						System.out.printf("%-9s %-13s - %-51s $%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, subTotal, tax, totalCost);
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n", "", quantity, costPerUnit);
					}
				} else {
					if(YearMembership == true) {
						//Discount
						System.out.println("THEY GET A 5% DISCOUNT");
						System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal, tax, totalCost);
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
					} else if (YearMembership == false) {
						//NO discount
						System.out.println("They do NOT get a discount");
						System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal, tax, totalCost);
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
					}
				}
			}
		}






		System.out.println("                                                                                ===================================");
		System.out.printf("SUB-TOTALS  %68s $%10d $%9d $%10d\n","", 0, 0, 0);

		if(memberType.equals("Student")) {
			System.out.printf("DISCOUNT (8 STUDENT & NO TAX) %73s $%10d\n", "", 0);
			System.out.printf("ADDITIONAL FEE (Student) %78s $%10d\n","" , 0);
		}

		System.out.printf("TOTAL %97s $%10d\n","" , 0);
		System.out.printf("\n\n                                       Thank you for your purchase!\n");
	}




}
