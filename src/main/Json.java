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

    /**
     * Serializes an object or class
     *
     * @param src      Object to Serialize
     * @param isStatic Include static variables
     * @return JSON representative of the Object
     */
    public static String serialize(Object src, boolean isStatic) {
        return (isStatic ? gStatic : gObject).toJson(src);
    }

    /**
     * Serializes an object or class directly into a writer
     *
     * @param src      Object to Serialize
     * @param isStatic Include static variables
     * @param writer   Writer to write the JSON into
     */
    public static void serialize(Object src, boolean isStatic, Writer writer) {
        (isStatic ? gStatic : gObject).toJson(src, writer);
    }

    /**
     * Deserializes an object from a JSON representation
     *
     * @param json     JSON representation of the object to deserialize
     * @param isStatic Whether or not to deserialize static fields
     * @param cls      Class of object to deserialize, ex: {@code Settings.class}
     * @return Deserialized Object represented in the JSON
     */
    public static Object deserialize(String json, boolean isStatic, Type cls) {
        return (isStatic ? gStatic : gObject).fromJson(json, cls);
    }

    /**
     * Deserializes an object from a JSON representation
     *
     * @param reader   Reader of JSON representative of the object
     * @param isStatic Whether or not to deserialize static fields
     * @param cls      Class of object to deserialize, ex: {@code Settings.class}
     * @return Deserialized Object represented in the JSON
     */
    public static Object deserialize(BufferedReader reader, boolean isStatic, Type cls) {
        return (isStatic ? gStatic : gObject).fromJson(reader, cls);
    }


    //File Methods

    /**
     * Gets the file from a path, starting in a given application directory
     * <p><b>Note</b>: This will return the file, whether or not it exists</p>
     *
     * @param path Path within the application directory
     * @return File with the given path
     */
    public static File getFile(String... path) {
        return new File(java.nio.file.Paths.get(appDir, path).toString());
    }

    /**
     * Saves an object as a JSON into a file
     *
     * @param src      Object to save into the file
     * @param isStatic Whether or not to include static fields
     * @param path     Path within the application directory to save the file
     */
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

    /**
     * Loads an object from a JSON in a file
     *
     * @param isStatic Whether or not to deserialize static fields
     * @param cls      Class of object to deserialize, ex: {@code Settings.class}
     * @param path     Path within the application directory
     * @return Deserialized Object represented in the JSON
     */
    public static Object readObject(boolean isStatic, Type cls, String... path) {
        try {
            return deserialize(Files.newBufferedReader(getFile("Settings.json").toPath()), isStatic, cls);
        } catch (Exception e) {
            return null;
        }
    }


}
