package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.client.AccountsJSONClient;
import com.wizeline.maven.learningjavamaven.iterator.BankAccountDTOCollection;
import com.wizeline.maven.learningjavamaven.iterator.BankAccountDTOCollectionImpl;
import com.wizeline.maven.learningjavamaven.iterator.Iterator;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.Post;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.service.BankAccountService;
import com.wizeline.maven.learningjavamaven.service.UserService;
import com.wizeline.maven.learningjavamaven.service.WelcomeUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.wizeline.maven.learningjavamaven.utils.Utils.*;

@Tag(name = "BankAccount", description = "Acceso información de cuentas .")
@RequestMapping("/api")
@RestController
public class BankAccountController {

    private static String SUCCESS_CODE = "OK000";

    @Value("${server.port}")
    private String port;

    @Autowired
    private KafkaTemplate<Object,Object> template;

    @Autowired
    AccountsJSONClient accountsJSONClient;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = Logger.getLogger(BankAccountController.class.getName());
    String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion BankAccountController...";

    @GetMapping(value="/getUserAccount",produces = "application/json")
    public ResponseEntity<?> getUserAccount(@RequestParam String user, @RequestParam String password, @RequestParam String date){
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion =Instant.now(); //Se crea objeto de tipo Instant con la hora actual
        ResponseDTO response = new ResponseDTO();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        String responseText = "";

        if(validateNullValue(date) && isDateFormatValid(date)){
            if(isPasswordValid(password)){
                response = userService.login(user,password);
                if(response.getCode().equals(SUCCESS_CODE)){
                    BankAccountDTO bankAccountDTO = bankAccountService.getAccountDetails(user,date);
                    getTiempoRespuesta(inicioDeEjecucion);
                    return new ResponseEntity<>(bankAccountDTO,responseHeaders,HttpStatus.OK);

                }else{
                    getTiempoRespuesta(inicioDeEjecucion);
                    responseText = "Nombre de usuario o password incorrecto";
                    return new ResponseEntity<>(responseText, responseHeaders, HttpStatus.OK);
                }
            }else{
                getTiempoRespuesta(inicioDeEjecucion);
                responseText = "Password Incorrecto";
                return new ResponseEntity<>(responseText, responseHeaders, HttpStatus.OK);
            }
        }else{
            getTiempoRespuesta(inicioDeEjecucion);
            responseText = "Formato de fecha incorrecto";
            return new ResponseEntity<>(responseText, responseHeaders, HttpStatus.OK);
        }//fin validacion date

    }//fin metodo getUserAccount


    @GetMapping(value="/getAccounts",produces = "application/json")
    public ResponseEntity<List<BankAccountDTO>> getAccounts() {
        LOGGER.info("El puerto usado es "+port);
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion = Instant.now();
        List<BankAccountDTO> accounts = bankAccountService.getAccounts();

        //Implementar el patron de diseño de comportamiento Iterator
        List<BankAccountDTO> accountsIteratorList = new ArrayList<>();
        BankAccountDTOCollection bankAccountDTOCollection = getAccountCollection(accounts);
        Iterator iterator = bankAccountDTOCollection.iterator("ALL");
        while(iterator.hasNext()){
            BankAccountDTO b = iterator.next();
            accountsIteratorList.add(b);
        }//terminacion implementación patron de diseño de comportamiento Iterator

        getTiempoRespuesta(inicioDeEjecucion);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(accountsIteratorList, responseHeaders, HttpStatus.OK);
    }//fin metodo getAccounts

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/getAccountsGroupByType",produces = "application/json")
    public ResponseEntity<Map<String, List<BankAccountDTO>>> getAccountsGroupByType() {
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion = Instant.now();

        List<BankAccountDTO> accounts = bankAccountService.getAccounts();

        // Aqui implementaremos la programación funcional
        Map<String,List<BankAccountDTO>> groupedAccounts;
        Function<BankAccountDTO,String> groupFunction = (account) -> account.getAccountType().toString();
        groupedAccounts = accounts.stream().collect(Collectors.groupingBy(groupFunction));

        getTiempoRespuesta(inicioDeEjecucion);

        return new ResponseEntity<>(groupedAccounts, HttpStatus.OK);

    }//fin metodo getAccountsGroupByType

    @Operation(summary = "Envia saludo al usuario")
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHelloGuest(@RequestParam String user) {
        WelcomeUser welcomeUser = (nameUser) -> "Bienvenido " + nameUser;
        return new ResponseEntity<>(welcomeUser.sayHelloTo(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/executeApi")
    public ResponseEntity<String> executeApiExterna() {
        RestTemplate plantilla = new RestTemplate();
        String resultado = plantilla.getForObject("https://pokeapi.co/api/v2/pokemon/ditto",String.class);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }


    @DeleteMapping("/deleteAccounts")
    public ResponseEntity<String> deleteAccounts(){
        bankAccountService.deleteAccounts();
        return new ResponseEntity<>("Todas las cuentas borradas",HttpStatus.OK);
    }//fin del metodo deleteAccounts

    @PutMapping("updateAccounts")
    public ResponseEntity<String> updateAccounts(@RequestParam String user){
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion = Instant.now();
        bankAccountService.updateAccounts(user);
        getTiempoRespuesta(inicioDeEjecucion);
        return new ResponseEntity<>("Se actualiza accountName del usuario ".concat(user),HttpStatus.OK);
    }//fin del metodo updateAccounts


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getAccountByUser")
    public ResponseEntity<List<BankAccountDTO>> getAccountByUser(@RequestParam String user) {
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion = Instant.now();
        LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
        List<BankAccountDTO> accounts = bankAccountService.getAccountByUser(user);
        Instant finalDeEjecucion = Instant.now();
        getTiempoRespuesta(inicioDeEjecucion,finalDeEjecucion);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(accounts, responseHeaders, HttpStatus.OK);
    }//fin del metodo getAccountByUser


    //Uso de FeignClient for demo purposes
    @GetMapping("/getExternalUser/{userId}")
    public ResponseEntity<Post> getExternalUser(@PathVariable Long userId) {
        Post postTest = accountsJSONClient.getPostById(userId);
        LOGGER.info("Getting post userId..." +postTest.getUserId());
        LOGGER.info("Getting post body..." +postTest.getBody());
        LOGGER.info("Getting post title..." +postTest.getTitle());
        postTest.setUserId("External user "+randomAccountNumber());
        postTest.setBody("No info in accountBalance since it is an external user");
        postTest.setTitle("No info in title since it is an external user");
        LOGGER.info("Setting post userId..." +postTest.getUserId());
        LOGGER.info("Setting post body..." +postTest.getBody());
        LOGGER.info("Setting post title...."+postTest.getTitle());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(postTest, responseHeaders, HttpStatus.OK);
    }//fin del metodo getExternalUser


    @PostMapping(path = "/send/{userId}")
    public void sendUserAccount(@PathVariable int userId) {
        List<BankAccountDTO> accounts = bankAccountService.getAccounts();
        LOGGER.info("------------------registro a seleccionar "+userId);
        BankAccountDTO account = accounts.get(userId);
        LOGGER.info("------------------Total de cuentas "+accounts.size());
        LOGGER.info("------------------ "+account.getUser());
        this.template.send("useraccount-topic", account);
    }


    private void getTiempoRespuesta(Instant inicioDeEjecucion){
        Instant finalDeEjecucion = Instant.now();
        String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
        LOGGER.info("Tiempo de respuesta: ".concat(total));
    }

    //Sobrecarga de metodo getTiempoRespuesta
    private void getTiempoRespuesta(Instant inicioDeEjecucion, Instant finalDeEjecucion){
        String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
        LOGGER.info("Tiempo de respuesta: ".concat(total));
    }

    private BankAccountDTOCollection getAccountCollection (List<BankAccountDTO> bankAccountDTOList){
        BankAccountDTOCollection bankAccountDTOCollection = new BankAccountDTOCollectionImpl();
        for(BankAccountDTO bankAccountDTO : bankAccountDTOList){
            bankAccountDTOCollection.addBankAccountDTO(bankAccountDTO);
        }
        return bankAccountDTOCollection;
    }

}
