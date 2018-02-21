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
				
				
					DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
					String sDate = dateOutput.print(startDate);
					String eDate = dateOutput.print(endDate);
					
					if(startDate.getMonthOfYear() == 1) {
						//They get a 15% discount
						String s1 = productType;
						String s2 = "'" + productName + "' @ ";
						String s3 = address;
						String all = s1 + " " + s2 + " " + s3;
						System.out.printf("%-9s %-70s $%10.2f $%9.2f $%10.2f\n", productCode, all, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + " with %%15 off)\n", "", sDate, eDate, quantity, costPerUnit);
						YMSubTotal = subTotal;
						YMTaxes = tax;
						YMTotal = totalCost;
						
					} else if (startDate.getMonthOfYear() != 1) {
						//No discount
						String s1 = productType;
						String s2 = "'" + productName + "' @ ";
						String s3 = address;
						String all = s1 + " " + s2 + " " + s3;
						System.out.printf("%-9s %-70s $%10.2f $%9.2f $%10.2f\n", productCode, all, subTotal, tax, totalCost);
						System.out.printf("%9s %-8s - %-8s " + "(" + "%-2.0f" + " units @ " + "$%5.2f" + ")\n", "", sDate, eDate, quantity, costPerUnit);
						YMSubTotal = subTotal;
						YMTaxes = tax;
						YMTotal = totalCost;
					}
					
					
			} else if (productType.equals("Day-long membership")) {
				DateTimeFormatter dateOutput = DateTimeFormat.forPattern("MM/dd/yy");
				String sDate = dateOutput.print(startDate);
				if(startDate.getMonthOfYear() == 1) {
					//They get a 50% discount
					System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
					System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + ")\n", "", sDate, quantity, costPerUnit);
					DMSubTotal = subTotal;
					DMTaxes = tax;
					DMTotal = totalCost;
				}else if (startDate.getMonthOfYear() != 1){
					//No Discount
					System.out.printf("%-9s %-20s" + " @ " + "%-48s" + "$%10.2f $%9.2f $%10.2f\n", productCode, productType, address, subTotal, tax, totalCost);
					System.out.printf("%9s %8s " + "(" + "%.0f" + " units @ $" + "%5.2f" + ")\n", "", sDate, quantity, costPerUnit);
					DMSubTotal = subTotal;
					DMTaxes = tax;
					DMTotal = totalCost;
				}

			} else if (productType.equals("Parking Pass")) {




				if(personCode.equals("")) {
					//THEY GET NO FREE PARKING PASSES
					System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%.2f" + ")" + "%39s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, "", subTotal, tax, totalCost);
					PPSubTotal = subTotal;
					PPTaxes = tax;
					PPTotal = totalCost;
				} else if (personCode.equals(DayMembershipFromInvoice)) {
					System.out.printf("%-9s %-12s %-4s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-1.0f free)" + "%25s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, quantity, costPerUnit, 1.00, "", subTotal-(1*costPerUnit), tax-(costPerUnit*0.04), totalCost-((costPerUnit) + ((costPerUnit)*0.04)));
					PPSubTotal = subTotal-(1*costPerUnit);
					PPTaxes = tax-(costPerUnit*0.04);
					PPTotal = totalCost-((costPerUnit) + ((costPerUnit)*0.04));
				} else if (personCode.equals(YearMembershipFromInvoice)) {
					if(quantity < 365) {
						System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-2.0f free)" + "%29s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, quantity, "", subTotal-(quantity*costPerUnit), tax-((quantity*costPerUnit)*0.04), totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04)));
						PPSubTotal = subTotal-(quantity*costPerUnit);
						PPTaxes = tax-((quantity*costPerUnit)*0.04);
						PPTotal = totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
					} else if (quantity > 365) {
						System.out.printf("%-9s %-12s " + "(" + "%-2.0f"+ " units @ " + "$" + "%4.2f" + " @ %-3.0f free)" + "%25s"+ "$%10.2f $%9.2f $%10.2f\n", productCode, productType, quantity, costPerUnit, 365.00, "", subTotal-(quantity*costPerUnit), tax-((quantity*costPerUnit)*0.04), totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04)));	
						PPSubTotal = subTotal-(quantity*costPerUnit);
						PPTaxes = tax-((quantity*costPerUnit)*0.04);
						PPTotal = totalCost-((quantity*costPerUnit) + ((quantity*costPerUnit)*0.04));
					}
				} 



			} else if (productType.equals("Rental Equipment")) {
				if(personCode.equals("")) {
					//They do NOT get a discount (bc there is no year membership tied to)
					System.out.printf("%-9s %-13s - %-51s $%10.2f $%9.2f $%10.2f\n", productCode, productType, productName, subTotal, tax, totalCost);
					System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n", "", quantity, costPerUnit);
					RESubTotal = subTotal;
					RETaxes = tax;
					RETotal = totalCost;
				} else {
					if(personCode.equals(YearMembershipFromInvoice)) {
						//THEY GET A 5% DISCOUNT
						System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal*0.95, (subTotal*0.95)*0.04, (subTotal*0.95) + ((subTotal*0.95)*0.04));
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
						RESubTotal =  subTotal*0.95;
						RETaxes = (subTotal*0.95)*0.04;
						RETotal = (subTotal*0.95) + ((subTotal*0.95)*0.04);
					} else {
						//They do NOT get a discount
						System.out.printf("%-9s %-16s - %-4s - %-44s $%10.2f $%9.2f $%10.2f\n", productCode, productType, personCode, productName, subTotal, tax, totalCost);
						System.out.printf("%10s" + "(" + "%.0f" + " units @ $" + "%5.2f" + "/unit)\n"+ "", "", quantity, costPerUnit);
						RESubTotal = subTotal;
						RETaxes = tax;
						RETotal = totalCost;
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
			discount = ((((allSubTotals) * 0.08) + allTaxes) * -1);
			additionalStudentFee = 10.50;
			System.out.printf("DISCOUNT (8 STUDENT & NO TAX) %73s $%10.2f\n", "", discount);
			System.out.printf("ADDITIONAL FEE (Student) %78s $%10.2f\n","" , additionalStudentFee);
		}

		System.out.printf("TOTAL %97s $%10.2f\n","" , allTotals + discount + additionalStudentFee);
		System.out.printf("\n\n                                       Thank you for your purchase!\n");

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
