package tpintrospection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.Arrays;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        // Question 13 : SecurityManager deprecated since 17 with no replacement
        SecurityManager sm = new SecurityManager();
        //System.setSecurityManager(sm);
        if (System.getSecurityManager() != null){
            System.out.println("Connection is established!");
        }

        try {
            Vector gifts = PapiBarbu.vaChercherLesCadeaux();
            System.out.println("Ouh le gros cadeau que voilà ! Voyons voir ce qu'il y a dedans...\n ");

            for (Object gift: gifts){
                introspect(gift);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void introspect(Object o) throws NoSuchFieldException {

        // Question 2 : Class object : toString() inutile en soit car toString() déjà fait dans le valueOf du println()
        //System.out.println(o.toString());

        // Question 5 : Class class et package java lang reflect
        Class reflectPermissionClass = ReflectPermission.class;

        Method[] methods = reflectPermissionClass.getMethods();
        Constructor[] constructors = reflectPermissionClass.getConstructors();
        Field[] fields = reflectPermissionClass.getFields();
        Field[] declaredFields = reflectPermissionClass.getDeclaredFields();

        //display(methods, constructors, fields, declaredFields);

        // Question 6 : sur les elements contenus dans le vecteur
        Class objectClass = o.getClass();

        Method[] methods2 = objectClass.getMethods();
        Constructor[] constructors2 = objectClass.getConstructors();
        Field[] fields2 = objectClass.getFields();
        Field[] declaredFields2 = objectClass.getDeclaredFields();

        //display(methods2, constructors2, fields2, declaredFields2);

        // Question 10 / 12 : Field codeSecret : donnez le code t0nt0nR@0uL pour +1 au TP

        try {
            Field codeSecret = objectClass.getDeclaredField("codeSecret");
            codeSecret.setAccessible(true);
            //field.set(o, "donnez le code t0nt0nR@0uL pour +20 au TP"); -> dommage que ça soit final :-)
            System.out.println(codeSecret.getName() + codeSecret.getType() + " : " + codeSecret.get(o));
        }
        catch (NoSuchFieldException | IllegalAccessException e){
            //e.printStackTrace();
        }

        try {
            Field i = objectClass.getDeclaredField("i");
            //System.out.println(i.getName() +" "+ i.getType() + " : " + i.get(o));
            i.setAccessible(true);
            i.setInt(o,i.getInt(o)-42);
            System.out.println("setInt - 42 : " + i.getName() + " " + i.getType() + " : " + i.get(o));

        }
        catch (NoSuchFieldException | IllegalAccessException e){
            //e.printStackTrace();
        }

    }

    private static void display(Method[] methods, Constructor[] constructors, Field[] fields, Field[] declaredFields) {
        System.out.println("fields: " + Arrays.toString(fields));
        System.out.println("declaredFields: " + Arrays.toString(declaredFields));
        System.out.println("methods: "  + Arrays.toString(methods));
        System.out.println("constructors: " + Arrays.toString(constructors));

        System.out.println("");
    }

}
