package com.juhnowski.parkovka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.aerospike.client.policy.ClientPolicy;

@SpringBootApplication
public class ParkovkaApplication implements CommandLineRunner {

	public static final String HOST = "localhost";
// final String HOST = "178.140.0.246";

	public static final int PORT = 3000;

	public static final AerospikeClient client = new AerospikeClient(HOST, PORT);

	public static void main(String[] args) {
		SpringApplication.run(ParkovkaApplication.class, args);
	}

	@Override
    public void run(String... args) {
        // System.out.println("Init Aerospike");
		// AerospikeClient client = new AerospikeClient(HOST, PORT);
		// System.out.println("Клиент инициализирован и подключен к кластеру.");

		// putBrands();
		// getBrands("all");
		// putModels();
		//getBrands("BMW");
		//	getBrands("Infiniti");
		//getModels(String brand);
		//income();
		//outcome();
		//report();
		//pay();
		//payments();
		//putBalance(100.00);
		//getBalance();
		// setRoles();
		// getRoles();
		// setRole();
		// getRole();
		// setName();
		// getName();
		// setTypes();
		// getTypes();
		// setType();
		// getType();
		// setLogo();
		// getLogo();
		//	setCar();
		//	getCar();
		//putBarriers();
		//getBarriers();
		getBarrier(1);
		close();
    }

	public static void getBrands(String brand) {
		//чтение моделей авто
		Key key = new Key("frm", "brands", brand);
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void putBrands(){
		List<String> models = new ArrayList<String>();
		models.addAll(AutoModels.getModels().keySet());
		Key modelsKey = new Key("frm", "brands", "all");
		System.out.println("putModels:theKey="+modelsKey);
		Bin modelsBin = new Bin("list", models);
		System.out.println( "Created " + modelsBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, modelsKey, modelsBin);
		System.out.println( "Posted " + modelsBin + ".");
	}

	public static void putModels(){

		List<String> brands = new ArrayList<String>();
		brands.addAll(AutoModels.getModels().keySet());
		brands.forEach(name -> {
				List<String> models = AutoModels.getModels().get(name);
				Key modelsKey = new Key("frm", "brands", name);
				Bin modelsBin = new Bin("list", models);
				System.out.println( "Created " + modelsBin + ".");
				ClientPolicy clientPolicy = new ClientPolicy();
				client.put(clientPolicy.writePolicyDefault, modelsKey, modelsBin);
		});
	}

	public static void income() {
		//подписка на въезд
		Key inKey = new Key("frm", "A123BC199", "visits");
		Bin inBin = new Bin("20230723022627", "in" );
		System.out.println( "Created " + inBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, inKey, inBin);
		System.out.println( "Posted " + inBin + ".");
	}

	public static void outcome() {
		//подписка на въезд
		Key inKey = new Key("frm", "A123BC199", "visits");
		Bin inBin = new Bin("20230723023627","out" );
		System.out.println( "Created " + inBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, inKey, inBin);
		System.out.println( "Posted " + inBin + ".");
	}

	public static void report() {
		Key key = new Key("frm", "A123BC199", "visits");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void pay() {
		//подписка на въезд
		Key payKey = new Key("frm", "A123BC199", "payments");
		Bin payBin = new Bin("20230723023827",1000.00 );
		System.out.println( "Created " + payBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, payKey, payBin);
		System.out.println( "Posted " + payBin + ".");
	}

	public static void payments() {
		Key key = new Key("frm", "A123BC199", "payments");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void putBalance(double value) {
		Key key = new Key("frm", "A123BC199", "balance");
		Record record = client.get(null, key);
		if (record==null) {
			Key balKey = new Key("frm", "A123BC199", "balance");
			Bin balBin = new Bin("rub",value ); //могут быть бонусное время или акции...
			System.out.println( "Created " + balBin + ".");
			ClientPolicy clientPolicy = new ClientPolicy();
			System.out.println("Created a client policy.");
			client.put(clientPolicy.writePolicyDefault, balKey, balBin);
			System.out.println( "Posted " + balBin + ".");
		} else {
			record.bins.values().forEach(v->{
				Key balKey = new Key("frm", "A123BC199", "balance");
				Bin balBin = new Bin("rub",(double)v+value );
				ClientPolicy clientPolicy = new ClientPolicy();
				client.put(clientPolicy.writePolicyDefault, balKey, balBin);
				System.out.println( "Posted " + balBin + ".");
			});
		}
	}

	public static void getBalance() {
		Key key = new Key("frm", "A123BC199", "balance");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setName(){
		Key balKey = new Key("frm", "A123BC199", "name");
		Bin bin = new Bin("fio","Илья Александрович Ю." );

		ClientPolicy clientPolicy = new ClientPolicy();
		client.put(clientPolicy.writePolicyDefault, balKey, bin);
		System.out.println( "Posted " + bin + ".");
	}

	public static void getName(){
		Key key = new Key("frm", "A123BC199", "name");
		Record record = client.get(null, key);
		System.out.println(record);
	}


	public static void setRoles(){
		List<String> models = new ArrayList<String>();
		models.addAll(Roles.list);
		Key modelsKey = new Key("frm", "roles", "all");
		System.out.println("putModels:theKey="+modelsKey);
		Bin modelsBin = new Bin("list", models);
		System.out.println( "Created " + modelsBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, modelsKey, modelsBin);
		System.out.println( "Posted " + modelsBin + ".");
	}

	public static void getRoles(){
		Key key = new Key("frm", "roles", "all");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setRole(){
		Key balKey = new Key("frm", "A123BC199", "role");
		Bin positionBin = new Bin("position","сотрудник" );
		Bin orgBin = new Bin("org","ООО Ромашка" );
		ClientPolicy clientPolicy = new ClientPolicy();
		client.put(clientPolicy.writePolicyDefault, balKey, positionBin, orgBin);
		System.out.println( "Posted " + positionBin + ".");
		System.out.println( "Posted " + orgBin + ".");
	}

	public static void getRole(){
		Key key = new Key("frm", "A123BC199", "name");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setTypes(){
		List<String> models = new ArrayList<String>();
		models.addAll(Types.list);
		Key modelsKey = new Key("frm", "types", "all");
		System.out.println("putModels:theKey="+modelsKey);
		Bin modelsBin = new Bin("list", models);
		System.out.println( "Created " + modelsBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, modelsKey, modelsBin);
		System.out.println( "Posted " + modelsBin + ".");
	}

	public static void getTypes(){
		Key key = new Key("frm", "types", "all");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setType(){
		Key balKey = new Key("frm", "A123BC199", "type");
		Bin bin = new Bin("type","Постоянный" );
		ClientPolicy clientPolicy = new ClientPolicy();
		client.put(clientPolicy.writePolicyDefault, balKey, bin);
	}

	public static void getType(){
		Key key = new Key("frm", "A123BC199", "type");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setLogo(){
		Key balKey = new Key("frm", "A123BC199", "logo");
		Bin bin = new Bin("url","https://img.png" );
		ClientPolicy clientPolicy = new ClientPolicy();
		client.put(clientPolicy.writePolicyDefault, balKey, bin);
	}

	public static void getLogo(){
		Key key = new Key("frm", "A123BC199", "logo");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void setCar(){
		Key balKey = new Key("frm", "A123BC199", "car");
		Bin binModel = new Bin("model","Audi A8" );
		Bin binColor = new Bin("color","Черный металлик");

		ClientPolicy clientPolicy = new ClientPolicy();
		client.put(clientPolicy.writePolicyDefault, balKey, binModel, binColor);
	}

	public static void getCar(){
		Key key = new Key("frm", "A123BC199", "car");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void getBarrier(Integer barrier) {
		Key key = new Key("frm", "barriers", barrier);
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void getBarriers() {
		Key key = new Key("frm", "barriers", "all");
		Record record = client.get(null, key);
		System.out.println(record);
	}

	public static void putBarriers(){
		List<Integer> models = new ArrayList<Integer>();
		models.addAll(Barriers.map.keySet());
		Key modelsKey = new Key("frm", "barriers", "all");
		System.out.println("putModels:theKey="+modelsKey);
		Bin modelsBin = new Bin("list", models);
		System.out.println( "Created " + modelsBin + ".");
		ClientPolicy clientPolicy = new ClientPolicy();
		System.out.println("Created a client policy.");
		client.put(clientPolicy.writePolicyDefault, modelsKey, modelsBin);
		System.out.println( "Posted " + modelsBin + ".");
		models.forEach(b -> {
			Key barrierKey = new Key("frm", "barriers", b);
			Bin nameBin = new Bin("name", Barriers.map.get(b));
			ClientPolicy clntPolicy = new ClientPolicy();
			client.put(clntPolicy.writePolicyDefault, barrierKey, nameBin);
			System.out.println( "Posted " + nameBin + ".");
		});
	}

// 	Key key = new Key("frm", "barrier", "1");
// Bin name = new Bin("name", "Въезд 1");
// Bin video = new Bin("name", "https://frm.com/video1_in.msrp");
// client.put(null, key, name, video);
// ------------------------------------------------
// Key key = new Key("frm", "barrier", "2");
// Bin name = new Bin("name", "Въезд 2");
// Bin video = new Bin("name", "https://frm.com/video2_in.msrp");
// client.put(null, key, name, video);
// ----------------------------------------------------------
// Key key = new Key("frm", "barrier", "3");
// Bin name = new Bin("name", "Выезд 1");
// Bin video = new Bin("name", "https://frm.com/video1_out.msrp");
// client.put(null, key, name, video);
// ------------------------------------------------
// Key key = new Key("frm", "barrier", "4");
// Bin name = new Bin("name", "Выезд 2");
// Bin video = new Bin("name", "https://frm.com/video2_out.msrp");
// client.put(null, key, name, video);
// ----------------------------------------------------------



	public static void close(){
		client.close();   
		System.out.println("Connection closed.");
	}
}
