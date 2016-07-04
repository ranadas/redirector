package com.rdas.model

import groovy.transform.ToString
import lombok.Getter
import lombok.Setter
import org.hibernate.validator.constraints.NotEmpty

/**
 * Created by rdas on 04/07/2016.
 */
@Getter
@Setter
@ToString
class Message {
    private Long id;

    @NotEmpty(message = "Message is required.")
    private String text;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    private Calendar created = Calendar.getInstance();
}
