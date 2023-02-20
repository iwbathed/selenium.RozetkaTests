package constants;

public class Constant {
    public static class TimeOutVariable {
        public static final int IMPLICITLY_WAIT = 4;
        public static final int EXPLICITLY_WAIT = 20;

    }

    public static class Urls {
        public static final String ROZETKA_HOME_PAGE = "https://rozetka.com.ua/ua/";
    }
    public static class ListingData {
        public static final int EXPECTED_ELEMENTS_NUMBER = 60;
    }
    public static class searchInput{
        public static final String COMPUTERS = "комп'ютери";
        public static final String NOTEBOOKS = "ноутбуки";
    }

    public enum SortOptions {
        CHEAP ("Від дешевих до дорогих"),
        EXPENSIVE ("Від дорогих до дешевих");

        private final String option;
        SortOptions(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }
    }


}
