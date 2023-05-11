package com.wizeline.maven.learningjavamaven.utils;

import com.wizeline.maven.learningjavamaven.enums.AccountType;
import com.wizeline.maven.learningjavamaven.enums.Country;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String PASSWORD_PATTERN = "^(.*[a-zA-Z].*[0-9])$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");

    public static String getString(String value){
        if(value != null){
            return value;
        }
        return "";
    }

    public static boolean validateNullValue(String value){
        if(value != null){
            return true;
        }
        return false;
    }

    public static long randomAccountNumber(){
        return new Random().nextLong();
    }

    public static double randomBalance (){
        return new Random().doubles(1000,9000).limit(1).findFirst().getAsDouble();
    }

    public static AccountType pickRandomAccountType(){
        //Creando un arreglo a partir de valores ya definidos
        AccountType [] accountTypes = AccountType.values();
        return accountTypes[new Random().nextInt(accountTypes.length)];
    }

    public static String randomInt(){
        return String.valueOf(new Random().ints(1,10).findFirst().getAsInt());
    }

    /*Gets Country
    *
    * @param country código de país
    * @return nombre del país correspondiente al código recibido
     */
    public static String getCountry(Country country){
        Map<Country,String> countries = new HashMap<>();
        countries.put(Country.US,"United States");
        countries.put(Country.MX, "Mexico");
        countries.put(Country.FR, "France");
        return countries.get(country);
    }

    public static boolean isDateFormatValid(String date){
        //Valida la fecha contra el patron que definimos
        return DATE_PATTERN.matcher(date).matches();
    }

    public static boolean isPasswordValid(String password) {
        // Valida la contraseña contra el patron que definimos
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}//fin clase Utils
