package org.cloudfoundry.samples.music.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.UUID;

public class RandomIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return generateId();
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
