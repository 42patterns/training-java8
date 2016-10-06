package com.example.foo.java8;

import com.example.foo.java8.people.Person;
import com.example.foo.java8.people.Phone;
import com.example.foo.java8.shopping.Category;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.*;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.example.foo.java8.people.Sex.FEMALE;
import static com.example.foo.java8.people.Sex.MALE;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Refactor XML parsing and search method
 */
@Ignore
public class J05c_StreamsXmlParseTest {

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
        Stream<Category> allCategories = getCategories();

        return allCategories
                .parallel()
                .filter(category -> category.getName().toLowerCase().contains(catName.toLowerCase()))
                .peek(category -> System.out.println("Found category " + category + " matches the search criteria"))
                .collect(toList());
    }

    /**
     * This is a private method, signature can change
     * Hint: how will performance differ when Stream is returned
     */
    private Stream<Category> getCategories() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try (InputStream stream = open("/shot_categories.xml")) {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

            //recommended to normalize
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("ns1:item");

            return IntStream.range(0, nList.getLength())
                    .mapToObj(nList::item)
                    .filter(n -> n.getNodeType() == Node.ELEMENT_NODE)
                    .map(n -> (Element) n)
                    .map(e -> new Category(Integer.parseInt(e.getElementsByTagName("ns1:catId").item(0).getTextContent()),
                            e.getElementsByTagName("ns1:catName").item(0).getTextContent(),
                            Integer.parseInt(e.getElementsByTagName("ns1:catParent").item(0).getTextContent()),
                            Integer.parseInt(e.getElementsByTagName("ns1:catPosition").item(0).getTextContent()),
                            Boolean.parseBoolean(e.getElementsByTagName("ns1:catIsProductCatalogueEnabled").item(0).getTextContent())));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return Stream.empty();
    }

    private InputStream open(String s) throws IOException {
        return this.getClass().getResource("/shop_categories.xml").openStream();
    }

}
