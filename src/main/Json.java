package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.harawata.appdirs.AppDirsFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;

public class Json {

    private static final String appDir = AppDirsFactory.getInstance().getUserDataDir("DDO Manager", "", "Tealeaf", true);

    private static Gson gStatic;
    private static Gson gObject;

    /**
     * Initializes the Json writers
     */
    public static void load() {
        //Create the builder
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();

        //Add any custom serialization and deserialization methods

        //Create the object
        gObject = builder.create();
        gStatic = builder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT).create();

    }

    //Serialize and Deserialize methods

    public static String serialize(Object src, boolean isStatic) {
        return (isStatic ? gStatic : gObject).toJson(src);
    }

    public static void serialize(Object src, boolean isStatic, Writer writer) {
        (isStatic ? gStatic : gObject).toJson(src, writer);
    }

    public static Object deserialize(String json, boolean isStatic, Type cls) {
        return (isStatic ? gStatic : gObject).fromJson(json, cls);
    }

    public static Object deserialize(BufferedReader reader, boolean isStatic, Type cls) {
        return (isStatic ? gStatic : gObject).fromJson(reader, cls);
    }


    //File Methods

    public static File getFile(String... path) {
        return new File(java.nio.file.Paths.get(appDir, path).toString());
    }

    public static void saveObject(Object src, boolean isStatic, String... path) {
        File f = getFile(path);
        if (f.getParentFile().mkdirs()) System.out.println("Created Directory " + f.getParentFile().getPath());

        try {
            FileWriter writer = new FileWriter(f);
            serialize(src, isStatic, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(boolean isStatic, Type cls, String... path) {
        try {
            return deserialize(Files.newBufferedReader(getFile("Settings.json").toPath()), isStatic, cls);
        } catch (Exception e) {
            return null;
        }
    }


}
