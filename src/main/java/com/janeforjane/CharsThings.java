package com.janeforjane;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

public class CharsThings {


    public void removeChars(){

        //without digits
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.javaLetterOrDigit();
        String result = matcher.retainFrom(input);

        System.out.println(result);//Hello12

        //without symbols
        String input2 = "あhello₤";
        String result2 = CharMatcher.ascii().retainFrom(input2);

        System.out.println(result2);//hello


        //without symbols out of range
        String input3 = "hello";
        String result3 = CharMatcher.inRange('0', 'm').retainFrom(input3);

        System.out.println(result3);//hell


        //without symbols out of charset
        Charset charset = Charset.forName("cp437");
//        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();

        Predicate<Character> inRange = new Predicate<Character>() {
            @Override
            public boolean apply(Character c) {
                return encoder.canEncode(c);
            }
        };

        String result4 = CharMatcher.forPredicate(inRange)
                .retainFrom("ЩHelloは");

        System.out.println(result4);//Hello


    }

    public void checkString(){

        String input = "hello";

        boolean result = CharMatcher.javaLowerCase().matchesAllOf(input);
        System.out.println(result);

        result = CharMatcher.is('e').matchesAnyOf(input);
        System.out.println(result);

        result = CharMatcher.javaDigit().matchesNoneOf(input);
        System.out.println(result);
    }

    public void trimString(){

        String input = "---hello,,,";

        String result = CharMatcher.is('-').trimLeadingFrom(input);
        System.out.println(result);//hello,,,

        String result2 = CharMatcher.is(',').trimTrailingFrom(input);
        System.out.println(result2);//---hello

        String result3 = CharMatcher.anyOf("-,").trimFrom(input);
        System.out.println(result3);//hello

    }

    public void collapseFromString(){

        String input = "       hel    lo      ";

        String result = CharMatcher.is(' ').collapseFrom(input, '-');
        System.out.println(result);//-hel-lo-

        String result2 = CharMatcher.is(' ').trimAndCollapseFrom(input, '-');
        System.out.println(result2);//hel-lo
    }

    public void replaceFromString(){

        String input = "apple-banana.";

        String result = CharMatcher.anyOf("-.").replaceFrom(input, '!');
        System.out.println(result);//apple!banana!

        String result2 = CharMatcher.is('-').replaceFrom(input, " and ");
        System.out.println(result2);//apple and banana.

    }

    public void countOccurrences(){

        String input = "a, c, z, 1, 2";

        int result = CharMatcher.is(',').countIn(input);
        System.out.println(result);//4

        int result2 = CharMatcher.inRange('a', 'h').countIn(input);
        System.out.println(result2);//2

    }

    public void splitString(){
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.,]")
                .omitEmptyStrings()
                .splitToList(input);

        System.out.println(result.toString());


    }

}
