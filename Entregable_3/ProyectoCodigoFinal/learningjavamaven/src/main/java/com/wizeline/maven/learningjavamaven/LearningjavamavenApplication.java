package com.wizeline.maven.learningjavamaven;

import com.wizeline.maven.learningjavamaven.config.EndpointBean;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.*;
import com.wizeline.maven.learningjavamaven.utils.exceptions.ExceptionGenerica;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sun.net.httpserver.HttpServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.security.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

import static com.wizeline.maven.learningjavamaven.utils.Utils.*;


@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class LearningjavamavenApplication {

	//private static final Logger LOGGER = Logger.getLogger(LearningjavamavenApplication.class.getName());
	//private static String SUCCESS_CODE = "OK000";
	//private static String responseTextThread = "";
	//private ResponseDTO response;
	//private static String textThread = "";


	@Autowired
	private EndpointBean endpointBean;

	//Definimos un Bean de Servicio este es manejado a través del contendeor de Spring IoC.
	@Bean
	public static UserService userService(){
		return new UserServiceImpl();
	}

	@Bean
	public static BankAccountService bankAccountService() {return new BankAccountServiceImpl();}

	@Bean
	public static BankCardService bankCardService() {return new BankCardServiceImpl();}



	public static void main(String[] args) throws IOException{
		SpringApplication.run(LearningjavamavenApplication.class, args);

//		LOGGER.info("Learning java - iniciado servicio REST ....");
//		/*Esta clase implementa un simple servidor HTTP*/
//		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//		String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion ...";

//		server.createContext("/api/login",(exchange -> {
//			LOGGER.info("Learning java - inicia procesamiento de peticion login...");
//			ResponseDTO response = new ResponseDTO();
//			String responseText = "";
//			/**Valida el tipo de http Request**/
//			if("GET".equals(exchange.getRequestMethod())){
//				LOGGER.info("Learning Java - Procesamiento petición HTTP de tipo GET ");
//				UserDTO user = new UserDTO();
//				user = user.getParameters(splitQuery(exchange.getRequestURI()));
//				response = login(user.getUser(), user.getPassword());
//				JSONObject json = new JSONObject(response);
//				responseText = json.toString();
//				exchange.getResponseHeaders().set("contentType","application/json; charset=UTF-8");
//				exchange.sendResponseHeaders(200,responseText.getBytes().length);
//			}else{
//				/**405 metodo no permitido**/
//				exchange.sendResponseHeaders(405,-1);
//			}
//			OutputStream output = exchange.getResponseBody();
//			/**Recuerda siempre cerrar las fuentes que tu abres
//			 Avoid memory leaks
//			 **/
//			LOGGER.info("Learning Java - Cerrando recursos ...");
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));

//		/*Esta clase implementa un simple servidor HTTP*/
//		server.createContext("/api/createUser",(exchange -> {
//			LOGGER.info("Learning java - inicia procesamiento de peticion createUser...");
//			ResponseDTO response = new ResponseDTO();
//			String responseText = "";
//			/**Valida el tipo de http Request**/
//			if("POST".equals(exchange.getRequestMethod())){
//				LOGGER.info("Learning Java - Procesamiento petición HTTP de tipo POST ");
//				UserDTO user = new UserDTO();
//				user = user.getParameters(splitQuery(exchange.getRequestURI()));
//				response = createUser(user.getUser(), user.getPassword());
//				JSONObject json = new JSONObject(response);
//				responseText = json.toString();
//				exchange.getResponseHeaders().set("contentType","application/json; charset=UTF-8");
//				exchange.sendResponseHeaders(200,responseText.getBytes().length);
//			}else{
//				/**405 metodo no permitido**/
//				exchange.sendResponseHeaders(405,-1);
//			}
//			OutputStream output = exchange.getResponseBody();
			/**Recuerda siempre cerrar las fuentes que tu abres
			 Avoid memory leaks
			 **/
//			LOGGER.info("Learning Java - Cerrando recursos ...");
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));

		// End Point para crear usuarios
//		server.createContext("/api/createUsers", (exchange -> {
//			LOGGER.info("LearningJava - Inicia procesamiento de peticion createUsers ...");
//			ResponseDTO response = new ResponseDTO();
//			/** Validates the type of http request  */
//			exchange.getRequestBody();
//			if ("POST".equals(exchange.getRequestMethod())) {
//				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST");
				//Obtenemos el request del body que mandamos
//				StringBuilder text = new StringBuilder();
//				try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
//					while(scanner.hasNext()) {
//						text.append(scanner.next());
//					}
//				}catch(Exception e){
//					LOGGER.severe(e.getMessage());
//					throw new ExceptionGenerica("Fallo al obtener el request del body ");
//				}
//				textThread = text.toString();
//				LOGGER.info(textThread);
				//Iniciamos Thread
//				LearningjavamavenApplication thread = new LearningjavamavenApplication();
//				thread.start();
				//Esperamos a que termine el thread
//				while(thread.isAlive());
//				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
//				exchange.sendResponseHeaders(200, responseTextThread.getBytes().length);
//			} else {
				/** 405 Method Not Allowed */
//				exchange.sendResponseHeaders(405, -1);
//			}
//			OutputStream output = exchange.getResponseBody();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
//			LOGGER.info("LearningJava - Cerrando recursos ...");
//			output.write(responseTextThread.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		//Consulta información de cuenta de usuario
//		server.createContext("/api/getUserAccount",(exchange -> {
//			LOGGER.info("Learning java - inicia procesamiento de peticion getUserAccount ...");

//			Instant inicioDeEjecucion =Instant.now(); //Se crea objeto de tipo Instant con la hora actual

//			ResponseDTO response = new ResponseDTO();
//			String responseText = "";
			/**Valida el tipo de http Request**/
//			if("GET".equals(exchange.getRequestMethod())){
//				LOGGER.info("Learning Java - Procesamiento petición HTTP de tipo GET ");
//				UserDTO user = new UserDTO();
//				Map<String,String> params = splitQuery(exchange.getRequestURI());
//				user = user.getParameters(params);

				//valida formato del parametro fecha (date) [dd-mm-yyyy]
//				String lastUsage = params.get("date");
//				LOGGER.info("lastUsage "+lastUsage);
//				if(validateNullValue(lastUsage) && isDateFormatValid(lastUsage) ) {

					//valida el password del usuario
//					if (isPasswordValid(user.getPassword())) {
//						response = login(user.getUser(), user.getPassword());
//						LOGGER.info("El Codigo de respuesta del metodo getUserAccount " + response.getCode());
//						if (response.getCode().equals(SUCCESS_CODE)) {
//							BankAccountDTO bankAccountDTO = getAccountDetails(user.getUser(),lastUsage);
//							JSONObject json = new JSONObject(bankAccountDTO);
//							responseText = json.toString();
//							exchange.getResponseHeaders().add("Content-type", "application/json");
//							exchange.sendResponseHeaders(200, responseText.getBytes().length);
//						} else {
//							responseText = "Nombre de usuario o password incorrecto";
//							exchange.getResponseHeaders().add("Content-type", "application/json");
//							exchange.sendResponseHeaders(401, responseText.getBytes().length);
//						}//else response

//					}else{
//						responseText = "Formato de password incorrecto";
//						exchange.getResponseHeaders().add("contentType", "application/json");
//						exchange.sendResponseHeaders(401, responseText.getBytes().length);
//					}
//				}else{
//					responseText = "Formato de fecha incorrecto";
//					exchange.getResponseHeaders().add("contentType", "application/json");
//					exchange.sendResponseHeaders(400, responseText.getBytes().length);
//				}

//			}else{
				/**405 metodo no permitido**/
//				exchange.sendResponseHeaders(405,-1);
//			}//else GET
//			OutputStream output = exchange.getResponseBody();

//			Instant finalDeEjecucion = Instant.now(); //Crea objeto de tipo Instant con la hora actual

			/**Recuerda siempre cerrar las fuentes que tu abres
			 Avoid memory leaks
			 **/
//			LOGGER.info("Learning Java - Cerrando recursos ...");
//			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion,finalDeEjecucion).toMillis()).concat("segundos. "));
//			LOGGER.info("Tiempo de respuesta: ".concat(total));
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));

		/**Consultar información de todas las cuentas**/
//		server.createContext("/api/getAccounts",(exchange -> {
//			LOGGER.info("Learning java - inicia procesamiento de peticion getAccounts...");
//			BankAccountService bankAccountBO = new BankAccountServiceImpl();

//			String responseText = "";
			/**Valida el tipo de http Request**/
//			if("GET".equals(exchange.getRequestMethod())){
//				LOGGER.info("Learning Java - Procesamiento petición HTTP de tipo GET ");
//				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
//				JSONArray json = new JSONArray(accounts);
//				responseText = json.toString();
//				exchange.getResponseHeaders().add("Content-type","application/json");
//				exchange.sendResponseHeaders(200,responseText.getBytes().length);
//			}else{
				/**405 metodo no permitido**/
//				exchange.sendResponseHeaders(405,-1);
//			}

//			OutputStream output = exchange.getResponseBody();
			/**Recuerda siempre cerrar las fuentes que tu abres
			 Avoid memory leaks
			 **/
//			LOGGER.info("Learning Java - Cerrando recursos ...");
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		// Consultar todas las cuentas y buscarla por nombre utilizando Optional por si no es encontrada
//		server.createContext("/api/getAccountByName", (exchange -> {
//			LOGGER.info(msgProcPeticion);
//			Instant inicioDeEjecucion = Instant.now();
//			BankAccountBO bankAccountBO = new BankAccountBOImpl();
//			String responseText = "";
			/** Validates the type of http request  */
//			if ("GET".equals(exchange.getRequestMethod())) {
//				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET getAccountByName");
//				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
				// Aquí implementaremos nuestro código de filtrar las cuentas por nombre utilizando optional
//				Map<String, String> params = splitQuery(exchange.getRequestURI());
//				Optional<String> Optionalnombre = getParameterValue(params, "name");
//				String nombre = Optionalnombre.get();
//				List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
//				accountsFiltered.clear();
//				LOGGER.info("nombtre : "+nombre);
//				for(int i=0;i<accounts.size();i++){
//					LOGGER.info("accounts.get(i).getAccountName("+i+") : "+accounts.get(i).getAccountName());
//					LOGGER.info("coincidencia "+accounts.get(i).getAccountName().indexOf(nombre));
//					if(accounts.get(i).getAccountName().indexOf(nombre) >= 0){
//						accountsFiltered.add(accounts.get(i));
//						break;
//					}
//				}
//				JSONArray json = new JSONArray(accountsFiltered);
//				responseText = json.toString();
//				LOGGER.info("total cuentas ...."+accounts.size());
//				exchange.getResponseHeaders().add("Content-type", "application/json");
//				exchange.sendResponseHeaders(200, responseText.getBytes().length);
//			} else {
				/** 405 Method Not Allowed */
//				exchange.sendResponseHeaders(405, -1);
//			}
//			OutputStream output = exchange.getResponseBody();
//			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
//			LOGGER.info("LearningJava - Cerrando recursos ...");
//			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
//			LOGGER.info("Tiempo de respuesta: ".concat(total));
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		// Consultar todas las cuentas y filtrarlas por usuario
//		server.createContext("/api/getAccountsByUser", (exchange -> {
//			LOGGER.info(msgProcPeticion);
//			Instant inicioDeEjecucion = Instant.now();
//			BankAccountBO bankAccountBO = new BankAccountBOImpl();
//			String responseText = "";
			/** Validates the type of http request  */
//			if ("GET".equals(exchange.getRequestMethod())) {
//				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET getAccountsByUser");
//				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
//				List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
//				accountsFiltered.clear();

				// Aquí implementaremos nuestro código de filtrar las cuentas por usuario usando genericos
//				Map<String, String> params = splitQuery(exchange.getRequestURI());
//				Optional<Object> Optionaluser= getParameterValueObject(params, "user");
//				Object user = Optionaluser.get();
//				for (int i = 0; i < accounts.size(); i++) {
//					if (accounts.get(i).getUser().indexOf(user.toString()) >= 0) {
//						accountsFiltered.add(accounts.get(i));
//					}
//				}

//				JSONArray json = new JSONArray(accountsFiltered);
//				responseText = json.toString();
//				exchange.getResponseHeaders().add("Content-type", "application/json");
//				exchange.sendResponseHeaders(200, responseText.getBytes().length);
//			} else {
				/** 405 Method Not Allowed */
//				exchange.sendResponseHeaders(405, -1);
//			}
//			OutputStream output = exchange.getResponseBody();
//			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
//			LOGGER.info("LearningJava - Cerrando recursos ...");
//			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
//			LOGGER.info("Tiempo de respuesta: ".concat(total));
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		// Consultar todas las cuentas y agruparlas por su tipo utilizando Programación Funcional
//		server.createContext("/api/getAccountsGroupByType", (exchange -> {
//			LOGGER.info(msgProcPeticion);
//			Instant inicioDeEjecucion = Instant.now();
//			BankAccountService bankAccountBO = new BankAccountServiceImpl();
//			String responseText = "";
			/** Validates the type of http request  */
//			if ("GET".equals(exchange.getRequestMethod())) {
//				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET getAccountsGroupByType ");
//				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();

				// Aqui implementaremos la programación funcional
//				Map<String,List<BankAccountDTO>> groupedAccounts;
//				Function<BankAccountDTO,String> groupFunction = (account) -> account.getAccountType().toString();
//				groupedAccounts = accounts.stream().collect(Collectors.groupingBy(groupFunction));

				//Ahora nuestra salida en lugar de ser accounts será groupedAccounts
//				JSONObject json = new JSONObject(groupedAccounts);
//				responseText = json.toString();
//				exchange.getResponseHeaders().add("Content-type", "application/json");
//				exchange.sendResponseHeaders(200, responseText.getBytes().length);
//			} else {
				/** 405 Method Not Allowed */
//				exchange.sendResponseHeaders(405, -1);
//			}
//			OutputStream output = exchange.getResponseBody();
//			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
//			LOGGER.info("LearningJava - Cerrando recursos ...");
//			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
//			LOGGER.info("Tiempo de respuesta: ".concat(total));
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		// Consultar todas las cuentas y regresarselas al usuario de manera cifrada
//		server.createContext("/api/getEncryptedAccounts", (exchange -> {
//			LOGGER.info(msgProcPeticion);
//			Instant inicioDeEjecucion = Instant.now();
//			BankAccountBO bankAccountBO = new BankAccountBOImpl();
//			String responseText = "";
			/** Validates the type of http request  */
//			if ("GET".equals(exchange.getRequestMethod())) {
//				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET getEncryptedAccounts");
//				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();

				// Aquí implementaremos nuestro código de cifrar nuestras cuentas y regresarselas al usuario de manera cifrada
//				byte[] keyBytes = new byte[]{
//						0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
//				};
//				byte[] ivBytes = new byte[]{
//						0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01
//				};
				//Establecemos a BouncyCastle como proveedor
//				Security.addProvider(new BouncyCastleProvider());

				//inicializamos las llaves y establecemos el algoritmo DES de cifrado
//				SecretKeySpec key = new SecretKeySpec(keyBytes,"DES");
//				IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//				Cipher cipher = null;
				//Inicializamos nuestro cifrado utilizando como transformador "DES/CTR/NoPadding" y estableciendole que utilizaremos Bouncy Castle
//				try{
//					cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
//					cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
					// Cifraremos solamente el nombre y el country (pueden cifrar todos los parámetros que gusten)
//					for (int i = 0; i < accounts.size(); i++) {
//						String accountName = accounts.get(i).getAccountName();
//						byte[] arrAccountName = accountName.getBytes();
//						byte [] accountNameCipher = new byte[cipher.getOutputSize(arrAccountName.length)];
//						int ctAccountNameLength = cipher.update(arrAccountName, 0, arrAccountName.length, accountNameCipher, 0);
//						ctAccountNameLength += cipher.doFinal(accountNameCipher, ctAccountNameLength);
//						accounts.get(i).setAccountName(accountNameCipher.toString());

//						String accountCountry = accounts.get(i).getCountry();
//						byte[] arrAccountCountry = accountCountry.getBytes();
//						byte[] accountCountryCipher = new byte[cipher.getOutputSize(arrAccountCountry.length)];
//						int ctAccountCountryLength = cipher.update(arrAccountCountry, 0, arrAccountCountry.length, accountCountryCipher, 0);
//						ctAccountNameLength += cipher.doFinal(accountCountryCipher, ctAccountCountryLength);
//						accounts.get(i).setCountry(accountCountryCipher.toString());
//					}
//				} catch (NoSuchAlgorithmException e) {
//					throw new RuntimeException(e);
//				} catch (NoSuchProviderException e) {
//					throw new RuntimeException(e);
//				} catch (NoSuchPaddingException e) {
//					throw new RuntimeException(e);
//				} catch (InvalidAlgorithmParameterException e) {
//					throw new RuntimeException(e);
//				} catch (ShortBufferException e) {
//					throw new RuntimeException(e);
//				} catch (IllegalBlockSizeException e) {
//					throw new RuntimeException(e);
//				} catch (BadPaddingException e) {
//					throw new RuntimeException(e);
//				} catch (InvalidKeyException e) {
//					throw new RuntimeException(e);
//				}

//				JSONArray json = new JSONArray(accounts);
//				responseText = json.toString();
//				exchange.getResponseHeaders().add("Content-type", "application/json");
//				exchange.sendResponseHeaders(200, responseText.getBytes().length);
//			} else {
//				/** 405 Method Not Allowed */
//				exchange.sendResponseHeaders(405, -1);
//			}
//			OutputStream output = exchange.getResponseBody();
//			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
//			LOGGER.info("LearningJava - Cerrando recursos ...");
//			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
//			LOGGER.info("Tiempo de respuesta: ".concat(total));
//			output.write(responseText.getBytes());
//			output.flush();
//			output.close();
//			exchange.close();
//		}));


		/**Creamos un default executor**/
//		server.setExecutor(null);
//		server.start();
//		LOGGER.info("Learning Java - server started on port 8080");



	}//termina metodo main

	public static Map<String,String> splitQuery(URI uri) throws UnsupportedEncodingException{
		Map<String,String> query_pairs = new LinkedHashMap<>();
		String query = uri.getQuery();
		String[] pairs = query.split("&");
		for(String pair : pairs){
			int idx = pair.indexOf("=");
			query_pairs.put(URLDecoder.decode(pair.substring(0,idx),"UTF-8"),URLDecoder.decode(pair.substring(idx+1),"UTF-8"));
		}
		return query_pairs;
	}//termina metodo splitQuery

	private static ResponseDTO login(String user, String password){
		UserService UserService = userService();
		return UserService.login(user,password);
	}//termina metodo login

	private static ResponseDTO createUser(String user, String password){
		UserService UserService = userService();
		return UserService.createUser(user,password);
	}//termina metodo createUser

	private static BankAccountDTO getAccountDetails (String user, String lastUsage){
		BankAccountService bankAccountBO = new BankAccountServiceImpl();
		return bankAccountBO.getAccountDetails(user, lastUsage);
	}//termina metodo getAccountDetails


//	@Deprecated(since = "Anotaciones update")
//	private void createUsers(){
//		try{
//			String user ="user";
//			String pass = "password";
//			JSONArray jsonArray = new JSONArray(textThread);
//			JSONObject user1 = new JSONObject(jsonArray.get(0).toString());
//			JSONObject user2 = new JSONObject(jsonArray.get(1).toString());
//			JSONObject user3 = new JSONObject(jsonArray.get(2).toString());
//
//			//Creamos usuario1
//			response = createUser(user1.getString(user),user1.getString(pass));
//			responseTextThread = new JSONObject(response).toString();
//			LOGGER.info("Usuario 1: "+responseTextThread);
//			Thread.sleep(1000);
//			//Creamos usuario2
//			response = createUser(user2.getString(user),user2.getString(pass));
//			responseTextThread = new JSONObject(response).toString();
//			LOGGER.info("Usuario 2: "+responseTextThread);
//			Thread.sleep(1000);
//			//Creamos usuario3
//			response = createUser(user3.getString(user),user3.getString(pass));
//			responseTextThread = new JSONObject(response).toString();
//			LOGGER.info("Usuario 3: "+responseTextThread);
//			Thread.sleep(1000);
//
//		}catch (Exception e){
//			LOGGER.severe(e.getMessage());
//			throw new ExceptionGenerica(e.getMessage());
//		}
//	}//fin del metodo createUsers


	//Creamos el metodo run que viene de la clase Thread
//	@Override
//	public void run(){
//		try{
//			crearUsuarios();
//		}catch(Exception e){
//			LOGGER.severe(e.getMessage());
//			throw new ExceptionGenerica(e.getMessage());
//		}
//	}//fin metodo run


	//Nuevo metodo crear usuarios
//	private void crearUsuarios(){
//		try{
//			String user = "user";
//			String pass = "password";
//			JSONArray jsonArray = new JSONArray(textThread);
//			JSONObject userJson;

//			ResponseDTO response = null;

//			LOGGER.info("jsonArray.length(): " + jsonArray.length());
//			for(int i=0;i<jsonArray.length();i++){
//				userJson = new JSONObject(jsonArray.get(i).toString());
//				response = createUser(userJson.getString(user), userJson.getString(pass));
//				responseTextThread = new JSONObject(response).toString();
//				LOGGER.info("Usuario " + (i+1) + ": " + responseTextThread);
//				Thread.sleep(1000);
//			}
//		}catch(InterruptedException e){
//			throw new RuntimeException(e);
//		}
//	}//fin metodo crearUsuarios


	/*Agregamos funcion llamada get Parameter Value para buscar nuestro parametro
    que se enviara por url
     */
	private static Optional<String> getParameterValue(Map<String,String> param, String paramName){
		String val = param.get(paramName);
		if(val != null && val !=""){
			return Optional.ofNullable(val);
		}
		return Optional.ofNullable("NA");
	}//fin metodo getParameterValue

	//Funcion generica
	private static Optional<Object> getParameterValueObject(Map<String, String> param, String paramName) {
		String val = param.get(paramName);
		if (val != null && val != "") {
			return Optional.ofNullable(val);
		}
		return Optional.ofNullable("NA");
	}//fin getParameterValueObject



}
