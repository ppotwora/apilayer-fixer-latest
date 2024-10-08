package org.iceo.apilayer.http.models.responses;

import lombok.*;

@Value
@NoArgsConstructor(force = true)
public class GetErrorResponse {

    Boolean success;

    ErrorDetails error;
}

