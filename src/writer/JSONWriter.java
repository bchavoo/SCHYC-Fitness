package writer;

import entitites.Member;
import entitites.Person;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.List;
import java.util.ArrayList;

import entitites.Address;
import entitites.Product;

public class JSONWriter {


	//public static void main(String[] args){
	//private final List<Member> finalmemberExport= new ArrayList<Member>();
	//private final List<Product> finalproductExport= new ArrayList<Product>();


	public static void createPersonJSON(List<Person> personList) {


		//Pretty Printing
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(personList);

		System.out.println(jsonString);




		//		JSONObject jsonObject = new JSONObject();
		//		
		//		jsonObject.put("Name", "BOB The Builder");
		//		jsonObject.put("Person Code", "94cc");
		//		jsonObject.put("Address", "2343 d street, Lincoln, NE, USA");
		//		
		//		JsonArray jsonArray = new JSONFileWriter();
		//		jsonArray.add("Java");
		//		jsonArray.add("Struts");
		//		jsonArray.add("jQuery");
		//		
		//		jsonObject.put("technology", jsonArray);
		//			
		//		try { 
		//			FileWriter fileWriter = new FileWriter("Person.json");
		//			fileWriter.write(jsonObject.toJSONString());
		//			fileWriter.flush();
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//		System.out.println(jsonObject);
		//		}






	}
}
//public static void main(String[] args){
/*
		public List<Person> personExportToJson() {


			return personExportToJson();
		}


		public  List<Member> memberExportToJson() {


			return memberExportToJson();
		}
		public List<Product> productExportToJson () {


			return productExportToJson();
		}




	}
 */

