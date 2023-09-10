package com.elastic.springbootelasticsearch.elasticsearch.service.validator;

import java.util.ArrayList;
import java.util.List;

import com.elastic.springbootelasticsearch.common.validator.Validator;

import org.springframework.stereotype.Service;

import com.elastic.springbootelasticsearch.common.constants.Messages;
import com.elastic.springbootelasticsearch.elasticsearch.dto.ProductElasticDTO;

@Service
public class ProductElasticValidator {
    public Validator requestValidator(ProductElasticDTO dto)
    {
        List<String> message = new ArrayList<>();
        if (dto.getId() == null) {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "id"));
        } else {
            if (dto.getId().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "id"));
            }
        }
        if(dto.getName() == null)
        {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "name"));

        }
        else {
            if(dto.getName().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "name"));
            }

        }

        if(dto.getDescription() == null)
        {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "description"));

        }
        else {
            if(dto.getDescription().isEmpty())
            {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "description"));
            }
        }
        if(dto.getPrice() == null)
        {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "price"));
        }

        if(message.isEmpty())
        {
           return new Validator().yes();
        }
        else {
            String result = "";
            for(String str : message)
            {
                if(result.isEmpty())
                {
                    result = str;
                }
                else{
                    result+=", "+str;
                }
            }
            return new Validator().no(result);
        }
    }
}
