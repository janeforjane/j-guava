package com.janeforjane;

import com.google.common.base.*;
import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CollectionThings {

    public void listIntoString(){

        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = Joiner.on(",").join(names);
//        String result = Joiner.on(",").skipNulls().join(names); - to skip nulls
//        String result = Joiner.on(",").useForNull("nameless").join(names); - to replace nulls with "nameless"

        System.out.println(result);

    }

    public void mapIntoString(){

        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ").withKeyValueSeparator(" = ")
                .join(salary);

        System.out.println(result);

    }

    public void joinNestedColl(){

        List<ArrayList<String>> nested = Lists.newArrayList(
                Lists.newArrayList("apple", "banana", "orange"),
                Lists.newArrayList("cat", "dog", "bird"),
                Lists.newArrayList("John", "Jane", "Adam"));

        String result = Joiner.on(";").join(Iterables.transform(nested,
                new Function<List<String>, String>() {
                    @Override
                    public String apply(List<String> input) {
                        return Joiner.on("-").join(input);
                    }
                }));

        System.out.println(result);

    }

    public void listFromString(){

        String input = "apple - banana - orange";
        List<String> result = Splitter.on("-").trimResults() //trimResults() removes the leading and trailing whitespace from the resulting substrings
                .splitToList(input);

        System.out.println(result.size());
        System.out.println(result.get(0));

    }

    public void mapFromString(){

        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(input);
    }

    public void reverseList(){
        List<String> names = Lists.newArrayList("John", "Adam", "Jane");
        List<String> reversed = Lists.reverse(names);
        //"Jane", "Adam", "John"
    }

    public void charsListFromString(){
        List<Character> chars = Lists.charactersOf("John");
        //'J', 'o', 'h', 'n'
    }

    public void partitionList(){
        List<String> names = Lists.newArrayList("John","Jane","Adam","Tom","Viki","Tyler");
        List<List<String>> result = Lists.partition(names, 2);
        //("John", "Jane")
        //("Adam", "Tom")
        //("Viki", "Tyler")
    }

    public void removeDuplicates(){
        List<Character> chars = Lists.newArrayList('h','e','l','l','o');
        List<Character> result = ImmutableSet.copyOf(chars).asList();
        //('h', 'e', 'l', 'o')
    }

    public void removeNulls(){
        List<String> names = Lists.newArrayList("John", null, "Adam", null, "Jane");
        Iterables.removeIf(names, Predicates.isNull());
        //("John", "Adam", "Jane")
    }

    public void checkContainingWithCustomRule(){

        Iterable<String> theCollection = Lists.newArrayList("a", "bc", "def");
        boolean contains = Iterables.any(theCollection, new Predicate<String>() {
            @Override
            public boolean apply(final String input) {
                return input.length() == 1;
            }
        });
    }

    public void findMinMax(){
        List<Integer> toSort = Arrays.asList(2, 1, 11, 100, 8, 14);
        int minfound = Ordering.usingToString().min(toSort);
        int maxfound = Ordering.usingToString().max(toSort);
        //1,100
    }


}
