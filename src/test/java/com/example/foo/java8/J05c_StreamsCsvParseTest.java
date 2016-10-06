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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        try (BufferedReader reader = open("/shop_categories.csv");
             Stream<String> lines = reader.lines()) {

            return lines
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(a -> new Category(
                            Integer.parseInt(a[0]),
                            a[1],
                            Integer.parseInt(a[2]),
                            Integer.parseInt(a[3]),
                            Boolean.parseBoolean(a[4])))
                    .filter(category -> category.getName().toLowerCase().contains(catName.toLowerCase()))
                    .peek(category -> System.out.println("Found category " + category + " matches the search criteria"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private BufferedReader open(String file) throws IOException {
        return new BufferedReader(new InputStreamReader(this.getClass().getResource(file).openStream()));
    }

}
