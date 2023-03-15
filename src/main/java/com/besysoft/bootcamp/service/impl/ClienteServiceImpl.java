package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.service.IClienteService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class ClienteServiceImpl implements IClienteService {
}
