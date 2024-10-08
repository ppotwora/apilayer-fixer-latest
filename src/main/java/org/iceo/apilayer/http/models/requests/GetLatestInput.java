package org.iceo.apilayer.http.models.requests;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class GetLatestInput {

    String base;

    String symbols;
}
