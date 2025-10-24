package dev.patika.veterinary.Management.System.core.config.modelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();
}
