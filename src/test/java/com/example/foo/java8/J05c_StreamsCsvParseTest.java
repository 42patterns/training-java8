package com.example.foo.java8;

import com.example.foo.java8.shopping.Category;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Refactor CSV parsing and search method
 */
@Ignore
public class J05c_StreamsCsvParseTest {

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description) {
            String desc = String.format("Test %s spent %d microseconds",
                    description.getMethodName(), TimeUnit.NANOSECONDS.toMicros(nanos));
            System.out.println(desc);
        }
    };

    @Test
    public void should_return_categories_for_bikes() {
        List<Category> bikeCategories = getMatchingCategories("rower");
        assertThat(bikeCategories, hasSize(13));
    }

    /**
     * This is a "public API" do not change signature of this class
     */
    public List<Category> getMatchingCategories(String catName) {
        List<Category> allCategories = getCategories();
        List<Category> catNames = new ArrayList<>();

        for (int i = 0; i < allCategories.size(); i++) {
            int counter = 0;
            if (allCategories.get(i).getName().toLowerCase().contains(catName.toLowerCase())) {
                catNames.add(allCategories.get(i));
                counter++;
                System.out.println("Found " + counter + " matching categories, that contains " + catName + "\n" +
                        "Category " + allCategories.get(i) + " matches the seaarch criteria");
            }
        }
        return catNames;
    }

    /**
     * This is a private method, signature can change
     * Hint: how will performance differ when Stream is returned
     */
    private List<Category> getCategories() {
        List<Category> allCategories = new ArrayList<>();

        try (BufferedReader reader = open("/shop_categories.csv")) {

            //skip first CSV line
            reader.readLine();

            String line;
            while( (line = reader.readLine()) != null) {
                String[] el = line.split(";");
                allCategories.add(new Category(
                        Integer.parseInt(el[0]),
                        el[1],
                        Integer.parseInt(el[2]),
                        Integer.parseInt(el[3]),
                        Boolean.parseBoolean(el[4])));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allCategories;
    }

    private BufferedReader open(String file) throws IOException {
        return new BufferedReader(new InputStreamReader(this.getClass().getResource(file).openStream()));
    }

}
