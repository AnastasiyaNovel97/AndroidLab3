package core.services;

import android.content.Context;

import core.recycler_view.ImageRecyclerItem;
import core.utils.Utils;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Supplier;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RecycleItemSupplier implements Supplier<ImageRecyclerItem> {
    static String PROPERTIES_FILE_NAME = "application.properties";
    List<String> LINKS;
    private static int numOfItem = 0;

    @SneakyThrows(IOException.class)
    public RecycleItemSupplier(Context context) {
        Properties appProps = new Properties();
        appProps.load(context.getAssets().open(PROPERTIES_FILE_NAME));
        LINKS = new ArrayList<>(Arrays.asList(appProps.getProperty("lab.links","").split(",")));
    }

    public ImageRecyclerItem generateItem() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return new ImageRecyclerItem("Арт "+(1+numOfItem++), LINKS.get(numOfItem-1)); //+" "+generatedString //Utils.getRandomElement(LINKS)
    }

    @Override
    public ImageRecyclerItem get() {
        return generateItem();
    }
}
