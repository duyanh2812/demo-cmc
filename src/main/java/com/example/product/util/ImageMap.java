package com.example.product.util;
import com.example.product.dto.ImageMapper;
import com.example.product.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageMap {
    public static List<ImageMapper>  MapImage(List<Image> img){
        List<ImageMapper> listDTO = new ArrayList<>();
        for (Image item:img
             ) {
            listDTO.add(new ImageMapper(item.getId(), item.getUrl()));

        }
        return listDTO;
    }
}
