package com.codecademy.diningreviewapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {

        PENDING("THIS REVIEW IS PENDING FOR APPROVAL"),
        APPROVED("THIS REVIEW WAS APPROVED"),
        REJECTED("THIS REVIEW WAS REJECTED");

        @Getter
        private String value;
}
