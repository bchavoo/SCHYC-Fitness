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

	public static void createPersonJSON(List<Person> personList) {


		//Pretty Printing
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(personList);

		//System.out.println(jsonString);

	}

	public static void createMemberJSON(List<Member> memberList) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(memberList);

		//System.out.println(jsonString);
	}

	public static void createProductJSON(List<Product> productList) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(productList);

		System.out.println(jsonString);
	}
}



