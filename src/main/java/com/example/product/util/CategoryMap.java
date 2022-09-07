package com.example.product.util;



import com.example.product.dto.CategoryMapper;
import com.example.product.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMap {
    public static List<CategoryMapper> MapImage(List<Category> ctg){
        List<CategoryMapper> listDTO = new ArrayList<>();
        for (Category item:ctg
        ) {
            listDTO.add(new CategoryMapper(item.getId(), item.getName()));

        }
        return listDTO;
    }
}
