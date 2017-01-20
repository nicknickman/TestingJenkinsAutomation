package transformation;

import cucumber.api.Transformer;

/**
 * Created by Nick on 15/01/2017.
 */
public class EmailTransform extends Transformer<String> {

    @Override
    public String transform(String userName) {
        return userName.concat("@ea.com");
    }
}
