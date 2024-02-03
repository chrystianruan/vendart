package com.api.vendart.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUtil {
    public List<String> getEmptyListWithMessage(String message) {
        List<String> emptyList = new ArrayList<>();
        emptyList.add(message);

        return emptyList;
    }

}
