package characters;

/**
 * Created by bertucci on 11/5/2017.
 */

public class CharCreateTests
{
    public static void main(String[] args)
    {
        CharCreate stupid = new CharCreate('w');
        System.out.println(stupid.serialize());
        CharCreate stupid2 = CharCreate.deserialize(stupid.serialize());
        System.out.println(stupid2.serialize());
    }
}
