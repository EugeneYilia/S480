import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i + 1);
            result.add(Arrays.stream(products)
                    .filter(product -> product.startsWith(prefix))
                    .sorted()
                    .limit(3)
                    .toList());
        }

        return result;
    }
}
