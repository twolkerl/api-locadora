package com.twl.apilocadora.util;

import org.springframework.data.domain.ExampleMatcher;

public class MatcherUtils {

    public static ExampleMatcher matchAnyContainingIgnoreCase() {
        return ExampleMatcher
                .matchingAny()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }
}
