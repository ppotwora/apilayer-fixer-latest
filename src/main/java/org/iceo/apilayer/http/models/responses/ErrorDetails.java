package org.iceo.apilayer.http.models.responses;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class ErrorDetails {

    Integer code;

    String type;

    String info;
}

