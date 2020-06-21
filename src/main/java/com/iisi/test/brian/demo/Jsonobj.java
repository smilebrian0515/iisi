package com.iisi.test.brian.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Jsonobj {
    Menu menu;

    @Getter
    @Setter
    public class Menu {
        String id;
        String name;
        List<Item> menuitem;

        @Getter
        @Setter
        public class Item {
            String id;
            String name;
        }
    }
}
