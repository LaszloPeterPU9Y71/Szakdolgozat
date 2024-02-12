package Szakdolgozat.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingSortingFilteringRequest {
    private Integer page;
    private Integer size;
    private Sorting sorting;
    private Sort sort;
    private String search;

    public enum Sort {
        DEFAULT("name"),
        BY_LAST_NAME("title"),
        BY_EMAIL("email"),
        BY_ID("id");

        private String value;

        Sort(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Sorting {
        ASC("ASC"),
        DESC("DESC");

        private String value;

        Sorting(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
