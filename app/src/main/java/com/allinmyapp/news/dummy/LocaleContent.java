package com.allinmyapp.news.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample country for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class LocaleContent {

    /**
     * A dummy item representing a piece of country.
     */
    public static class LocaleEntity {
        public final String country;
        public final String locale;

        public LocaleEntity(String country, String locale) {
            this.country = country;
            this.locale = locale;
        }

        @Override
        public String toString() {
            return country;
        }
    }
}
